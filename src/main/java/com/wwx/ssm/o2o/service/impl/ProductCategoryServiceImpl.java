package com.wwx.ssm.o2o.service.impl;

import com.wwx.ssm.o2o.dao.ProductCategoryMapper;
import com.wwx.ssm.o2o.entity.ProductCategory;
import com.wwx.ssm.o2o.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    ProductCategoryMapper mapper;

    public List<ProductCategory> getProductCategoryList(Integer shopId) {
        return mapper.queryProductCategoryList(shopId);
    }

    public int addProductCategory(List<ProductCategory> category) {
        int num = -1;
        try {
            num = mapper.batchInsertProductCategory(category);
            return num;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return num;
    }

    public int deleteProductCategoryById(Integer id) {
        int num = -1;
        try {
            num = mapper.deleteProductCategoryById(id);
            return num;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return num;
    }
}
