/**
 *
 *       修改 和 注册店铺都可以使用该 jsp和 js
 */
$(function () {
    var shopId = getQueryString("shopId");
    //shopId?true:false
    var isEdit = !!shopId;
    //URL地址
    var postUrl = "";
    //判断是注册店铺或是修改店铺信息
    if(isEdit){
        getShopInfo(shopId);
        postUrl = "/shopAdmin/modifyShop";
    }else {
        getShopInitInfo();
        postUrl = "/shopAdmin/registerShop";
    }

    //修改店铺信息时获取店铺的全部新消息
    function getShopInfo(shopId) {
        $.getJSON("/shopAdmin/getShopById?shopId="+shopId,function (data) {
            if(data.code === 100){
                var shop = data.extend.map.shop;
                $("#shopName").val(shop.shopName);
                $("#shopAddress").val(shop.shopAddress);
                $("#shopDesc").val(shop.shopDesc);
                $("#shopPhone").val(shop.phone);

                $.each(data.extend.map.areaList,function () {
                   $("<option></option>").append(this.areaName)
                       .attr("value",this.areaId).appendTo("#area");
                });
                //将area中的id值设为选中
                $("#area option[value='"+shop.area.areaId+"']").attr("selected",true);

                $("<option></option>").append(shop.shopCategory.shopCategoryName)
                    .attr("value",shop.shopCategory.shopCategoryId).appendTo("#shopCategory");
                //将商品类别设为不可选
                $("#shopCategory").attr("disabled","disabled");
            }
        })
    }

    //注册店铺时初始化店铺类型及地区信息
    function getShopInitInfo() {
        $.getJSON("/shopAdmin/getShopInitInfo",function (data) {
            if(data.code === 100){
                $.each(data.extend.map.shopCategoryList,function () {
                   var getShopCategory = $("<option></option>").append(this.shopCategoryName)
                       .attr("value",this.shopCategoryId);
                    getShopCategory.appendTo("#shopCategory");
                });
                $("#shopCategory").removeAttr("disabled");

                $.each(data.extend.map.areaList,function () {
                    var getArea = $("<option></option>").append(this.areaName)
                        .attr("value",this.areaId);
                    getArea.appendTo("#area");
                });
            }else{
                $.toast("获取信息失败");
            }
        })
    }

    $("#submit").click(function () {
        //alert("提交")
        var shop = {};
        if(isEdit){
            shop.shopId = shopId;
        }
        shop.shopName = $("#shopName").val();
        shop.shopDesc = $("#shopDesc").val();
        shop.shopAddress = $("#shopAddress").val();
        shop.phone = $("#shopPhone").val();

        if(!shop.shopName){
            $.toast("请填写商铺名称");
            return;
        }

        var regPhone = /^[1][3,4,5,7,8][0-9]{9}$/;
        if (!shop.phone){
            $.toast("请填写电话");
            return;
        }
        if(!regPhone.test(shop.phone)) {
            $.toast("请填写正确的电话号码");
            $("#j_captcha").click();
            return;
        }
        if (!shop.shopAddress){
            $.toast("请填写商铺地址");
            return;
        }

        shop.shopCategory = {
            /*
            zepto： 获取下拉列表选中的值
                    $("#shopCategory option:checked").val()
                    获取选中的文本值
                    $("#shopCategory option:checked").text()

            jQuery：获取下拉列表选中的值
                    $("#shopCategory option:selected").val()
                    获取选中的文本值
                    $("#shopCategory option:selected").text()
            *
            * */

            shopCategoryId:$("#shopCategory option:checked").val()
        };

        shop.area = {
            areaId:$("#area option:checked").val()
        };

        var shopImg = $("#shopImg")[0].files[0];
        var formData = new FormData();

        //封装数据
        formData.append("shopImg",shopImg);
        formData.append("shopStr",JSON.stringify(shop));

        //获取验证码信息
        var verifyCode = $("#j_captcha").val();
        if(!verifyCode){
            $.toast("请输入验证码");
            return;
        }
        formData.append("verifyCode",verifyCode);

        $.ajax({
            url:postUrl,
            data:formData,
            type:"POST",
            contentType:false,
            processData:false,
            cache:false,
            success:function (data) {
                if(data.code === 100){
                    $.toast("提交成功");
                }else {
                    $.toast("提交失败");
                }
                $("#captchaImg").click();
            }
        })
    });
});