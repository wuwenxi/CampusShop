package com.wwx.ssm.o2o.dao;

import com.wwx.ssm.o2o.entity.ShopCategory;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShopCategoryMapper {

    List<ShopCategory> queryForListShopCategory(@Param("shopCategory") ShopCategory shopCategory);

    int addShopCategory(ShopCategory shopCategory);

    int updateShopCategoryById(ShopCategory shopCategory);

    int deleteShopCategoryId(Integer shopCategoryId);
}