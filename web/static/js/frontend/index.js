
$(function () {

    $.getJSON("/frontend/mainPageListInfo",function (data) {
        if(data.code === 100){
            console.log(data);
            //拼接轮播图
            var headLineList = data.extend.map.headLineList;
            var swiperHtml = '';
            headLineList.map(function (item, index) {
                swiperHtml += ''
                    + '<div class="swiper-slide img-wrap">'
                    +      '<img class="banner-img" src= "'+ item.lineImg +'" alt="'+ item.lineName +'">'
                    + '</div>';
            });
            $('.swiper-wrapper').html(swiperHtml);
            $(".swiper-container").swiper({
                autoplay: 3000,
                autoplayDisableOnInteraction: false
            });

            //拼接店铺列表
            var shopCategory = data.extend.map.shopCategoryList;
            var shopCategoryHtml = "";
            shopCategory.map(function (item,index) {
                shopCategoryHtml += ''
                    +  '<div class="col-50 shop-classify" data-category='+ item.shopCategoryId +'>'
                    +      '<div class="word">'
                    +          '<p class="shop-title">'+ item.shopCategoryName +'</p>'
                    +          '<p class="shop-desc">'+ item.shopCategoryDesc +'</p>'
                    +      '</div>'
                    +      '<div class="shop-classify-img-warp">'
                    +          '<img class="shop-img" src="'+ item.shopCategoryImg +'">'
                    +      '</div>'
                    +  '</div>';
            });
            $(".row").html(shopCategoryHtml);

        }else {
            $.toast("获取信息失败")
        }
    });


    /**
     *   左侧边栏的响应事件
     */
    $("#me").click(function () {
        $.openPanel("#panel-left-demo");
    });

    $(document).on("click",".shop-classify",function (e) {
        var shopCategoryId = e.currentTarget.dataset.category;
        //alert(shopCategoryId);
        window.location.href = "/frontend/shopList?parentId= "+ shopCategoryId;
    })
});