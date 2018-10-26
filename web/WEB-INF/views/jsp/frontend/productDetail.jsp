<%--
  Created by IntelliJ IDEA.
  User: wwx
  Date: 2018/10/26
  Time: 19:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>商品详情</title>
    <meta name="viewport" content="initial-scale=1, maximum-scale=1">
    <link rel="shortcut icon" href="/favicon.ico">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">

    <link rel="stylesheet"
          href="../../../../dist/css/sm.min.css">
    <link rel="stylesheet"
          href="../../../../dist/css/sm-extend.min.css">
    <link rel="stylesheet"
          href="../../../../static/css/frontend/productDetail.css">
    <script type='text/javascript'
            src='../../../../dist/js/zepto.min.js' charset='utf-8'></script>

</head>
<body>
    <div class="page-group">
        <div class="page">
            <header class="bar bar-nav">
                <a class="button button-link button-nav pull-left" external href="#"
                   onClick="javascript :history.back(-1);" data-transition='slide-out'>
                    <span class="icon icon-left"></span> 返回
                </a>
                <h1 class="title">商品详情</h1>
            </header>
            <nav class="bar bar-tab">
                <a class="tab-item" href="/frontend/index" external> <span
                        class="icon icon-home"></span> <span class="tab-label">首页</span>
                </a> <a class="tab-item" href="#" id="me"> <span
                    class="icon icon-me"></span> <span class="tab-label">我</span>
            </a>
            </nav>
            <div class="content infinite-scroll infinite-scroll-bottom"
                 data-distance="100">
                <!-- 这里是页面内容区 -->
                <div class="shop-detail-dev">
                    <div class="card">
                        <div valign="bottom"
                             class="card-header color-white no-border no-padding">
                            <img id="product-img" class='card-cover' src="" alt="">
                        </div>
                        <div class="card-content">
                            <div class="card-content-inner">
                                <p class="color-gray">
                                    <span id="product-time"></span>
                                </p>
                                <p id="product-name"></p>
                                <p id="product-desc"></p>
                            </div>
                        </div>
                        <div class="card-footer" id="imgList">
                            <!-- <a href="#" class="link">赞</a> -->
                            <!-- <a href="#" class="link">更多</a> -->
                        </div>
                    </div>
                </div>



                <div class="list-div"></div>
            </div>
        </div>

        <div class="panel-overlay"></div>
        <div class="panel panel-right panel-reveal" id="panel-left-demo">
            <div class="content-block">
                <p>
                    <a href="/myo2o/frontend/myrecord" class="close-panel">消费记录</a>
                </p>
                <p>
                    <a href="/myo2o/frontend/mypoint" class="close-panel">我的积分</a>
                </p>
                <p>
                    <a href="/myo2o/frontend/pointrecord" class="close-panel">积分兑换记录</a>
                </p>
                <!-- Click on link with "close-panel" class will close panel -->
            </div>
        </div>
    </div>


    <script type='text/javascript'
            src='../../../../dist/js/sm.min.js' charset='utf-8'></script>
    <script type='text/javascript'
            src='../../../../dist/js/sm-extend.min.js' charset='utf-8'></script>
    <script type="text/javascript" src="../../../../dist/js/sm-city-picker.min.js"
            charset="UTF-8"></script>
    <script type='text/javascript'
            src='../../../../static/js/common/common.js' charset='utf-8'></script>
    <script type='text/javascript'
            src='../../../../static/js/frontend/productDetail.js' charset='utf-8'></script>
</body>
</html>
