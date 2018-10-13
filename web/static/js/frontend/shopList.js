$(function () {
    var parentId = getQueryString("parentId");
    var flag = !!parentId;

    var loading = false;
    var maxItems = 999;
    var pageSize = 20;
    var pageIndex = 1;

    var areaId ="";
    var shopCategoryId="";
    var shopName="";

    if(flag){
        getShopListInitInfo(parentId);
    }else {
        parentId = -1;
        getShopListInitInfo(parentId)
    }

    function getShopListInitInfo(id){
        $.getJSON("/frontend/shopListInitInfo/"+id,function (data) {
            if(data.code === 100){
                //console.log(data);
                var shopCategoryList = data.extend.map.shopCategoryList;
                var html = '';
                html += '<a href="#" class="button" data-category-id=""> 全部类别  </a>';
                shopCategoryList
                    .map(function(item, index) {
                        html += '<a href="#" class="button" data-category-id='
                            + item.shopCategoryId
                            + '>'
                            + item.shopCategoryName
                            + '</a>';
                    });
                $('#shoplist-search-div').html(html);
                var selectOptions = '<option value="">全部街道</option>';
                var areaList = data.extend.map.areaList;
                areaList.map(function(item, index) {
                    selectOptions += '<option value="'
                        + item.areaId + '">'
                        + item.areaName + '</option>';
                });
                $('#area-search').html(selectOptions);
            }
        })
    }

    /*无限分页*/
    function addItems(pageSize,pageIndex) {
        loading = true;
        $.ajax({
            url:"/frontend/getShopList" + "?"+"pageIndex=" + pageIndex + "&pageSize=" + pageSize
                + "&areaId=" + areaId + "&shopCategoryId=" + shopCategoryId + '&shopName=' + shopName,
            //传递多个参数 data:"parentId="+parentId + "&areaId="+3,
            data:"parentId="+parentId,
            type:"GET",
            success:function (data) {
                if (data.code === 100) {
                    maxItems = data.extend.map.count;
                    var html = '';
                    data.extend.map.shopList.map(function (item, index) {
                        html += '' + '<div class="card" data-shop-id="'
                            + item.shopId + '">' + '<div class="card-header">'
                            + item.shopName + '</div>'
                            + '<div class="card-content">'
                            + '<div class="list-block media-list">' + '<ul>'
                            + '<li class="item-content">'
                            + '<div class="item-media">' + '<img src="'
                            + item.shopImg + '" width="44">' + '</div>'
                            + '<div class="item-inner">'
                            + '<div class="item-subtitle">' + item.shopDesc
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
            }
        });
    }

    addItems(pageSize,pageIndex);

    $(document).on(".infinite",".infinite-scroll-bottom",function () {
        if(loading)
            return;
        addItems(pageSize,pageIndex);
    });

    //卡片的响应事件
    $(".shop-list").on("click",".card",function (e) {
        var shopId = e.currentTarget.dataset.shopId;
        alert(shopId);
        window.location.href = "/frontend/shopDetail?shopId="+shopId;
    });

    //类别选择
    $("#shoplist-search-div").on("click",".button",function (e) {
        if(parentId){
            shopCategoryId  = e.target.dataset.categoryId;
            if($(e.target).hasClass("button-fill")){
                $(e.target).removeClass("button-fill");
                shopCategoryId = '';
            }else {
                //移除同级别的其他样式
                $(e.target).addClass("button-fill").siblings()
                    .removeClass("button-fill");
            }
            $(".list-div").empty();
            pageIndex = 1;
            addItems(pageSize,pageIndex);
        }else {
            parentId = e.target.dataset.categoryId;
            if($(e.target).hasClass("button-fill")){
                $(e.target).removeClass("button-fill");
                parentId = '';
            }else {
                $(e.target).addClass("button-fill").siblings()
                    .removeClass("button-fill");
            }
            $(".list-div").empty();
            pageIndex = 1;
            addItems(pageSize,pageIndex);
            parentId = '';
        }
    });

    //查询指定商铺
    $("#search").on("input",function (e) {
        shopName = e.target.value;
        $(".list-div").empty();
        pageIndex = 1;
        addItems(pageSize,pageIndex);
    });

    //地区选择
    $("#area-search").on("change",function (e) {
        areaId = $("#area-search").val();
        $(".list-div").empty();
        pageIndex = 1;
        addItems(pageSize,pageIndex);
    });


    /**/
    $('#me').click(function() {
        $.openPanel('#panel-left-demo');
    });

    $.init();
});
