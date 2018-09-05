package com.wwx.ssm.o2o.controller.fontend;

import com.wwx.ssm.o2o.bean.Msg;
import com.wwx.ssm.o2o.entity.HeadLine;
import com.wwx.ssm.o2o.entity.ShopCategory;
import com.wwx.ssm.o2o.service.HeadLineService;
import com.wwx.ssm.o2o.service.ShopCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 *   页面转发器
 */
@Controller
@RequestMapping("/frontend")
public class MainPageController {

    @Autowired
    private ShopCategoryService shopCategoryService;

    @Autowired
    private HeadLineService headLineService;

    private Map<String,Object> map = new HashMap<String, Object>();

    /**
     *
     *           获取首页头条展示信息和一级店铺类别
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/mainPageListInfo",method = RequestMethod.GET)
    public Msg mainPageListInfo(){

        List<ShopCategory> shopCategoryList;
        try {
            //获取一级店铺类别（parentId为空的类别）
            shopCategoryList = shopCategoryService.getShopCategoryList(null);
            map.put("shopCategoryList",shopCategoryList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<HeadLine> headLineList;
        try {
            HeadLine headLine = new HeadLine();
            headLine.setEnableStatus(1);
            headLineList = headLineService.getHeadLineList(headLine);
            map.put("headLineList",headLineList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Msg.success().add("map",map);
    }
}
