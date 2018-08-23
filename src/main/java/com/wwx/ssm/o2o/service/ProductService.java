package com.wwx.ssm.o2o.service;

import com.wwx.ssm.o2o.bean.ImageHolder;
import com.wwx.ssm.o2o.entity.Product;
import com.wwx.ssm.o2o.execution.ProductExecution;

import java.util.List;

public interface ProductService {

    /**
     *    通过店铺id获取全部商品
     * @param shopId
     * @return
     */
    ProductExecution getProductList(Integer shopId);

    /**
     *    添加商品
     *
     * @param product 商品信息
     * @param image  缩略图 ：输入流及图片名
     * @param imageList 详细图：输入流及图片名
     * @return
     */
    ProductExecution addProduct(Product product, ImageHolder image, List<ImageHolder> imageList);

    /**
     *
     *   通过商品id删除商品
     * @param productId
     * @return
     */
    ProductExecution deleteProduct(Integer productId);
}
