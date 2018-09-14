package com.wwx.ssm.o2o.controller.frontend;

import com.wwx.ssm.o2o.bean.Msg;
import com.wwx.ssm.o2o.entity.Area;
import com.wwx.ssm.o2o.entity.Shop;
import com.wwx.ssm.o2o.entity.ShopCategory;
import com.wwx.ssm.o2o.enums.ShopStateEnum;
import com.wwx.ssm.o2o.execution.ShopExecution;
import com.wwx.ssm.o2o.service.AreaService;
import com.wwx.ssm.o2o.service.ShopCategoryService;
import com.wwx.ssm.o2o.service.ShopService;
import com.wwx.ssm.o2o.utils.HttpServletRequestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 *    商品展示
 */
@Controller
@RequestMapping("/frontend")
public class ShopListController {

    private Map<String,Object> map = new HashMap<String, Object>();

    @Autowired
    ShopCategoryService shopCategoryService;

    @Autowired
    ShopService shopService;

    @Autowired
    AreaService areaService;
    /**
     *
     *    获取商品展示时  获取商品类别和区域信息
     * @return
     */
    @RequestMapping(value = "/shopListInitInfo/{parentId}",method = RequestMethod.GET)
    @ResponseBody
    public Msg ShopListInitInfo(@PathVariable Integer parentId){
        //获取商品类别   获取一级类别或二级类别
        List<ShopCategory> shopCategoryList = new ArrayList<ShopCategory>();
        if(parentId > 0 ){
            try {
                ShopCategory shopCategory = new ShopCategory();
                ShopCategory parentCategory = new ShopCategory();
                //获取一级类别下的子类别
                parentCategory.setShopCategoryId(parentId);
                shopCategory.setParent(parentCategory);
                shopCategoryList = shopCategoryService.getShopCategoryList(shopCategory);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            try {
                shopCategoryList = shopCategoryService.getShopCategoryList(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        map.put("shopCategoryList",shopCategoryList);
        //获取区域信息
        List<Area> areaList = new ArrayList<Area>();
        try {
            areaList = areaService.getAreaList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("areaList",areaList);

        return Msg.success().add("map",map);
    }

    /**
     *
     *         根据条件查询店铺列表
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getShopList",method = RequestMethod.GET)
    public Msg getShopList(HttpServletRequest request){
        //获取起始页
        Integer pageIndex = HttpServletRequestUtils.getInt(request,"pageIndex");
        //获取页面大小
        Integer pageSize = HttpServletRequestUtils.getInt(request,"pageSize");

        if((pageIndex > -1) && (pageSize > -1)){
            //根据条件查询
            //获取一级类别id
            Integer parentId = HttpServletRequestUtils.getInt(request,"parentId");
            //获取地区id
            Integer areaId = HttpServletRequestUtils.getInt(request,"areaId");
            //获取店铺类别id
            Integer shopCategoryId = HttpServletRequestUtils.getInt(request,"shopCategoryId");
            //获取店铺名
            String shopName = HttpServletRequestUtils.getString(request,"shopName");
            //将条件拼装成shop对象
            Shop shop = compactShop(parentId,areaId,shopCategoryId,shopName);
            //查询符合条件的店铺列表
            ShopExecution execution = shopService.getShopList(shop,pageIndex,pageSize);
            if(execution.getState().equals(ShopStateEnum.SUCCESS.getState())){
                map.put("shopList",execution.getShops());
                map.put("count",execution.getCount());
            }else {
                return Msg.fail();
            }
            return Msg.success().add("map",map);
        }else {
            return Msg.fail();
        }
    }

    private Shop compactShop(Integer parentId,Integer areaId,Integer shopCategoryId,String shopName){
        Shop shop = new Shop();
        if(parentId > 0){
            ShopCategory shopCategory = new ShopCategory();
            ShopCategory parentCategory = new ShopCategory();
            parentCategory.setShopCategoryId(parentId);
            shopCategory.setParent(parentCategory);
            shop.setShopCategory(shopCategory);
        }
        if(shopCategoryId > 0){
            ShopCategory shopCategory = new ShopCategory();
            shopCategory.setShopCategoryId(shopCategoryId);
            shop.setShopCategory(shopCategory);
        }
        if(areaId > 0){
            Area area = new Area();
            area.setAreaId(areaId);
            shop.setArea(area);
        }
        if(shopName!=null && !shopName.equals("")){
            shop.setShopName(shopName);
        }
        //检索状态为可用
        shop.setEnableStatus(1);
        return shop;
    }
}
