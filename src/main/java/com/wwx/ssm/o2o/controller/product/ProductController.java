package com.wwx.ssm.o2o.controller.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wwx.ssm.o2o.bean.ImageHolder;
import com.wwx.ssm.o2o.bean.Msg;
import com.wwx.ssm.o2o.entity.Product;
import com.wwx.ssm.o2o.entity.ProductImg;
import com.wwx.ssm.o2o.entity.Shop;
import com.wwx.ssm.o2o.enums.ProductEnum;
import com.wwx.ssm.o2o.enums.ProductImgEnum;
import com.wwx.ssm.o2o.exception.ProductException;
import com.wwx.ssm.o2o.execution.ProductExecution;
import com.wwx.ssm.o2o.execution.ProductImgExecution;
import com.wwx.ssm.o2o.service.ProductImgService;
import com.wwx.ssm.o2o.service.ProductService;
import com.wwx.ssm.o2o.utils.CodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/shopAdmin")
public class ProductController {

    @Autowired
    ProductService service;

    @Autowired
    ProductImgService imgService;

    //最大允许上传的图片数量
    private static final Integer IMAGE_MAX_COUNT = 6;

    private Map<String,Object> map = new HashMap<String, Object>();

    /**
     *
     *    获取商品列表
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getProductList",method = RequestMethod.GET)
    public Msg getProductList(HttpServletRequest request){
        Shop shop = (Shop) request.getSession().getAttribute("currentShop");
        try {
            ProductExecution execution = service.getProductList(shop.getShopId());
            if(execution.getState().equals(ProductEnum.SUCCESS.getStatus())){
                List<Product> list = execution.getProductList();
                if(list != null && list.size() >0 ){
                    for (Product product:list){
                        List<ProductImg> imgList = null;
                        try {
                            ProductImgExecution imgExecution = imgService.getProductImgList(product.getProductId());
                            if(imgExecution.getState().equals(ProductImgEnum.SUCCESS.getStatus())){
                                imgList = imgExecution.getProductImgList();
                            }
                        } catch (Exception e) {
                            throw new ProductException("获取商品详细图片失败");
                        }
                        product.setProductImgList(imgList);
                    }
                }
                map.put("productList",list);
            }
        } catch (Exception e) {
            throw new ProductException("获取商品列表失败");
        }
        return Msg.success().add("map",map);
    }

    /**
     *
     *      添加商品信息
     * @param request
     * @return
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(value = "/addProduct",method = RequestMethod.POST)
    public Msg addProduct(HttpServletRequest request) throws IOException {
        //验证码
        if(!CodeUtils.checkVerifyCode(request)){
            return Msg.fail().add("error","验证码错误");
        }
        //解析数据
        ObjectMapper objectMapper = new ObjectMapper();
        String productStr = (String) request.getSession().getAttribute("product");
        Product product;
        try {
            product = objectMapper.readValue(productStr,Product.class);
        } catch (IOException e) {
            return Msg.fail().add("errorMsg:",e.getMessage());
        }

        ImageHolder image = null;
        List<ImageHolder> productImgList = null;
        try {
            // 解析图片
            productImgList = new ArrayList<ImageHolder>();

            CommonsMultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
            if (resolver.isMultipart(request)) {
                MultipartHttpServletRequest servletRequest = (MultipartHttpServletRequest) request;
                //1. 获取缩略图
                CommonsMultipartFile productImg = (CommonsMultipartFile) servletRequest.getFile("productImg");
                if (productImg != null) {
                    image = new ImageHolder(productImg.getName(),productImg.getInputStream());
                }
                //2. 获取详细图
                for (int i=0;i<IMAGE_MAX_COUNT;i++){
                    //前端页面按顺序返回图片名0-5
                    CommonsMultipartFile productImages = (CommonsMultipartFile) servletRequest.getFile("productImages"+ i);
                    if(productImages != null){
                        ImageHolder imageHolder = new ImageHolder(productImages.getOriginalFilename(),productImages.getInputStream());
                        productImgList.add(imageHolder);
                    }else {
                        break;
                    }
                }
            }else {
                return Msg.fail().add("error","请上传图片");
            }
        } catch (IOException e) {
            return Msg.fail().add("error","发生未知错误");
        }

        if(product!= null && product.getProductId()!= null){
            Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
            Shop shop = new Shop();
            shop.setShopId(currentShop.getShopId());

            product.setShop(shop);
            try {
                ProductExecution execution = service.addProduct(product,image,productImgList);
                if(execution.getState().equals(ProductEnum.SUCCESS.getStatus())){
                    return Msg.success();
                }
            } catch (Exception e) {
                throw new ProductException("添加失败");
            }
        }
        return Msg.fail().add("error","添加失败");
    }
}
