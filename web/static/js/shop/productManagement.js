
$(function () {

    getList();

    function getList(){
        $.getJSON("/shopAdmin/getProductList?pageIndex=1&pageSize=999",function (data) {
            if(data.code === 100){
                var tempHtml="";

                $.each(data.extend.map.list,function (index,item) {


                    //若商品为上架状态，则显示下架
                    var textOp = "下架";
                    var contraryStatus = 0;
                    if (item.enableStatus === 0) {
                        textOp = "上架";
                        contraryStatus = 1;
                    } else {
                        contraryStatus = 0;
                    }
                    tempHtml += '' + '<div class="row row-product">'
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
                        + '<a href="#" class="status" data-id="'
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
                });
                $('.product-wrap').html(tempHtml);

            }
        });
    }



    $("#new").click(function () {
        window.location.href = "/shop/productEdit";
    });

    $(document).on("click","a",function (e) {
        var target = $(e.currentTarget);
        if(target.hasClass("edit")){
            window.location.href = "/shop/productEdit?productId="+e.currentTarget.dataset.id;
        }
        if(target.hasClass("status")){
            changeStatus(e.currentTarget.dataset.id,e.currentTarget.dataset.status);
        }
        if (target.hasClass("preview")){
            alert("...")
        }
    });

    function changeStatus(id,status) {
        var product = {};
        product.productId = id;
        product.enableStatus = status;

        $.confirm( "确定么？",function () {
            $.ajax({
                url:"/shopAdmin/modifyProduct",
                data:{
                    product:JSON.stringify(product),
                    statusChange:true
                },
                type:"POST",
                success:function (data) {
                    if(data.code === 100){
                        $.toast("操作成功！");
                        getList();
                    }else {
                        $.toast("操作失败！");
                    }
                }
            })
        })

    }
});