package com.wwx.ssm.o2o.service;

import com.wwx.ssm.o2o.bean.ImageHolder;
import com.wwx.ssm.o2o.entity.Shop;
import com.wwx.ssm.o2o.exception.ShopException;
import com.wwx.ssm.o2o.execution.ShopExecution;


public interface ShopService {
    /**
     *
     *     添加商铺
     * @param shop
     * @param image  封装了文件的输入流及文件名
     * @return
     */
    ShopExecution addShop(Shop shop,ImageHolder image);

    void updateShop(Shop shop);

    void deleteShopById(Integer id);

    /**
     *         获取单个店铺信息
     *
     * @param id
     * @return
     */
    Shop getShopById(Integer id);

    /**
     *
     *  更新店铺   包括处理图片
     * @param shop 商铺
     * @param image 封装了文件的输入流 及 文件名
     * @return
     * @throws ShopException
     */
    ShopExecution modifyShop(Shop shop,ImageHolder image) throws ShopException;

    /**
     *    获取店铺类别   通过ShopExecution整合店铺信息
     * @param shop
     * @param pageIndex  起始页码 前端页面用页数，dao层用行数  需先进行转换
     * @param pageSize  总条数
     * @return
     */
    ShopExecution getShopList(Shop shop,Integer pageIndex,Integer pageSize);
}
