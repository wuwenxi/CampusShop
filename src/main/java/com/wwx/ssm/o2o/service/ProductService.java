package com.wwx.ssm.o2o.service;

import com.wwx.ssm.o2o.bean.ImageHolder;
import com.wwx.ssm.o2o.entity.Product;
import com.wwx.ssm.o2o.execution.ProductExecution;

import java.util.List;

public interface ProductService {

    /**
     *
     *    获取商品信心
     * @param productId
     * @return
     */
    ProductExecution getProductById(Integer productId);

    /**
     *
     *        模糊查询  获取全部商品
     * @param product
     * @param pageSize
     * @param pageIndex
     * @return
     */
    ProductExecution getProductList(Product product,Integer pageIndex,Integer pageSize);

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
     *  更新店铺信息
     * @param product
     * @param image
     * @param imageList
     * @return
     */
    ProductExecution modifyProduct(Product product,ImageHolder image,List<ImageHolder> imageList);
    /**
     *
     *   通过商品id删除商品
     * @param productId
     * @return
     */
    ProductExecution deleteProduct(Integer productId);
}
