/**
 *      
 */
$(function () {

    getList();

    function getList(){
        $.getJSON("/shopAdmin/getShopList",function (data) {
            if (data.code === 100){
                console.log(data.extend.map.user);
                $("#user-name").text(data.extend.map.user.name);
                handleShop(data.extend.map.shopList);
            }
        });
    }

    function handleShop(shopInfo) {
        var html="";
        $.each(shopInfo,function (index,item) {
            html += '<div class="row row-shop"><div class="col-40">'+ item.shopName +'</div><div class="col-40">'
                + shopStatus(item.enableStatus) +
                '</div><div class="col-20">'
                + goShop(item.enableStatus, item.shopId)
                +'</div></div>';
        });
        $(".shop-wrap").html(html);
    }

    function shopStatus(data) {
        if(data === 0){
            return "审核中"
        }else if(data === -1){
            return "非法店铺"
        }else {
            return "审核通过"
        }
    }

    function goShop(status, id) {
        if (status !== 0 && status !== -1) {
            return '<a href="/shop/shopManagement?shopId='+ id +'">进入</a>';
        } else {
            return '';
        }
    }
});