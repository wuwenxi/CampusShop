

$(function () {
    var shopId = getQueryString("shopId");
    var url = "/shopAdmin/getShopManagementInfo?shopId="+shopId;
    $.getJSON(url,function (data) {
        if(data.extend.map.redirect){
            window.location.href = data.extend.map.url;
        }else {
            if(data.extend.map.shopId !== undefined && data.extend.map.shopId !== null ){
                shopId = this.shopId;
            }
            $("#shopInfo").attr("href","/shop/shopOperation?shopId="+shopId);
        }
    });
});