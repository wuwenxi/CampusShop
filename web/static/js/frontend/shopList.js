
$(function () {
    var parentId = getQueryString("parentId");
    var flag = !!parentId;
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


    /**/
    $('#me').click(function() {
        $.openPanel('#panel-left-demo');
    });

});
