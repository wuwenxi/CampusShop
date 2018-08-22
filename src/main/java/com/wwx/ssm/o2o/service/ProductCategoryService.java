package com.wwx.ssm.o2o.service;

import com.wwx.ssm.o2o.entity.ProductCategory;
import com.wwx.ssm.o2o.execution.ProductCategoryExecution;

import java.util.List;

public interface ProductCategoryService {

    /**
     *      获取商铺类别
     * @param shopId
     * @return
     */
    List<ProductCategory> getProductCategoryList(Integer shopId);

    /**
     *
     *  添加商品类别
     * @param category
     * @return
     */
    ProductCategoryExecution addProductCategory(List<ProductCategory> category);

    /**
     *
     *  删除商品类别
     * @param id
     * @return
     */
    ProductCategoryExecution deleteProductCategoryById(Integer id);
}
