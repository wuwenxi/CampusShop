package com.wwx.ssm.o2o.controller;

import com.wwx.ssm.o2o.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AreaController {

    @Autowired
    AreaService service;

    @RequestMapping("/listArea")
    public String getList(){
        return "list";
    }
}
