package com.wwx.ssm.o2o.controller.shopAdmin;

import com.wwx.ssm.o2o.bean.Msg;
import com.wwx.ssm.o2o.entity.ProductCategory;
import com.wwx.ssm.o2o.entity.Shop;
import com.wwx.ssm.o2o.enums.ProductCategoryEnum;
import com.wwx.ssm.o2o.exception.ProductCategoryException;
import com.wwx.ssm.o2o.execution.ProductCategoryExecution;
import com.wwx.ssm.o2o.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/shopAdmin")
public class ProductCategoryController {

    @Autowired
    ProductCategoryService service;

    private Map<String,Object> map = new HashMap<String, Object>();

    /**
     *                获取商品类别列表
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getProductCategoryList",method = RequestMethod.GET)
    public Msg getProductCategoryList(HttpServletRequest request){
        //从session中获取当前店铺信息
        Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");

        if(currentShop != null && currentShop.getShopId()!=null){
            List<ProductCategory> list;
            try {
                //获取列表
                list = service.getProductCategoryList(currentShop.getShopId());
            } catch (Exception e) {
                e.printStackTrace();
                return Msg.fail();
            }
            if(list!=null){
                map.put("list",list);
                return Msg.success().add("map",map);
            }
        }
        return Msg.fail();
    }

    /**
     *
     *   添加商品类别
     * @param categoryList
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addProductCategory",method = RequestMethod.POST)
    public Msg addProductCategory(@RequestBody List<ProductCategory> categoryList, HttpServletRequest request){
        //从session中获取店铺信息
        Shop shop = (Shop)request.getSession().getAttribute("currentShop");
        //为每个商品类别附上店铺id及创建时间
        for(ProductCategory category:categoryList){
            category.setShopId(shop.getShopId());
            category.setCreateTime(new Date());
        }

        //进行批量添加
        if(categoryList.size()>0){
            try {
                ProductCategoryExecution execution = service.addProductCategory(categoryList);
                if(execution.getStatus().equals(ProductCategoryEnum.SUCCESS.getStatus())){
                    return Msg.success();
                }else {
                    return Msg.fail();
                }
            } catch (Exception e) {
                throw new ProductCategoryException("errorMsg:"+ e.getMessage());
            }
        }

        return Msg.fail();
    }

    /**
     *
     *   删除商品类别
     * @param productCategoryId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteProductCategory/{id}",method = RequestMethod.DELETE)
    public Msg deleteProductCategory(@PathVariable("id")Integer productCategoryId){
        if(productCategoryId <= 0){
            return Msg.fail();
        }

        try {
            ProductCategoryExecution execution = service.deleteProductCategory(productCategoryId);
            if(execution.getStatus().equals(ProductCategoryEnum.SUCCESS.getStatus())){
                return Msg.success();
            }else {
                return Msg.fail();
            }
        } catch (Exception e) {
            throw new ProductCategoryException("errorMsg:"+e.getMessage());
        }
    }
}
