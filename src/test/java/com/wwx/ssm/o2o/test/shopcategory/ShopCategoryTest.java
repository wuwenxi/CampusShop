package com.wwx.ssm.o2o.test.shopcategory;

import com.wwx.ssm.o2o.bean.ImageHolder;
import com.wwx.ssm.o2o.dao.ShopCategoryMapper;
import com.wwx.ssm.o2o.entity.ShopCategory;
import com.wwx.ssm.o2o.test.BaseTest;
import com.wwx.ssm.o2o.utils.ImageUtils;
import com.wwx.ssm.o2o.utils.PathUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;

public class ShopCategoryTest extends BaseTest {

    @Autowired
    ShopCategoryMapper mapper;

    @Test
    public void test(){
        ShopCategory shopCategory = new ShopCategory();
        ShopCategory parentCategory = new ShopCategory();
        parentCategory.setShopCategoryId(4);
        shopCategory.setParent(parentCategory);
        List<ShopCategory> list = mapper.queryForListShopCategory(shopCategory);
        System.out.println(list.size());
        for (ShopCategory category:list) {
            System.out.println(category);
        }
    }

    @Test
    public void testAdd(){

        File file = new File(
                "D:\\Project\\CampusShop\\image\\frontend\\shopcategory\\2017061223281361578.png");
        FileInputStream in = null;
        try {
           in = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ImageHolder image = new ImageHolder(file.getName(),in);

        String imgAddress = ImageUtils.generateShopCategoryImage(image,PathUtils.getShopCategoryImagePath());
        ShopCategory shopCategory = new ShopCategory(null,"租赁市场","租赁市场",
                imgAddress,100,new Date(),null,null);
        int num = mapper.addShopCategory(shopCategory);
        if(num < 0){
            System.out.println("操作失败");
        }else {
            System.out.println("操作成功");
        }
    }

    @Test
    public void testUpdate(){
        File file = new File("D:\\Project\\CampusShop\\image\\upload\\item\\shopcategory\\2018090718232513391.png");
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ImageHolder image = new ImageHolder(file.getName(),in);

        String imgAddress = ImageUtils.generateShopCategoryImage(image,PathUtils.getShopCategoryImagePath());
        ShopCategory shopCategory = new ShopCategory(8,null,null,
                imgAddress,100,null,new Date(),null);
        int num = mapper.updateShopCategoryById(shopCategory);
        if(num < 0){
            System.out.println("操作失败");
        }else {
            System.out.println("操作成功");
        }
    }

    @Test
    public void test01(){
        String separator = System.getProperty("file.separator");
        System.out.println(separator);
        List<ShopCategory> list = mapper.queryForListShopCategory(null);
        for(ShopCategory shopCategory:list){
            if(shopCategory.getShopCategoryImg()!=null){
                String address = shopCategory.getShopCategoryImg();
                System.out.println(address);
                address = address.replace(separator,"/");
                System.out.println(address);
                shopCategory.setShopCategoryImg(address);
                mapper.updateShopCategoryById(shopCategory);
            }
        }
    }
}