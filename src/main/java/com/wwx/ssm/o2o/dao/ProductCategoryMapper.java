package com.wwx.ssm.o2o.dao;


import com.wwx.ssm.o2o.entity.ProductCategory;

import java.util.List;

public interface ProductCategoryMapper {

    /**
     *  通过店铺id 查询店铺类别
     * @param shopId
     * @return
     */
    List<ProductCategory> queryProductCategoryList(Integer shopId);

    int insertProductCategory(ProductCategory category);
}