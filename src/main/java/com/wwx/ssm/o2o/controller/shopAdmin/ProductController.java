package com.wwx.ssm.o2o.controller.shopAdmin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wwx.ssm.o2o.bean.ImageHolder;
import com.wwx.ssm.o2o.bean.Msg;
import com.wwx.ssm.o2o.entity.Product;
import com.wwx.ssm.o2o.entity.ProductCategory;
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
import com.wwx.ssm.o2o.utils.HttpServletRequestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
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
     *        根据商品id获取商品信息
     * @param productId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getProduct/{productId}",method = RequestMethod.GET)
    public Msg getProductById(@PathVariable Integer productId){
        if(productId!=null){
            try {
                ProductExecution execution = service.getProductById(productId);
                if(execution.getState().equals(ProductEnum.SUCCESS.getStatus())){
                    map.put("product",execution.getProduct());
                     return Msg.success().add("map",map);
                }
            } catch (Exception e) {
                throw new ProductException("获取失败");
            }
        }
        return Msg.fail();
    }


    /**
     *
     *    获取商品列表
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getProductList",method = RequestMethod.GET)
    public Msg getProductList(HttpServletRequest request){
        //前台获取pageIndex
        Integer pageIndex = HttpServletRequestUtils.getInt(request,"pageIndex");
        //前台获取pageIndex
        Integer pageSize = HttpServletRequestUtils.getInt(request,"pageSize");
        //
        Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
        if(pageIndex>-1 && pageSize>-1 && currentShop!= null && currentShop.getShopId()!=null){
            Integer productCategory = HttpServletRequestUtils.getInt(request,"productCategoryId");
            String productName = HttpServletRequestUtils.getString(request,"productName");

            Product product = compactProduct(currentShop.getShopId(),productCategory,productName);

            ProductExecution execution = service.getProductList(product,pageIndex,pageSize);
            map.put("list",execution.getProductList());
            map.put("count",execution.getCount());
        }
        return Msg.success().add("map",map);
    }

    private Product compactProduct(Integer shopId, Integer productCategory, String productName) {
        Shop shop = new Shop();
        shop.setShopId(shopId);

        Product product = new Product();
        product.setShop(shop);

        if(productCategory > 0){
            ProductCategory category = new ProductCategory();
            category.setProductCategoryId(productCategory);
            product.setProductCategory(category);
        }

        if(productName!=null){
            product.setProductName(productName);
        }

        return product;
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
    public Msg addProduct(HttpServletRequest request){
        //验证码
        if(!CodeUtils.checkVerifyCode(request)){
            return Msg.fail().add("error","验证码错误");
        }
        //解析数据
        ObjectMapper objectMapper = new ObjectMapper();
        String productStr = HttpServletRequestUtils.getString(request,"product");
        Product product;
        try {
            product = objectMapper.readValue(productStr,Product.class);
        } catch (IOException e) {
            return Msg.fail().add("errorMsg:",e.getMessage());
        }

        ImageHolder image = null;
        List<ImageHolder> productImgList;
        // 解析图片
        productImgList = new ArrayList<ImageHolder>();

        CommonsMultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        try {
            if (resolver.isMultipart(request)) {
               image = resolveImage(request,image,productImgList);
            }else {
                return Msg.fail().add("error","请上传图片");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
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
        } catch (ProductException e) {
            e.printStackTrace();
        }
        return Msg.fail().add("error","添加失败");
    }

    @ResponseBody
    @RequestMapping(value = "/modifyProduct",method = RequestMethod.POST)
    public Msg modifyProduct(HttpServletRequest request){

        //验证码
        /**
         *  两种编辑方式：1.通过编辑页面  2.通过上架、下架操作
         *  需要经过判断
         */

        //判断是经过第一种方式还是第二种方式，若为第二种方式，则跳过验证码验证
        boolean statusChange = HttpServletRequestUtils.getBoolean(request,"statusChange");

        if(!statusChange && !CodeUtils.checkVerifyCode(request)){
            return Msg.fail().add("error","验证码错误");
        }
        //解析数据
        ObjectMapper objectMapper = new ObjectMapper();
        String productStr = HttpServletRequestUtils.getString(request,"product");
        Product product;
        try {
            product = objectMapper.readValue(productStr,Product.class);
        } catch (IOException e) {
            return Msg.fail().add("errorMsg:",e.getMessage());
        }

        ImageHolder image = null;
        List<ImageHolder> productImgList;
        // 解析图片
        productImgList = new ArrayList<ImageHolder>();

        CommonsMultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());

        try {
            if (resolver.isMultipart(request)) {
                //2. 获取详细图
                image = resolveImage(request,image,productImgList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
            Shop shop = new Shop();
            shop.setShopId(currentShop.getShopId());

            product.setShop(shop);
            try {
                ProductExecution execution = service.modifyProduct(product,image,productImgList);
                if(execution.getState().equals(ProductEnum.SUCCESS.getStatus())){
                    return Msg.success();
                }
            } catch (Exception e) {
                throw new ProductException("更新失败");
            }
        } catch (ProductException e) {
            e.printStackTrace();
        }
        return Msg.fail().add("error","更新失败");
    }

    private ImageHolder resolveImage(HttpServletRequest request, ImageHolder image, List<ImageHolder> productImgList) {
        try {
            MultipartHttpServletRequest servletRequest = (MultipartHttpServletRequest) request;
            //1. 获取缩略图
            CommonsMultipartFile productImg = (CommonsMultipartFile) servletRequest.getFile("productImg");
            if (productImg != null) {
                image = new ImageHolder(productImg.getOriginalFilename(),productImg.getInputStream());
            }
            //获取详细图
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
