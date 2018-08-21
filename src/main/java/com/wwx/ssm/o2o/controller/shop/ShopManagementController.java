package com.wwx.ssm.o2o.controller.shop;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wwx.ssm.o2o.entity.*;
import com.wwx.ssm.o2o.enums.ShopStateEnum;
import com.wwx.ssm.o2o.execution.ShopExecution;
import com.wwx.ssm.o2o.service.AreaService;
import com.wwx.ssm.o2o.service.ShopCategoryService;
import com.wwx.ssm.o2o.service.ShopService;
import com.wwx.ssm.o2o.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/shopAdmin")
public class ShopManagementController {

    @Autowired
    ShopService shopService;

    @Autowired
    AreaService areaService;

    @Autowired
    ShopCategoryService shopCategoryService;

    private Map<String, Object> map = new HashMap<String, Object>();

    private ShopExecution execution = new ShopExecution();


    /**
     * 获取用户信息
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getShopManagementInfo",method = RequestMethod.GET)
    public Msg getManagementInfo(HttpServletRequest request){
        Integer shopId = HttpServletRequestUtils.getInt(request,"shopId");
        //若传入的shopId值小于等于0  则从session中获取currentShop
        //否则设置currentShop传入当session当中
        if(shopId<=0){
            //从session中获取currentShop
            Object currentShop = request.getSession().getAttribute("currentSHop");
            //若不存在currentShop，则用户的这次操作为非法操作，前端页面提示用户非法操作，并重定向到店铺列表界面
            //若存在currentShop，直接获取用户的信息，并返回信息不需要进行重定向
            if(currentShop == null){
                map.put("redirect",true);
                map.put("url","/shop/shopList");
            }else {
                Shop shop = (Shop) currentShop;
                map.put("redirect",false);
                map.put("shopId",shop.getShopId());
            }
        }else{
             //设置currentShop传入当session当中
            Shop currentShop = new Shop();
            currentShop.setShopId(shopId);
            request.getSession().setAttribute("currentShop",currentShop);
            map.put("redirect",false);
        }
        return Msg.success().add("map",map);
    }

    /**
     *      获取店铺列表
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getShopList",method = RequestMethod.GET)
    public Msg getShopList(HttpServletRequest request){
        PersonInfo info = new PersonInfo();
        info.setUserId(1);
        info.setName("吴文锡");
        request.getSession().setAttribute("user",info);

        info = (PersonInfo) request.getSession().getAttribute("user");
        try {
            Shop shop = new Shop();
            shop.setOwner(info);
            ShopExecution sc = shopService.getShopList(shop,0,50);
            map.put("shopList",sc.getShops());
            //System.out.println(shop);
            map.put("user",shop.getOwner());
            //System.out.println(shop.getOwner());
            return Msg.success().add("map",map);
        } catch (Exception e) {
            e.printStackTrace();
            return Msg.fail();
        }
    }

    /**
     *         修改店铺信息
     *         1. 获取店铺信息
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getShopById",method = RequestMethod.GET)
    public Msg getShopById(HttpServletRequest request){
        Integer id;
        try {
            id = HttpServletRequestUtils.getInt(request,"shopId");
            Shop shop = shopService.getShopById(id);
            List<Area> list = areaService.getAreaList();
            map.put("areaList",list);
            map.put("shop",shop);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Msg.success().add("map",map);
    }

    /***
     *              2. 返回修改后的信息
     * @param request
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/modifyShop",method = RequestMethod.POST)
    public Msg modifyShopInfo(HttpServletRequest request) throws Exception{

        if(!CodeUtils.checkVerifyCode(request)){
            return Msg.fail().add("error","验证码错误");
        }
        //转换json数据
        String shopStr = HttpServletRequestUtils.getString(request, "shopStr");
        ObjectMapper objectMapper = new ObjectMapper();
        Shop shop;
        try {
            shop = objectMapper.readValue(shopStr, Shop.class);
        } catch (Exception e) {
            e.printStackTrace();
            return Msg.fail().add("errorMsg", e.getMessage());
        }

        //获取图片  文件上传解析
        CommonsMultipartFile shopImage = null;
        //文件解析
        CommonsMultipartResolver resolve = new CommonsMultipartResolver(request.getSession().getServletContext());
        if (resolve.isMultipart(request)) {
            MultipartHttpServletRequest servletRequest = (MultipartHttpServletRequest) request;
            //获取图片文件
            shopImage = (CommonsMultipartFile) servletRequest.getFile("shopImg");
        }

        if(shop.getShopId()!=null){
            if(shopImage!=null){
                if(!ValidateUtil.checkShopImage(shopImage.getOriginalFilename())){
                    return Msg.fail().add("error","文件格式错误！请传入图片");
                }
                execution = shopService.modifyShop(shop,shopImage.getInputStream(),shopImage.getOriginalFilename());
            }else
                execution = shopService.modifyShop(shop,null,null);
            if(execution.getState().equals(ShopStateEnum.SUCCESS.getState())){
                return Msg.success().add("execution",execution.getStateInfo());
            }
        }
        return Msg.fail().add("error","更新失败");
    }

    /**
     *
     *       注册店铺
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/registerShop", method = RequestMethod.POST)
    public Msg registerShop(HttpServletRequest request) {

        if(!CodeUtils.checkVerifyCode(request)){
            return Msg.fail().add("Error","验证码错误");
        }

        /**
         *        1. 接受并转换相应的数据，包括店铺信息和店铺图片（Json数据转为POJO）
         *         2.  注册店铺
         *          3. 返回信息
         */
        //转换json数据
        String shopStr = HttpServletRequestUtils.getString(request, "shopStr");
        ObjectMapper objectMapper = new ObjectMapper();
        Shop shop;
        try {
            shop = objectMapper.readValue(shopStr, Shop.class);
        } catch (Exception e) {
            e.printStackTrace();
            return Msg.fail().add("errorMsg", e.getMessage());
        }

