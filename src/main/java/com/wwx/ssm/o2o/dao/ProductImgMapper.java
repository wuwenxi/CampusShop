package com.wwx.ssm.o2o.dao;


import com.wwx.ssm.o2o.entity.ProductImg;

import java.util.List;

public interface ProductImgMapper {

    /**
     *    根据商品id获取商品详细图片
     * @param productId
     * @return
     */
    List<ProductImg> queryProductImgList(Integer productId);

    /**
     *    批量插入商品的详细图片
     * @param productImgList
     * @return
     */
    int batchInsertProductImg(List<ProductImg> productImgList);

    /**
     *
     *     根据商品id删除商品详细图片
     * @param productId
     * @return
     */
    int deleteProductImgByProductId(Integer productId);
}