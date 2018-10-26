package com.wwx.ssm.o2o.controller.frontend;

import com.wwx.ssm.o2o.bean.Msg;
import com.wwx.ssm.o2o.entity.Product;
import com.wwx.ssm.o2o.enums.ProductEnum;
import com.wwx.ssm.o2o.execution.ProductExecution;
import com.wwx.ssm.o2o.service.ProductService;
import com.wwx.ssm.o2o.utils.HttpServletRequestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/frontend")
public class ProductDetailController {

    @Autowired
    ProductService productService;

    private Map<String,Object> map = new HashMap<String, Object>();

    @RequestMapping(value = "/showProductDetail/{productId}",method = RequestMethod.GET)
    @ResponseBody
    public Msg getProduct(@PathVariable Integer productId){
        //Integer productId = HttpServletRequestUtils.getInt(request,"productId");
        if(productId>0){
            ProductExecution execution = productService.getProductById(productId);
            if(execution.getState().equals(ProductEnum.SUCCESS.getStatus())){
                map.put("product",execution.getProduct());
                return Msg.success().add("map",map);
            }else {
                return Msg.success().add("message","获取商品信息失败");
            }
        }else {
            return Msg.success();
        }

    }

}
