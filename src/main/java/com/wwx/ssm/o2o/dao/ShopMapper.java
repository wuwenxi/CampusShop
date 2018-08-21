package com.wwx.ssm.o2o.dao;

import com.wwx.ssm.o2o.entity.Shop;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShopMapper {
    /**
     *   获取店铺总数
     * @param shop
     * @return
     */
    int queryShopCount(@Param("shop")Shop shop);
    /**
     *      分页查询，查询条件：店铺类别、地区、店铺名（模糊查询）、状态、用户
     * @param shop
     * @param rowIndex     查询开始的起始位置
     * @param pageSize     查询的总记录数
     * @return
     */
    List<Shop> queryShopList(@Param("shop")Shop shop,@Param("rowIndex")Integer rowIndex,@Param("pageSize")Integer pageSize);

    /**
     *          通过店铺id来查询
     *
     * @param id
     * @return
     */
    Shop queryShopById(Integer id);

    /**
     * 新增店铺
     * @param shop
     * @return
     */
    int insertShop(Shop shop);

    /**
     *  更新店铺
     * @param shop
     * @return
     */
    int updateShop(Shop shop);

    int deleteShopById(Integer id);

}