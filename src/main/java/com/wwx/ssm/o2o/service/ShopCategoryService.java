package com.wwx.ssm.o2o.service;

import com.wwx.ssm.o2o.entity.ShopCategory;

import java.util.List;

public interface ShopCategoryService {

    List<ShopCategory> getShopCategoryList(ShopCategory shopCategory);
}
