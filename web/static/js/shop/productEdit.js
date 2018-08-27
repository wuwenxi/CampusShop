
$(function () {

    var productId = getQueryString("productId");
    var isEdit = !!productId;
    var postUrl;

    getList();

    if(isEdit){
        postUrl = "/shopAdmin/modifyProduct";
        getListInfo(productId);
    }else {
        postUrl = "/shopAdmin/addProduct";
    }
    //获取商品类别列表
    function getList() {
        $.getJSON("/shopAdmin/getProductCategoryList",function (data) {
            if(data.code === 100){
                console.log(data);
                $.each(data.extend.map.list,function () {
                    var productCategory = $("<option></option>").append(this.productCategoryName)
                        .attr("value",this.productCategoryId);
                    productCategory.appendTo("#category");
                })
            }
        })
    }
    //获取商品信息
    function getListInfo(id) {
        $.getJSON("/shopAdmin/getProduct/"+id,function (data) {
            if(data.code === 100){
                console.log(data);
                var info = data.extend.map.product;
                $("#product-name").val(info.productName);
                $("#priority").val(info.priority);
                $("#product-desc").val(info.productDesc);
                $("#normal-price").val(info.normalPrice);
                $("#promotion-price").val(info.promotionPrice);

                $("#category option[value='"+info.productCategory.productCategoryId+"']")
                    .attr("selected",true);
            }
        })
    }

    //动态添加按钮
    $('.detail-img-div').on('change', '.detail-img:last-child', function() {
        if ($('.detail-img').length < 6) {
            $('#detail-img').append('<input type="file" class="detail-img">');
        }
    });

    $("#submit").click(function () {
        var product = {};
        product.productId = productId;
        product.productName = $("#product-name").val();
        product.priority = $("#priority").val();
        product.normalPrice = $("#normal-price").val();
        product.promotionPrice = $("#promotion-price").val();
        product.productDesc = $("#product-desc").val();
        product.productCategory ={
            productCategory:$("#category option:checked").val()
        };

        var formData = new FormData();
        formData.append("product",product);

        //获取图片
        var productImg = $("#small-img")[0].files[0];
        formData.append("productImg",productImg);
        //获取详细图片
        $(".detail-img").map(function (index,item) {
            if ($(".detail-img")[index].files.length > 0) {
                formData.append("productImgs" + index,$("#detail-img")[0].files[0]);
            }
        });

        //封装Json数据
        formData.append("productStr",JSON.stringify(product));

        //获取验证码信息
        var verifyCode = $("#j_captcha").val();
        if(!verifyCode){
            $.toast("请输入验证码");
            return;
        }
        formData.append("verifyCode",verifyCode);


        /*$.ajax({
            url:postUrl,
            type:"POST",
            success:function (data) {
                if(data.code === 100){
                    alert("success");
                }
            }
        })*/
    })
});