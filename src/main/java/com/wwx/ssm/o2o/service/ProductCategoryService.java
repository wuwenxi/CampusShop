package com.wwx.ssm.o2o.service;

import com.wwx.ssm.o2o.entity.ProductCategory;

import java.util.List;

public interface ProductCategoryService {

    List<ProductCategory> getProductCategoryList(Integer shopId);

    int addProductCategory(List<ProductCategory> category);

    int deleteProductCategoryById(Integer id);
}
