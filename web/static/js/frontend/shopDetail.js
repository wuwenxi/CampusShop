$(function () {

    var loading = false;

    var shopId = getQueryString("shopId");
    //alert(shopId);

    var pageIndex = 1;
    var pageSize = 20;
    var productCategoryId = "";
    var productName = "";
    var maxItems = 999;

    if(shopId>0){
        getShopDetail()
    }

    function getShopDetail(){
        $.getJSON("/frontend/showShopDetail?shopId="+shopId,function (data) {
            if(data.code===100){
                //console.log(data);
                var shop = data.extend.map.shop;
                $("#shop-cover-pic").attr("src",shop.shopImg);
                $("#shop-update-time").html(
                    "更新时间："+new Date(shop.lastEditTime).toLocaleDateString()
                );
                $("#shop-name").html("商铺："+shop.shopName);
                $("#shop-desc").html(shop.shopDesc);
                $("#shop-addr").html("地址："+shop.shopAddress);
                $("#shop-phone").html("电话："+shop.phone);

                var productCategoryList = data.extend.map.list;
                var html = '';
               productCategoryList.map(function (item,index) {
                   html += "<a href='#' class='button' data-product-search-id="+
                       item.productCategoryId+ ">"
                        +item.productCategoryName+"</a>"
               });
               $("#shopdetail-button-div").html(html);
            }
        });
    }

    addItem(pageIndex,pageSize);

    function addItem(pageIndex,pageSize){
        loading = true;
        $.getJSON("/frontend/listProductInfo?shopId="+shopId
            +"&pageIndex="+pageIndex
            +"&pageSize="+pageSize
            +"&productCategoryId="+productCategoryId
            +"&productName="+productName
            ,function (data) {
                if (data.code === 100) {
                    //console.log(data);
                    maxItems = data.extend.map.count;
                    var html = '';
                    data.extend.map.list.map(function (item, index) {
                        html += '' + '<div class="card" data-shop-id="'
                            + item.productId + '">' + '<div class="card-header">'
                            + item.productName + '</div>'
                            + '<div class="card-content">'
                            + '<div class="list-block media-list">' + '<ul>'
                            + '<li class="item-content">'
                            + '<div class="item-media">' + '<img src="'
                            + item.imgAddress + '" width="44">' + '</div>'
                            + '<div class="item-inner">'
                            + '<div class="item-subtitle">' + item.productDesc
                            + '</div>' + '</div>' + '</li>' + '</ul>'
                            + '</div>' + '</div>' + '<div class="card-footer">'
                            + '<p class="color-gray">'
                            + new Date(item.lastEditTime).toLocaleDateString()
                            + '更新</p>' + '<span>点击查看</span>' + '</div>'
                            + '</div>';
                    });
                    $('.list-div').append(html);
                    var total = $('.list-div .card').length;
                    if (total >= maxItems) {
                        // 加载完毕，则注销无限加载事件，以防不必要的加载
                        $.detachInfiniteScroll($('.infinite-scroll'));
                        // 删除加载提示符
                        $('.infinite-scroll-preloader').remove();
                    }
                    pageIndex += 1;

                    loading = false;
                    $.refreshScroller();
                }
            })
    }

    $("#shopdetail-button-div").on("click",".button",function (e) {
        productCategoryId = e.target.dataset.productSearchId;
        if(productCategoryId){
            if($(e.target).hasClass("button-fill")){
                $(e.target).removeClass("button-fill");
                productCategoryId = "";
            }else {
                $(e.target).addClass("button-fill").siblings()
                    .removeClass("button-fill");
            }
            $(".list-div").empty();
            pageIndex = 1;
            addItem(pageIndex,pageSize);
        }
    });

    $(".list-div").on("click",".card",function (e) {
        var productId = e.currentTarget.dataset.shopId;
        //alert(productId);
        window.location.href = "/frontend/productDetail?productId="+productId;
    });

    $("#search").on("input",function (e) {
        productName = e.target.value;
        $(".list-div").empty();
        pageIndex = 1;
        addItem(pageIndex,pageSize);
    });

    $('#me').click(function() {
        $.openPanel('#panel-left-demo');
    });

    $.init();
});