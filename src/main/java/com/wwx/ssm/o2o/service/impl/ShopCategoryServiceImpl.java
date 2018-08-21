package com.wwx.ssm.o2o.service.impl;

import com.wwx.ssm.o2o.dao.ShopCategoryMapper;
import com.wwx.ssm.o2o.entity.ShopCategory;
import com.wwx.ssm.o2o.service.ShopCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopCategoryServiceImpl implements ShopCategoryService {

    @Autowired
    ShopCategoryMapper mapper;

    public List<ShopCategory> getShopCategoryList(ShopCategory shopCategory) {
        return mapper.queryForListShopCategory(shopCategory);
    }
}
