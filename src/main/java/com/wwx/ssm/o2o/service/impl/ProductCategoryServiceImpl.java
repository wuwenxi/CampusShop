package com.wwx.ssm.o2o.service.impl;

import com.wwx.ssm.o2o.dao.ProductCategoryMapper;
import com.wwx.ssm.o2o.dao.ProductMapper;
import com.wwx.ssm.o2o.entity.ProductCategory;
import com.wwx.ssm.o2o.enums.ProductCategoryEnum;
import com.wwx.ssm.o2o.exception.ProductCategoryException;
import com.wwx.ssm.o2o.execution.ProductCategoryExecution;
import com.wwx.ssm.o2o.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    ProductCategoryMapper mapper;

    @Autowired
    ProductMapper productMapper;

    public List<ProductCategory> getProductCategoryList(Integer shopId) {
        return mapper.queryProductCategoryList(shopId);
    }

    public ProductCategoryExecution addProductCategory(List<ProductCategory> category) {
        if (category!=null && category.size()>0){
            try {
                int num = mapper.batchInsertProductCategory(category);
                if (num <= 0){
                    throw new ProductCategoryException("添加失败");
                }else {
                    return new ProductCategoryExecution(ProductCategoryEnum.SUCCESS,category);
                }
            } catch (Exception e) {
                throw new ProductCategoryException("errorMsg:" + e.getMessage());
            }
        }else {
            return new ProductCategoryExecution(ProductCategoryEnum.EMPTY_LIST);
        }
    }

    public ProductCategoryExecution deleteProductCategory(Integer productCategoryId) {

        //删除商品类别前  商品中的商品类别致为空  解除商品与商品类别的关联
        try {
            int num = productMapper.updateProductCategoryToNull(productCategoryId);
            if(num < 0){
                throw new ProductCategoryException("商品类别更新失败");
            }
        } catch (Exception e) {
            throw new ProductCategoryException(e.getMessage());
        }

        try {
            int num = mapper.deleteProductCategory(productCategoryId);
            if(num <= 0 ){
                throw new ProductCategoryException("操作失败");
            }else {
                return new ProductCategoryExecution(ProductCategoryEnum.SUCCESS);
            }
        } catch (Exception e) {
            throw new ProductCategoryException("errorMsg:"+e.getMessage());
        }
    }
}
