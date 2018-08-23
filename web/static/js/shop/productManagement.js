
$(function () {

    $.getJSON("/shopAdmin/getProductList",function (data) {
        if(data.code === 100){
            $.each(data.extend.map.productList,function (index,item) {
                var textOp = "下架";
                var contraryStatus = 0;
                if (item.enableStatus === 0) {
                    textOp = "上架";
                    contraryStatus = 1;
                } else {
                    contraryStatus = 0;
                }
                var tempHtml = '' + '<div class="row row-product">'
                    + '<div class="col-30">'
                    + item.productName
                    + '</div>'
                    + '<div class="col-20">'
                    + item.priority
                    + '</div>'
                    + '<div class="col-50">'
                    + '<a href="#" class="edit" data-id="'
                    + item.productId
                    + '" data-status="'
                    + item.enableStatus
                    + '">编辑</a>'
                    + '<a href="#" class="delete" data-id="'
                    + item.productId
                    + '" data-status="'
                    + contraryStatus
                    + '">'
                    + textOp
                    + '</a>'
                    + '<a href="#" class="preview" data-id="'
                    + item.productId
                    + '" data-status="'
                    + item.enableStatus
                    + '">预览</a>'
                    + '</div>'
                    + '</div>';
                $(".product-wrap").append(tempHtml);
            })

        }
    });


    $(document).on("click",".edit",function () {
        alert("...")
    });

    $(document).on("click",".delete",function () {
        alert("...")
    });

    $(document).on("click",".preview",function () {
        alert("...")
    });

});