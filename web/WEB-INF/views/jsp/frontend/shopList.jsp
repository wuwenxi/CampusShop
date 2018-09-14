<%--
  Created by IntelliJ IDEA.
  User: wwx
  Date: 2018/9/9
  Time: 20:32
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
          href="../../../../dist/css/sm.min.css">
    <link rel="stylesheet"
          href="../../../../dist/css/sm-extend.min.css">
    <script type='text/javascript'
            src='../../../../dist/js/zepto.min.js' charset='utf-8'></script>

    <link rel="stylesheet"
          href="../../../../static/css/frontend/shoplist/shoplist.css">

</head>
<%
    pageContext.setAttribute("APP_PATH",request.getContextPath());
%>
<body>

    <div class="page-group">
        <div class="page">
            <header class="bar bar-nav">
                <%--<a class="button button-link button-nav pull-left" external
                   href="index" data-transition='slide-out'> <span
                        class="icon icon-left"></span> 返回
                </a>--%>
                <h1 class="title">商店列表</h1>
            </header>
            <div class="bar bar-header-secondary">
                <div class="searchbar">
                    <a class="searchbar-cancel">取消</a>
                    <div class="search-input">
                        <label class="icon icon-search" for="search"></label> <input
                            type="search" id='search' placeholder='输入关键字...' />
                    </div>
                </div>
            </div>
            <nav class="bar bar-tab">
                <a class="tab-item" href="${APP_PATH}/frontend/index" external> <span
                        class="icon icon-home"></span> <span class="tab-label">首页</span>
                </a> <a class="tab-item" href="#" id="me"> <span
                    class="icon icon-me"></span> <span class="tab-label">我</span>
            </a>
            </nav>
            <div class="content infinite-scroll infinite-scroll-bottom"
                 data-distance="100">
                <!-- 这里是页面内容区 -->
                <div class="shoplist-button-div" id="shoplist-search-div">
                     <%--<a href="#" class="button">所有货物</a>
                        <a href="#" class="button">吃的</a>
                        <a href="#" class="button">喝的</a>
                        <a href="#" class="button">Usual Button 1</a>
                        <a href="#" class="button">Usual Button 1</a>
                        <a href="#" class="button">Usual Button 1</a>--%>
                </div>
                <div class="select-wrap">
                    <select class="select" id="area-search"></select>
                </div>
                <div class="list-div shop-list">
                     <div class="card">
                           <%-- <div class="card-header">传统火锅店</div>
                            <div class="card-content">
                                <div class="list-block media-list">
                                    <ul>
                                        <li class="item-content">
                                            <div class="item-media">
                                                <img src="http://gqianniu.alicdn.com/bao/uploaded/i4//tfscom/i3/TB10LfcHFXXXXXKXpXXXXXXXXXX_!!0-item_pic.jpg_250x250q60.jpg" width="44">
                                            </div>
                                            <div class="item-inner">
                                                <div class="item-subtitle"></div>
                                            </div>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <div class="card-footer">
                                <span>2015/01/15</span>
                                <span>5 评论</span>
                            </div>--%>
                        </div>
                </div>
                <div class="infinite-scroll-preloader">
                    <div class="preloader"></div>
                </div>
            </div>
        </div>
    </div>
    <!--侧边栏  -->
    <div class="panel-overlay"></div>
    <div class="panel panel-right panel-reveal" id="panel-left-demo">
        <div class="content-block">
            <p>
                <a href="${APP_PATH}/frontend/myrecord" class="close-panel">消费记录</a>
            </p>
            <p>
                <a href="${APP_PATH}/frontend/mypoint" class="close-panel">我的积分</a>
            </p>
            <p>
                <a href="${APP_PATH}/frontend/pointrecord" class="close-panel">积分兑换记录</a>
            </p>
            <!-- Click on link with "close-panel" class will close panel -->
        </div>
    </div>

    <script type='text/javascript'
            src='${APP_PATH}/dist/js/sm.min.js' charset='utf-8'></script>
    <script type='text/javascript'
            src='${APP_PATH}/dist/js/sm-extend.min.js' charset='utf-8'></script>
    <script type="text/javascript" src="${APP_PATH}/dist/js/sm-city-picker.min.js"
            charset="UTF-8"></script>

    <script type="text/javascript" src="../../../../static/js/common/common.js"></script>
    <script type="text/javascript" src="../../../../static/js/frontend/shopList.js"></script>
</body>
</html>
