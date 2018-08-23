/**
 *
 *
 */
$(function () {
    getList();

    function getList(){
        $(".category-wrap").empty();
        $.getJSON("/shopAdmin/getProductCategoryList",function (data) {
            if(data.code === 100){
                var list = data.extend.map.list;
                $.each(list,function (index,item) {
                    var productCategory = ''
                        + '<div class="row row-product-category now">'
                        + '<div class="col-33 product-category-name">'
                        + item.productCategoryName
                        + '</div>'
                        + '<div class="col-33">'
                        + item.priority
                        + '</div>'
                        + '<div class="col-33"><a href="#" class="button delete" data-id="'
                        + item.productCategoryId
                        + '">删除</a></div>'
                        + '</div>';
                    $(".category-wrap").append(productCategory);
                })
            }
        });
    }

    //删除按钮  删除商品类别
    $(document).on("click",".now .delete",function (e) {
        var target = e.currentTarget;
        var productCategoryId = target.dataset.id ;
        $.confirm("确定删除该类别？",function () {
            $.ajax({
                url:"/shopAdmin/deleteProductCategory/"+productCategoryId,
                type:"DELETE",
                success:function (data) {
                    if(data.code === 100){
                        $.toast("删除成功");
                        getList();
                    }
                }
            });
        });
    });

    //删除新创建的行，
    $(document).on("click",".temp .delete",function () {
        $(this).parent().parent().remove();
    });

    $("#submit").click(function () {
        var tempArr = $(".temp");
        var productCategoryList = [];
        $.each(tempArr,function (index,item) {
            var tempObj = {};
            tempObj.productCategoryName = $(item).find(".category").val();
            tempObj.priority = $(item).find(".priority").val();
            if(tempObj.productCategoryName && tempObj.priority){
                productCategoryList.push(tempObj);
            }
        });

        $.ajax({
            url:"/shopAdmin/addProductCategory",
            data:JSON.stringify(productCategoryList),
            type:"POST",
            contentType : 'application/json',
            success:function (data) {
                if(data.code === 100){
                    $.toast("添加成功");
                    getList();
                }
            }
        });
    });

    //添加按钮
    $("#addPC").click(function () {
        var add = '<div class="row row-product-category temp">'
            + '<div class="col-33"><input class="category-input category" type="text" placeholder="分类名"></div>'
            + '<div class="col-33"><input class="category-input priority" type="number" placeholder="优先级"></div>'
            + '<div class="col-33"><a href="#" class="button delete">删除</a></div>'
            + '</div>';
        $('.category-wrap').append(add);
    });
});


