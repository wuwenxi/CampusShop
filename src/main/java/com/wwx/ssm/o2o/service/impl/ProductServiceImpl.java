package com.wwx.ssm.o2o.service.impl;

import com.wwx.ssm.o2o.bean.ImageHolder;
import com.wwx.ssm.o2o.dao.ProductImgMapper;
import com.wwx.ssm.o2o.dao.ProductMapper;
import com.wwx.ssm.o2o.entity.Product;
import com.wwx.ssm.o2o.entity.ProductImg;
import com.wwx.ssm.o2o.enums.ProductEnum;
import com.wwx.ssm.o2o.exception.ProductException;
import com.wwx.ssm.o2o.exception.ProductImgException;
import com.wwx.ssm.o2o.execution.ProductExecution;
import com.wwx.ssm.o2o.service.ProductService;
import com.wwx.ssm.o2o.utils.ImageUtils;
import com.wwx.ssm.o2o.utils.PathUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductMapper mapper;

    @Autowired
    ProductImgMapper imgMapper;

    public ProductExecution getProductList(Integer shopId) {
        try {
            List<Product> list =  mapper.queryProductList(shopId);
            if(list!=null && list.size()>0){
                return new ProductExecution(ProductEnum.SUCCESS,list);
            }else {
                return new ProductExecution(ProductEnum.ERROR);
            }
        } catch (Exception e) {
            throw new ProductException("errorMsg:"+e.getMessage());
        }
    }

    public ProductExecution addProduct(Product product, ImageHolder image,List<ImageHolder> imageHolderList) {
        if(product!= null && product.getShop()!=null && product.getShop().getShopId()!=null){
            //添加时间
            product.setCreateTime(new Date());
            product.setLastEditTime(new Date());
            //设为上架状态
            product.setEnableStatus(1);

            //插入缩略图的地址
            if(image!=null){
                try {
                    insertProductImg(product,image);
                } catch (Exception e) {
                    throw new ProductException("存储缩略图失败");
                }
            }
            //插入商品
            try {
                int num = mapper.insertProduct(product);
                if(num <= 0){
                    throw new ProductException("添加商品失败");
                }
            } catch (Exception e) {
                throw new ProductException("errorMsg:"+e.getMessage());
            }

            //处理详情图
            if(imageHolderList!=null && imageHolderList.size() > 0){
                insertProductImgList(product,imageHolderList);
            }
            return new ProductExecution(ProductEnum.SUCCESS,product);
        }
        return new ProductExecution(ProductEnum.INNER_ERROR);
    }

    public ProductExecution deleteProduct(Integer productId) {
        try {
            int num = mapper.deleteProductByProductId(productId);
            if(num <= 0){
                return new ProductExecution(ProductEnum.ERROR);
            }else {
                return new ProductExecution(ProductEnum.SUCCESS);
            }
        } catch (Exception e) {
            throw new ProductException("errorMsg:"+e.getMessage());
        }
    }

    //获取商品缩略图存储地址
    private void insertProductImg(Product product,ImageHolder imageHolder){
        String path = PathUtils.getShopImagePath(product.getShop().getShopId());
        String realPath = ImageUtils.generateThumbnail(imageHolder,path);
        product.setImgAddress(realPath);
    }

    //处理商品详情图
    private void insertProductImgList(Product product,List<ImageHolder> imageHolderList){
        String path = PathUtils.getShopImagePath(product.getShop().getShopId());
        List<ProductImg> list = new ArrayList<ProductImg>();
        for(ImageHolder imageHolder:imageHolderList){
            String imgAddress = ImageUtils.generateNormalImage(imageHolder,path);
            ProductImg productImg = new ProductImg(null,imgAddress,
                    null,null,new Date(),product.getProductId());
            list.add(productImg);
        }

        if(list.size()>0){
            try {
                int num = imgMapper.batchInsertProductImg(list);
                if(num <= 0){
                    throw new ProductImgException("添加商品详情图失败");
                }
            } catch (Exception e) {
                throw new ProductImgException("添加商品详情图发生错误");
            }
        }
    }
}
