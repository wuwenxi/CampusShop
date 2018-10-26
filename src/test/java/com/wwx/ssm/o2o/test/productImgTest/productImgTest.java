package com.wwx.ssm.o2o.test.productImgTest;

import com.wwx.ssm.o2o.dao.ProductImgMapper;
import com.wwx.ssm.o2o.entity.ProductImg;
import com.wwx.ssm.o2o.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class productImgTest extends BaseTest {

    @Autowired
    ProductImgMapper mapper;

    @Test
    public void test01(){
        List<ProductImg> list = new ArrayList<ProductImg>();
        list.add(new ProductImg());
        int num = mapper.batchInsertProductImg(list);
        if(num <= 0){
            System.out.println("添加失败");
        }else {
            System.out.println("添加成功");
        }
    }

    @Test
    public void test02(){
        List<ProductImg> list = mapper.queryProductImgList(1);
        for(ProductImg productImg:list){
            System.out.println(productImg);
        }
    }

    @Test
    public void test03(){
        List<ProductImg> list = mapper.queryAllProductImgList();
        for (ProductImg productImg:list){
            if(productImg.getImgAddress()!=null){
                if(productImg.getImgAddress().contains("\\")){
                    productImg.getImgAddress().replace("\\","/");
                }
            }
        }
    }
}
