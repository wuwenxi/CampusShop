package com.wwx.ssm.o2o.controller.frontend;

import com.wwx.ssm.o2o.bean.Msg;
import com.wwx.ssm.o2o.entity.Product;
import com.wwx.ssm.o2o.entity.ProductCategory;
import com.wwx.ssm.o2o.entity.Shop;
import com.wwx.ssm.o2o.entity.ShopCategory;
import com.wwx.ssm.o2o.enums.ProductEnum;
import com.wwx.ssm.o2o.execution.ProductExecution;
import com.wwx.ssm.o2o.service.ProductCategoryService;
import com.wwx.ssm.o2o.service.ProductService;
import com.wwx.ssm.o2o.service.ShopCategoryService;
import com.wwx.ssm.o2o.service.ShopService;
import com.wwx.ssm.o2o.utils.HttpServletRequestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/frontend")
public class ShopDetailController {

    @Autowired
    ProductService productService;

    @Autowired
    ShopService shopService;

    @Autowired
    ProductCategoryService productCategoryService;

    private Map<String,Object> map = new HashMap<String, Object>();

    //展示店铺细节
    @RequestMapping(value = "/showShopDetail",method = RequestMethod.GET)
    @ResponseBody
    public Msg ShowShopDetail(HttpServletRequest request){
        //商铺信息，商品类别，
        Integer shopId = HttpServletRequestUtils.getInt(request,"shopId");
        if(shopId < 0){
            return Msg.fail();
        }
        try {
            Shop shop  = shopService.getShopById(shopId);
            List<ProductCategory> list = productCategoryService.getProductCategoryList(shopId);
            map.put("shop",shop);
            map.put("list",list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Msg.success().add("map",map);
    }

    //根据条件查询商品信息
    @ResponseBody
    @RequestMapping(value = "/listProductInfo",method = RequestMethod.GET)
    public Msg getProductInfo(HttpServletRequest request){

        Integer pageIndex = HttpServletRequestUtils.getInt(request,"pageIndex");

        Integer pageSize = HttpServletRequestUtils.getInt(request,"pageSize");

        Integer shopId = HttpServletRequestUtils.getInt(request,"shopId");

        if(pageIndex > 0 && pageSize > 0 && shopId > 0){
            Integer productCategoryId = HttpServletRequestUtils.getInt(request,"productCategoryId");

            String productName = HttpServletRequestUtils.getString(request,"productName");

            Product product = compactProduct(shopId,productCategoryId,productName);

            ProductExecution execution = productService.getProductList(product,pageIndex,pageSize);

            if(execution.getState().equals(ProductEnum.SUCCESS.getStatus())){
                map.put("list",execution.getProductList());
                map.put("count",execution.getCount());
                return Msg.success().add("map",map);
            }else {
                return Msg.fail();
            }
        }else {
            return Msg.fail();
        }
    }

    private Product compactProduct(Integer shopId,Integer productCategoryId,String productName){
        Product product = new Product();
        Shop shop = new Shop();
        shop.setShopId(shopId);
        if(shopId > 0){
            product.setShop(shop);
        }
        if(productCategoryId > 0){
            ProductCategory productCategory = new ProductCategory();
            productCategory.setProductCategoryId(productCategoryId);
            product.setProductCategory(productCategory);
        }
        if(!productName.equals("") && productName!=null){
            product.setProductName(productName);
        }
        product.setEnableStatus(1);
        return product;
    }
}
