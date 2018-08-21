package com.wwx.ssm.o2o.test;

import com.wwx.ssm.o2o.dao.AreaMapper;
import com.wwx.ssm.o2o.dao.ShopMapper;
import com.wwx.ssm.o2o.entity.Area;
import com.wwx.ssm.o2o.entity.PersonInfo;
import com.wwx.ssm.o2o.entity.Shop;
import com.wwx.ssm.o2o.entity.ShopCategory;
import com.wwx.ssm.o2o.service.AreaService;
import com.wwx.ssm.o2o.utils.ValidateUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml"})
public class SpringTest {

    @Autowired
    AreaMapper areaMapper;

    @Autowired
    SqlSession sqlSession;

    @Autowired
    AreaService service;

    @Test
    public void query(){
        List<Area> list = service.getAreaList();
        System.out.println(list.size());
        for (Area area:list) {
            System.out.println(area);
        }
    }

    @Autowired
    ShopMapper shopMapper;

    @Test
    public void testShop(){
        PersonInfo own = new PersonInfo();
        Area area = new Area();
        ShopCategory shopCategory = new ShopCategory();

        own.setUserId(1);
        area.setAreaId(1);
        shopCategory.setShopCategoryId(1);

        /*Shop shop = new Shop(30,own,null,null,"优乐美奶茶","优乐美奶茶","德阳市",null,null,
                80,null,new Date(),1,null);*/
        //shopMapper.insertShop(shop);

       // shopMapper.updateShop(shop);
    }

    @Test
    public void test(){
        File file =new File("F:\\新建文件夹\\4068.doc");
        System.out.println(ValidateUtil.checkShopImage(file.getName()));
    }

}
