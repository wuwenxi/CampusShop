
$(function () {

    var productId = getQueryString("productId");
   // alert(productId);

    $.ajax({
        url:"/frontend/showProductDetail/"+productId,
        type:"GET",
        success:function (data) {
            console.log(data);
            var product = data.extend.map.product;
            $("#product-img").attr("src",product.imgAddress);
            $("#product-time").html("更新时间："+new Date(product.lastEditTime).toLocaleDateString());
            $("#product-desc").html(product.productDesc);
            $("#product-name").html(product.productName);
            var html = "";
            product.productImgList.map(function (item, index) {
                html += "<div><img src= '"+item.imgAddress+"'/></div>";
            });
            $("#imgList").html(html);
        }
    });

    $('#me').click(function() {
        $.openPanel('#panel-left-demo');
    });
    $.init();

});