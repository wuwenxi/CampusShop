package com.wwx.ssm.o2o.dao;


import com.wwx.ssm.o2o.entity.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductMapper {

    /**
     *
     *    根据商品id获取商品信息
     * @param productId
     * @return
     */
    Product queryProductById(Integer productId);

    /**
     *
     *      通过模糊查询 获取商品信息
     * @param product
     * @return
     */
    List<Product> queryProductList(@Param("product") Product product,@Param("rowIndex")Integer rowIndex,
                                   @Param("pageIndex")Integer pageIndex);

    /**
     *
     *  获取全部商品
     * @param product
     * @return
     */
    int queryProduct(@Param("product")Product product);

    /**
     *    添加商品信息
     * @param product
     * @return
     */
    int insertProduct(Product product);

    /**
     *
     *   更新商品信息
     * @param product
     * @return
     */
    int updateProduct(Product product);

    int updateProductCategoryToNull(Integer productCategoryId);

    /**
     *
     *   根据商品id删除商品
     * @param productId
     * @return
     */
    int deleteProductByProductId(Integer productId);

}