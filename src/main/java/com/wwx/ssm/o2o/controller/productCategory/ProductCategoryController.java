package com.wwx.ssm.o2o.controller.productCategory;

import com.wwx.ssm.o2o.entity.Msg;
import com.wwx.ssm.o2o.entity.ProductCategory;
import com.wwx.ssm.o2o.entity.Shop;
import com.wwx.ssm.o2o.service.ProductCategoryService;
import com.wwx.ssm.o2o.utils.HttpServletRequestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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
     *                获取店铺类型列表
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
}
