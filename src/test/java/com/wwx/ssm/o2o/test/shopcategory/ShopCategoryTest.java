package com.wwx.ssm.o2o.test.shopcategory;

import com.wwx.ssm.o2o.dao.ShopCategoryMapper;
import com.wwx.ssm.o2o.entity.ShopCategory;
import com.wwx.ssm.o2o.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ShopCategoryTest extends BaseTest {

    @Autowired
    ShopCategoryMapper mapper;

    @Test
    public void test(){
        //List<ShopCategory> list = mapper.queryForListShopCategory();
        List<ShopCategory> list = mapper.queryForListShopCategory(new ShopCategory());
        System.out.println(list.size());
        for (ShopCategory category:list) {
            System.out.println(category);
        }
    }
}
