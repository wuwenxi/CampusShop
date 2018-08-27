
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

                var productName = $("#product_name").append(item.productName);
                var priority = $("#priority").append(item.priority);
                var edit = $(".edit").append("编辑").attr("product_id",item.productId)
                    .attr("status",item.enableStatus);
                var del = $(".delete").append(textOp).attr("product_id",item.productId)
                    .attr("status",contraryStatus);
                var preview = $(".preview").append("预览").attr("product_id",item.productId)
                    .attr("status",item.enableStatus);

                var operation =$("#operation").append(edit).append(del).append(preview);
                $(".row .row-product").append(productName)
                    .append(priority).append(operation);
            })

        }
    });

    $("#new").click(function () {
        window.location.href = "/shop/productEdit";
    });

    $(document).on("click","a",function (e) {
        var target = $(e.currentTarget);
        if(target.hasClass("edit")){
            var productId = $(this).attr("product_id");
            window.location.href = "/shop/productEdit?productId="+productId;
        }
        if(target.hasClass("delete")){
            alert("...")
        }
        if (target.hasClass("preview")){
            alert("...")
        }
    });

});