        //获取图片  文件上传解析
        CommonsMultipartFile shopImage;
        //文件解析
        CommonsMultipartResolver resolve = new CommonsMultipartResolver(request.getSession().getServletContext());
        if (resolve.isMultipart(request)) {
            MultipartHttpServletRequest servletRequest = (MultipartHttpServletRequest) request;
            //获取图片文件
            shopImage = (CommonsMultipartFile) servletRequest.getFile("shopImg");
        } else {
            return Msg.fail().add("Error", "上传图片不能为空");
        }
        if(!ValidateUtil.checkShopImage(shopImage.getOriginalFilename())){
            return Msg.fail().add("error","文件格式错误！请传入图片");
        }

        try {
            //前端页面不添加用户信息
            //从session中获取用户信息  注册店铺需要登录  即可得到用户的信息
            PersonInfo info = (PersonInfo) request.getSession().getAttribute("user");
            info.setUserId(1);
            shop.setOwner(info);

            execution = shopService.addShop(shop,shopImage.getInputStream(),shopImage.getOriginalFilename());

            //如果状态为审核，则返回正确，否则错误
            if (execution.getState().equals(ShopStateEnum.CHECK.getState())) {
                /**
                 *          用户和店铺是一对多的关系
                 *          注册店铺或将店铺信息保存到session中
                 */
                List<Shop> shopList = (List<Shop>) request.getSession().getAttribute("shopList");
                if(shopList == null || shopList.size()==0){
                    shopList = new ArrayList<Shop>();
                }
                //把刚刚注册的店铺添加到列表中
                shopList.add(execution.getShop());
                request.getSession().setAttribute("shopList",shopList);
                return Msg.success().add("execution",execution);
            } else {
                return Msg.fail().add("error", execution.getStateInfo());
            }
        } catch (Exception e) {
            return Msg.fail().add("errorMsg:",e.getMessage());
        }
    }

    /**
     *     注册店铺时  获取店铺类别  区域信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getShopInitInfo",method = RequestMethod.GET)
    public Msg getShopInitInfo(){
        //获取地区信息
        try {
            List<Area> areaList = areaService.getAreaList();
            //获取店铺类型信息
            List<ShopCategory> shopCategoryList = shopCategoryService.getShopCategoryList(new ShopCategory());
            map.put("areaList",areaList);
            map.put("shopCategoryList",shopCategoryList);
        } catch (Exception e) {
            return Msg.fail().add("Error","获取信息失败");
        }
        return Msg.success().add("map",map);
    }
}
