/**
 *
 *
 */
$(function () {
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

    //添加按钮
    $("#addPC").click(function () {
        alert("....")
    });

    //删除按钮
    $(document).on("click",".delete",function (e) {
        var target = e.currentTarget;
        var productCategoryId = target.dataset.id ;
        $.confirm("确定删除该店铺类别？",function () {
            $.ajax({
                url:"/shopAdmin/deleteProductCategory/"+productCategoryId,
                type:"DELETE",
                success:function (data) {
                    if(data.code === 100){
                        alert("删除成功");
                    }
                }
            })
        });
    });

    $("#submit").click(function () {
        alert(".....")
    });
});