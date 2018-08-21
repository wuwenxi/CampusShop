package com.wwx.ssm.o2o.service.impl;

import com.wwx.ssm.o2o.enums.ShopStateEnum;
import com.wwx.ssm.o2o.dao.ShopMapper;
import com.wwx.ssm.o2o.entity.Shop;
import com.wwx.ssm.o2o.exception.ShopException;
import com.wwx.ssm.o2o.execution.ShopExecution;
import com.wwx.ssm.o2o.service.ShopService;
import com.wwx.ssm.o2o.utils.ImageUtils;
import com.wwx.ssm.o2o.utils.PageCalculator;
import com.wwx.ssm.o2o.utils.PathUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    ShopMapper mapper;

    /**
     *       店铺注册  service层 逻辑
     *       抛出异常的作用  终止事务
     *
     *        改造后的addShop方法： 传入shop对象，输入流，文件名称
     * @param shop
     * @param inputStream
     * @return
     */
    public ShopExecution addShop(Shop shop,InputStream inputStream,String fileName) throws ShopException {
        if(shop == null){
            //店铺信息为空
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        }
        if(shop.getArea() == null ){
            //地区为空
            return new ShopExecution(ShopStateEnum.NULL_AREA_ID);
        }
        if (shop.getShopCategory()==null){
            //店铺类别为空
            return new ShopExecution(ShopStateEnum.NULL_SHOP_CATEGORY_ID);
        }

        /**
         *   实现店铺添加逻辑
         *   1. 将店铺添加进入数据库
         *   2.
         */
        try {
            //将初始状态设为审核  给店铺写入创建时间和更新时间
            shop.setEnableStatus(0);
            shop.setCreateTime(new Date());
            shop.setLastEditTime(new Date());
            //插入数据库

            int number = mapper.insertShop(shop);
            if(number <= 0){
                throw new SecurityException("店铺添加失败");
            }else {
                //添加图片
                if(inputStream!= null){
                    try{
                        //添加图片地址
                        addShopImg(shop,inputStream,fileName);
                    }catch (Exception e){
                        throw new ShopException("updateInputStream ERROR:" + e.getMessage());
                    }

                    //更新店铺的图片地址
                    number = mapper.updateShop(shop);
                    if(number<=0){
                        throw new ShopException("更新图片地址失败");
                    }else {
                        //店铺创建成功
                        return new ShopExecution(ShopStateEnum.CHECK,shop);
                    }
                }else {
                    throw new ShopException("店铺图片未知");
                }
            }
        } catch (Exception e) {
            throw new ShopException("addShop ERROR : "+e.getMessage());
        }

    }

    private void addShopImg(Shop shop,InputStream inputStream,String fileName){
        //获取相对路径  "upload/item/shop/"+ shopId + "/"
        String path = PathUtils.getShopImagePath(shop.getShopId());
        //存储图片的绝对路径
        String realPath = ImageUtils.generateThumbnail(inputStream,fileName,path);
        //将图片地址存入数据库
        shop.setShopImg(realPath);
    }

    public void updateShop(Shop shop) {
        mapper.updateShop(shop);
    }

    public void deleteShopById(Integer id) {
        mapper.deleteShopById(id);
    }

    public Shop getShopById(Integer id) {
        return mapper.queryShopById(id);
    }

    public ShopExecution modifyShop(Shop shop, InputStream in, String fileName) throws ShopException{

        if(shop == null || shop.getShopId() == null){
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        }else {
            //  1.  判断是否需要更新图片
            try {
                if(in != null && fileName!=null && !"".equals(fileName)){
                    Shop shopImg = mapper.queryShopById(shop.getShopId());
                    if(shopImg.getShopImg()!=null){
                        ImageUtils.deleteFileOrPath(shopImg.getShopImg());
                    }
                    //更新图片
                    addShopImg(shop,in,fileName);
                }
                // 2.  更新商铺信息
                shop.setLastEditTime(new Date());
                try {
                    mapper.updateShop(shop);
                } catch (Exception e) {
                    return new ShopExecution(ShopStateEnum.INNER_ERROR);
                }
                shop = mapper.queryShopById(shop.getShopId());
                return new ShopExecution(ShopStateEnum.SUCCESS,shop);
            } catch (Exception e) {
                return new ShopExecution(ShopStateEnum.INNER_ERROR);
            }
        }
    }

    public ShopExecution getShopList(Shop shop, Integer pageIndex, Integer pageSize) {
        //转换页码
        int rowIndex = PageCalculator.calculatorRowIndex(pageIndex,pageSize);
        //获取店铺列表
        List<Shop> shopList = mapper.queryShopList(shop, rowIndex, pageSize);
        //获取店铺总数
        int count = mapper.queryShopCount(shop);
        ShopExecution sc = new ShopExecution();
        if(shopList!=null){
            sc.setShops(shopList);
            sc.setCount(count);
        }else{
            sc.setState(ShopStateEnum.INNER_ERROR.getState());
        }
        return sc;
    }

}
