<%--
  Created by IntelliJ IDEA.
  User: wwx
  Date: 2018/8/21
  Time: 17:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>商品分类管理</title>
    <meta name="viewport" content="initial-scale=1, maximum-scale=1">
    <link rel="shortcut icon" href="/favicon.ico">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">

    <link rel="stylesheet"
          href="${APP_PATH}/dist/css/sm.min.css">
    <link rel="stylesheet"
          href="${APP_PATH}/dist/css/sm-extend.min.css">

    <script type='text/javascript'
            src='${APP_PATH}/dist/js/zepto.min.js' charset='utf-8'></script>
    <link rel="stylesheet" href="${APP_PATH}/static/css/shop/productCategoryManage.css">
</head>
<%
    pageContext.setAttribute("APP_PATH",request.getContextPath());
%>
<body>
    <header class="bar bar-nav">
        <h1 class="title">商品分类管理</h1>
    </header>
    <div class="content">
        <div class="content-block">
            <div class="row row-product-category">
                <div class="col-33">类别</div>
                <div class="col-33">优先级</div>
                <div class="col-33">操作</div>
            </div>
            <div class="category-wrap">

            </div>
        </div>
        <div class="content-block">
            <div class="row">
                <div class="col-50">
                    <a href="#" class="button button-big button-fill button-success" id="addPC">新增</a>
                </div>
                <div class="col-50">
                    <a href="#" class="button button-big button-fill" id="submit">提交</a>
                </div>
            </div>
        </div>
    </div>

    <script type='text/javascript'
            src='${APP_PATH}/dist/js/sm.min.js' charset='utf-8'></script>
    <script type='text/javascript'
            src='${APP_PATH}/dist/js/sm-extend.min.js' charset='utf-8'></script>
    <script type="text/javascript" src="${APP_PATH}/dist/js/sm-city-picker.min.js"
            charset="UTF-8"></script>
    <script type='text/javascript' src="${APP_PATH}/static/js/shop/productCategoryManage.js" charset='utf-8'></script>
</body>
</html>
