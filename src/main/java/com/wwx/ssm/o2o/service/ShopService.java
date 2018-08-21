package com.wwx.ssm.o2o.service;

import com.wwx.ssm.o2o.entity.Shop;
import com.wwx.ssm.o2o.exception.ShopException;
import com.wwx.ssm.o2o.execution.ShopExecution;
import org.apache.ibatis.annotations.Param;

import java.io.InputStream;
import java.util.List;


public interface ShopService {

    /**
     *
     * @param shop
     * @param inputStream
     * @param fileName
     * @return
     */
    ShopExecution addShop(Shop shop, InputStream inputStream,String fileName);

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
     *          更新店铺   包括处理图片
     *
     * @param shop
     * @param in
     * @param fileName
     * @return
     */
    ShopExecution modifyShop(Shop shop,InputStream in,String fileName) throws ShopException;

    /**
     *    获取店铺类别   通过ShopExecution整合店铺信息
     * @param shop
     * @param pageIndex  起始页码 前端页面用页数，dao层用行数  需先进行转换
     * @param pageSize  总条数
     * @return
     */
    ShopExecution getShopList(Shop shop,Integer pageIndex,Integer pageSize);
}
