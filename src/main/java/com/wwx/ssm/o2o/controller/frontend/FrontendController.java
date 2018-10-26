package com.wwx.ssm.o2o.controller.frontend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/frontend")
public class FrontendController {

    @RequestMapping("/index")
    public String MainIndex(){
        return "frontend/index";
    }

    @RequestMapping("/shopList")
    public String ShopList(){
        return "frontend/shopList";
    }

    @RequestMapping("/shopDetail")
    public String ShopDetail(){
        return "frontend/shopDetail";
    }

    @RequestMapping("/productDetail")
    public String ProductDetail(){
        return "frontend/productDetail";
    }
}
