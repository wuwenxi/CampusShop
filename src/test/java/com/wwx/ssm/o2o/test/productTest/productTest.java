package com.wwx.ssm.o2o.test.productTest;

import com.wwx.ssm.o2o.dao.ProductMapper;
import com.wwx.ssm.o2o.entity.Product;
import com.wwx.ssm.o2o.entity.ProductCategory;
import com.wwx.ssm.o2o.entity.ProductImg;
import com.wwx.ssm.o2o.entity.Shop;
import com.wwx.ssm.o2o.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class productTest extends BaseTest {

    @Autowired
    ProductMapper mapper;

    @Test
    public void test01(){
        ProductCategory category = new ProductCategory();
        category.setProductCategoryId(16);
        Shop shop = new Shop();
        shop.setShopId(1);
        int num = mapper.insertProduct(
                new Product(null,"珍珠奶茶","珍珠奶茶，大杯，中杯",null,"10.00",
                        "10.00",100, new Date(),new Date(),
                        1,null,category, shop));
        if(num <= 0){
            System.out.println("添加失败");
        }else {
            System.out.println("添加成功");
        }
    }

    @Test
    public void test02(){
        List<Product> list = mapper.queryProductList(1);
        for(Product product:list){
            System.out.println(product);
        }
    }
}