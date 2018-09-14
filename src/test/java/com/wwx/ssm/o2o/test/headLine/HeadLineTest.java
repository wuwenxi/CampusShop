package com.wwx.ssm.o2o.test.headLine;

import com.wwx.ssm.o2o.bean.ImageHolder;
import com.wwx.ssm.o2o.dao.HeadLineMapper;
import com.wwx.ssm.o2o.entity.HeadLine;
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

public class HeadLineTest extends BaseTest {

    @Autowired
    HeadLineMapper mapper;

    @Test
    public void testQueryHeadLine(){
        HeadLine headLine = new HeadLine();
        headLine.setEnableStatus(1);
        List<HeadLine> lineList = mapper.queryHeadLineList(headLine);
        System.out.println(lineList.size());
        HeadLine headLine1 = mapper.queryHeadLineById(1);
        System.out.println(headLine1);
    }

    @Test
    public void testAdd(){
        File file = new File("D:\\Project\\CampusShop\\image\\frontend\\headtitle\\2017061320400198256.jpg");
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ImageHolder image = new ImageHolder(file.getName(),in);

        String lineImg = ImageUtils.generateHeadLineImage(image,PathUtils.getHeadLineImagePath());
        HeadLine headLine = new HeadLine(null,"链接4","http://www.baidu.com",
                lineImg,100,0,new Date(),null);
        int num = mapper.addHeadLine(headLine);
        if(num < 0){
            System.out.println("操作失败");
        }else {
            System.out.println("操作成功");
        }
    }

    @Test
    public void testUpdate(){
        File file = new File("D:\\Project\\CampusShop\\image\\upload\\item\\headtitle\\2018090720435211234.jpg");
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ImageHolder image = new ImageHolder(file.getName(),in);

        String lineImg = ImageUtils.generateHeadLineImage(image,PathUtils.getHeadLineImagePath());
        HeadLine headLine = new HeadLine(4,null,"http://www.baidu.com",
                lineImg,100,1,null,new Date());
        int num = mapper.updateHeadLine(headLine);
        if(num < 0){
            System.out.println("操作失败");
        }else {
            System.out.println("操作成功");
        }
    }

    @Test
    public void testDel(){

    }
}
