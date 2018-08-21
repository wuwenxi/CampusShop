package com.wwx.ssm.o2o.test.productCategoryTest;

import com.wwx.ssm.o2o.entity.ProductCategory;
import com.wwx.ssm.o2o.service.ProductCategoryService;
import com.wwx.ssm.o2o.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class ProductCategoryTest extends BaseTest {


    @Autowired
    ProductCategoryService service;

    @Test
    public void test01(){
        int num = service.addProductCategory
                (new ProductCategory(null,"美食",90,new Date(),1));
        if(num <= 0){
            System.out.println("添加失败");
        }else {
            System.out.println("添加成功");
        }
    }

    @Test
    public void test02(){
        List<ProductCategory> list = service.getProductCategoryList(1);
        if(list.size()>0){
            System.out.println("店铺类别数：" + list.size());
            for (ProductCategory category : list){
                System.out.println(category);
            }
        }
    }
}
