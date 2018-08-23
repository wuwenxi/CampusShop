package com.wwx.ssm.o2o.service;

import com.wwx.ssm.o2o.entity.ProductImg;
import com.wwx.ssm.o2o.execution.ProductImgExecution;

import java.util.List;

public interface ProductImgService {
    /**
     *
     *    通过商品id获取商品详细图片
     * @param productId
     * @return
     */
    ProductImgExecution getProductImgList(Integer productId);

    /**
     *
     *    批量添加商品详细图片
     * @param productImgList
     * @return
     */
    ProductImgExecution batchAddProductImg(List<ProductImg> productImgList);

    /**
     *
     *    通过商品id删除商品详细图片
     * @param productId
     * @return
     */
    ProductImgExecution deleteProductImgByProductId(Integer productId);
}
