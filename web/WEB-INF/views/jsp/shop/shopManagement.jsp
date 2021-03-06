<%--
  Created by IntelliJ IDEA.
  User: wwx
  Date: 2018/8/20
  Time: 16:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>商店管理</title>
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

    <link rel="stylesheet" href="${APP_PATH}/static/css/shop/shopmanage.css">
</head>
<%
    pageContext.setAttribute("APP_PATH",request.getContextPath());
%>
<body>
<header class="bar bar-nav">
    <h1 class="title">商店管理</h1>
</header>
<div class="content">
    <div class="content-block">
        <div class="row">
            <div class="col-50 mb">
                <a id="shopInfo" href="${APP_PATH}/shop/shopOperation" class="button button-big button-fill" >商铺信息</a>
            </div>
            <div class="col-50 mb">
                <a href="${APP_PATH}/shop/productManage" class="button button-big button-fill">商品管理</a>
            </div>
            <div class="col-50 mb">
                <a href="${APP_PATH}/shop/productCategoryManage" class="button button-big button-fill">类别管理</a>
            </div>
           <%-- <div class="col-50 mb">
                <a href="/myo2o/shop/awardmanage" class="button button-big button-fill">奖品管理</a>
            </div>
            <div class="col-50 mb">
                <a href="/myo2o/shop/productbuycheck" class="button button-big button-fill">消费记录</a>
            </div>
            <div class="col-50 mb">
                <a href="/myo2o/shop/awarddelivercheck" class="button button-big button-fill">积分对换</a>
            </div>
            <div class="col-50 mb">
                <a href="/myo2o/shop/usershopcheck" class="button button-big button-fill">顾客积分</a>
            </div>
            <div class="col-50 mb">
                <a href="/myo2o/shop/shopauthmanage" class="button button-big button-fill">授权管理</a>
            </div>--%>
            <div class="col-100 mb">
                <a href="${APP_PATH}/shop/shopList" class="button button-big button-fill button-danger">返回</a>
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

<script type="text/javascript" src="${APP_PATH}/static/js/shop/shopmanagement.js"></script>
<script type="text/javascript" src="${APP_PATH}/static/js/common/common.js"></script>
</body>
</html>
