package com.wwx.ssm.o2o.controller.Shop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/shop")
@Controller
public class ShopAdminController {

    @RequestMapping(value = "/shopOperation",method = RequestMethod.GET)
    public String ShopOperation(){
        return "shopOperation";
    }

    @RequestMapping(value = "/shopList",method = RequestMethod.GET)
    public String ShopList(){
        return "shopList";
    }
    @RequestMapping(value = "/shopManagement",method = RequestMethod.GET)
    public String ShopManagement(){
        return "shopManagement";
    }
}
