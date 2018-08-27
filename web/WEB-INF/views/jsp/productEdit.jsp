<%--
  Created by IntelliJ IDEA.
  User: wwx
  Date: 2018/8/23
  Time: 17:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>商品编辑</title>
    <meta name="viewport" content="initial-scale=1, maximum-scale=1">
<%--    <link rel="shortcut icon" href="/favicon.ico">--%>
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">

    <link rel="stylesheet" href="${APP_PATH}/dist/css/sm-extend.min.css"/>
    <link rel="stylesheet" href="${APP_PATH}/dist/css/sm.min.css"/>
    <script type="text/javascript" src="${APP_PATH}/dist/js/zepto.min.js"></script>
</head>
<%
    pageContext.setAttribute("APP_PATH",request.getContextPath());
%>
<body>
    <header class="bar bar-nav">
        <h1 class="title">商品编辑</h1>
    </header>
    <div class="content">
        <div class="list-block">
            <ul>
                <li>
                    <div class="item-content">
                        <div class="item-media">
                            <i class="icon icon-form-name"></i>
                        </div>
                        <div class="item-inner">
                            <div class="item-title label">商品名称</div>
                            <div class="item-input">
                                <input type="text" id="product-name" placeholder="商品名称">
                            </div>
                        </div>
                    </div>
                </li>
                <li>
                    <div class="item-content">
                        <div class="item-media">
                            <i class="icon icon-form-email"></i>
                        </div>
                        <div class="item-inner">
                            <div class="item-title label">目录</div>
                            <div class="item-input">
                                <select id="category">
                                </select>
                            </div>
                        </div>
                    </div>
                </li>
                <li>
                    <div class="item-content">
                        <div class="item-media">
                            <i class="icon icon-form-email"></i>
                        </div>
                        <div class="item-inner">
                            <div class="item-title label">优先级</div>
                            <div class="item-input">
                                <input type="number" id="priority" placeholder="数字越大越排前面">
                            </div>
                        </div>
                    </div>
                </li>
                <li>
                    <div class="item-content">
                        <div class="item-media">
                            <i class="icon icon-form-email"></i>
                        </div>
                        <div class="item-inner">
                            <div class="item-title label">原价</div>
                            <div class="item-input">
                                <input type="number" id="normal-price" placeholder="可选">
                            </div>
                        </div>
                    </div>
                </li>
                <li>
                    <div class="item-content">
                        <div class="item-media">
                            <i class="icon icon-form-email"></i>
                        </div>
                        <div class="item-inner">
                            <div class="item-title label">现价</div>
                            <div class="item-input">
                                <input type="number" id="promotion-price" placeholder="可选">
                            </div>
                        </div>
                    </div>
                </li>
                <li>
                    <div class="item-content">
                        <div class="item-media">
                            <i class="icon icon-form-email"></i>
                        </div>
                        <div class="item-inner">
                            <div class="item-title label">缩略图</div>
                            <div class="item-input">
                                <input type="file" id="small-img">
                            </div>
                        </div>
                    </div>
                </li>
                <li>
                    <div class="item-content">
                        <div class="item-media">
                            <i class="icon icon-form-email"></i>
                        </div>
                        <div class="item-inner detail-img-div">
                            <div class="item-title label">详情图片</div>
                            <div class="item-input" id="detail-img">
                                <input type="file" class="detail-img">
                                <!-- <input type="file" class="detail-img" id="detail-img-1">
                                <input type="file" class="detail-img" id="detail-img-2"> -->
                            </div>
                        </div>
                    </div>
                </li>
                <li>
                    <div class="item-content">
                        <div class="item-media">
                            <i class="icon icon-form-email"></i>
                        </div>
                        <div class="item-inner">
                            <div class="item-title label">商品描述</div>
                            <div class="item-input">
                                <textarea id="product-desc" placeholder="商品描述"></textarea>
                            </div>
                        </div>
                    </div>
                </li>
                <li>
                    <div class="item-content">
                        <div class="item-media">
                            <i class="icon icon-form-email"></i>
                        </div>
                        <div class="item-inner">
                            <label for="j_captcha" class="item-title label">验证码</label> <input
                                id="j_captcha" name="j_captcha" type="text"
                                class="form-control in" placeholder="验证码" />
                            <div class="item-input">
                                <img id="captcha_img" alt="点击更换" title="点击更换"
                                     onclick="changeVerifyCode(this)" src="../Kaptcha"/>
                            </div>
                        </div>
                    </div>
                </li>

            </ul>
        </div>
        <div class="content-block">
            <div class="row">
                <div class="col-50">
                    <a href="${APP_PATH}/shop/productManage"
                       class="button button-big button-fill button-danger" id="back">返回商品管理</a>
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
    <script type='text/javascript'
            src='${APP_PATH}/static/js/common/common.js' charset='utf-8'></script>
    <script type='text/javascript'
            src='${APP_PATH}/static/js/shop/productEdit.js' charset='utf-8'></script>

</body>
</html>
