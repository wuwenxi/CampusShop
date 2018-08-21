<%--
  Created by IntelliJ IDEA.
  User: wwx
  Date: 2018/8/20
  Time: 14:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>商店列表</title>
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

    <link rel="stylesheet" href="${APP_PATH}/static/css/shop/shoplist.css">
</head>
<%
    pageContext.setAttribute("APP_PATH",request.getContextPath());
%>
<body>
<header class="bar bar-nav">
    <h1 class="title">商店列表</h1>
</header>
<div class="content">
    <div class="content-block">
        <p>你好,<span id="user-name"></span>
            <a class="pull-right" href="${APP_PATH}/shop/shopOperation">增加店铺</a>
        </p>
        <div class="row row-shop">
            <div class="col-40">商店名称</div>
            <div class="col-40">状态</div>
            <div class="col-20">操作</div>
        </div>
        <div class="shop-wrap">

        </div>
    </div>
    <div class="content-block">
        <div class="row">
            <div class="col-50">
                <a href="" id="log-out"
                   class="button button-big button-fill button-danger">退出系统</a>
            </div>
            <div class="col-50">
                <a href="#" class="button button-big button-fill button-success">修改密码</a>
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
<script type="text/javascript" src="${APP_PATH}/static/js/shop/shoplist.js"></script>
</body>
</html>
