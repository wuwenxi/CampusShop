package com.wwx.ssm.o2o.controller.shopAdmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/shop")
@Controller
public class ShopAdminController {

    @RequestMapping(value = "/shopOperation",method = RequestMethod.GET)
    public String ShopOperation(){
        return "shop/shopOperation";
    }

    @RequestMapping(value = "/shopList",method = RequestMethod.GET)
    public String ShopList(){
        return "shop/shopList";
    }

    @RequestMapping(value = "/shopManagement",method = RequestMethod.GET)
    public String ShopManagement(){
        return "shop/shopManagement";
    }

    @RequestMapping(value = "/productCategoryManage",method = RequestMethod.GET)
    public String ProductCategoryManagement(){
        return "shop/productCategoryManagement";
    }

    @RequestMapping(value = "/productManage",method = RequestMethod.GET)
    public String ProductManagement(){
        return "shop/productManagement";
    }

    @RequestMapping(value = "/productEdit",method = RequestMethod.GET)
    public String ProductEdit(){
        return "shop/productEdit";
    }
}
