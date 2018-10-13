package com.wwx.ssm.o2o.test.shop;

import com.wwx.ssm.o2o.bean.ImageHolder;
import com.wwx.ssm.o2o.dao.ShopMapper;
import com.wwx.ssm.o2o.entity.Area;
import com.wwx.ssm.o2o.entity.PersonInfo;
import com.wwx.ssm.o2o.entity.Shop;
import com.wwx.ssm.o2o.entity.ShopCategory;
import com.wwx.ssm.o2o.enums.ShopStateEnum;
import com.wwx.ssm.o2o.execution.ShopExecution;
import com.wwx.ssm.o2o.service.ShopService;
import com.wwx.ssm.o2o.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class ShopTest extends BaseTest {

    @Autowired
    ShopService shopService;

    @Autowired
    ShopMapper mapper;

    @Test
    public void testAddShop(){
        PersonInfo own = new PersonInfo();
        Area area = new Area();
        ShopCategory shopCategory = new ShopCategory();

        own.setUserId(1);
        area.setAreaId(1);
        shopCategory.setShopCategoryId(1);

        File image = new File("E:\\Spring\\img\\shuixianhua.jpg");
        Shop shop = new Shop(null,own,area,shopCategory,"老字号包子铺","包子铺",
                "德阳市黄许镇","15283840975",null,100,new Date(),new Date(),0,null);
        mapper.insertShop(shop);
    }

    @Test
    public void testQueryById(){
        Shop shop = shopService.getShopById(1);
        System.out.println(shop);
    }

    @Test
    public void modifyShop(){
        Shop shop = new Shop();
        shop.setShopId(1);
        shop.setLastEditTime(new Date());
        File file = new File("E:\\Spring\\img\\Desert.jpg");
        try {
            InputStream in = new FileInputStream(file);
            ImageHolder image = new ImageHolder("Desert.jpg",in);
            ShopExecution execution = shopService.modifyShop(shop,image);
            System.out.println(execution.getStateInfo());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void queryShopList(){
        Shop shop = new Shop();
        PersonInfo info = new PersonInfo();
        info.setUserId(1);
        shop.setOwner(info);
        List<Shop> shopList = mapper.queryShopList(shop,0,5);
        System.out.println(shopList.size());
        for (Shop s:shopList) {
            System.out.println(s);
        }

        int count = mapper.queryShopCount(shop);
        System.out.println(count);

        ShopCategory sc = new ShopCategory();
        sc.setShopCategoryId(1);
        shop.setShopCategory(sc);
        shopList = mapper.queryShopList(shop,0,1);
        count = mapper.queryShopCount(shop);
        System.out.println("店铺列表："+ shopList.size() + "，店铺总数："+count);
    }

    @Test
    public void testGetShopList(){
        Shop shop = new Shop();
        PersonInfo info = new PersonInfo();
        info.setUserId(1);
        shop.setOwner(info);
        ShopExecution sc = shopService.getShopList(shop,1,2);
        System.out.println(sc.getShops().size());
        System.out.println(sc.getCount());
    }

    @Test
    public void delete(){
        for (int i=1;i<40;i++){
            shopService.deleteShopById(i);
        }
    }

    @Test
    public void ShopListAndCount(){
        ShopCategory childCategory = new ShopCategory();
        ShopCategory parentCategory = new ShopCategory();
        parentCategory.setShopCategoryId(4);
        childCategory.setParent(parentCategory);
        Shop shop = new Shop();
        shop.setShopCategory(childCategory);
        int num = mapper.queryShopCount(shop);
        System.out.println(num);
        List<Shop> list = mapper.queryShopList(shop,0,100);
        for(Shop shop1 : list){
            System.out.println(shop1);
        }
    }

    @Test
    public void testQueryShop(){
        ShopCategory shopCategory = new ShopCategory();
        shopCategory.setShopCategoryId(1);
        ShopCategory parentCategory = new ShopCategory();
        parentCategory.setShopCategoryId(3);
        shopCategory.setParent(parentCategory);
        Shop shop = new Shop(null,null,null,shopCategory,"香",null,null,null,null
        ,null,null,null,1,null);
        ShopExecution execution = shopService.getShopList(shop,1,20);
        if(execution.getState().equals(ShopStateEnum.SUCCESS.getState())){
            System.out.println(execution.getCount());
            for (Shop shop1:execution.getShops()){
                System.out.println(shop1);
            }
        }else{
            System.out.println("操作失败");
        }
    }

}
