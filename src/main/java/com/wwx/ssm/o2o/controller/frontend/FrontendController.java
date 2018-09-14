package com.wwx.ssm.o2o.controller.frontend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
