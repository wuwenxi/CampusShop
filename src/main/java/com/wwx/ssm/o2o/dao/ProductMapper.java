package com.wwx.ssm.o2o.dao;


import com.wwx.ssm.o2o.entity.Product;

import java.util.List;

public interface ProductMapper {

    /**
     *
     *      根据店铺id获取商品信息
     * @param shopId
     * @return
     */
    List<Product> queryProductList(Integer shopId);

    /**
     *    添加商品信息
     * @param product
     * @return
     */
    int insertProduct(Product product);

    /**
     *
     *   根据商品id删除商品
     * @param productId
     * @return
     */
    int deleteProductByProductId(Integer productId);

}