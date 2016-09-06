<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="format-detection" content="telephone=no, email=no">
    <meta content="no" name="apple-mobile-web-app-capable">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="shortcut icon" type="image/x-icon" href="http://p.sangepg.com/images/favicon.ico">
    <meta name="keywords" content="三个苹果">
    <meta name="description" content="">
    <title>我的篮子</title>
    <link rel="stylesheet" href="http://s.sangepg.com/css/360/global.css">
    <link rel="stylesheet" href="http://s.sangepg.com/css/360/cart_new.css">
    <style>
        .num {
            width: 40px;
            font-size: 13px;
            border: 0;
            border-radius: 0;
            border-top: 1px solid #e6e6e6;
            border-bottom: 1px solid #e6e6e6;
            text-align: center;
            height: 28px;
            float: left;
            background: #fff;
            -webkit-box-sizing: border-box;
            -moz-box-sizing: border-box;
            box-sizing: border-box;
        }
    </style>
    <script src="http://s.sangepg.com/js/jquery/jquery-3.0.0.min.js"></script>
    <script src="http://s.sangepg.com/js/jquery/jquery.cookie.js"></script>
    <script src="http://s.sangepg.com/js/jquery/jquery.alerts.js"></script>
    <script src="http://s.sangepg.com/js/bootstrap/bootstrap.min.js"></script>
    <script src="http://s.sangepg.com/js/360/global.js"></script>
    <script src="http://s.sangepg.com/js/cart/cart.js"></script>
</head>
<body class="content pd-50">
<article id="container">
    <header id="h5-header" class="g-header">
        <div class="g-back">
            <a href="javascript:historyUtils.back();;"><i></i></a>
        </div>
        <div class="header-nav">
            <ul class="header-tab">
                我的篮子
            </ul>
        </div>
    </header>
    <div id="main" class="cartBody cartMain">
        <c:if test="${carts !=null}">
            <c:forEach items="${carts}" var="cart">
                <div class="cart-shop" id="51">
                    <div class="cart-shop-title">
                        <div onclick="checkCartAll(this,'${cart.cartGroup}')" class="shop-checkbox shop-title-checkbox">
                            <c:if test="${fn:length(cart.noWishesBuyFruitList) >0}">
                                <input id="${cart.cartGroup}" type="checkbox">
                            </c:if>
                            <c:if test="${fn:length(cart.noWishesBuyFruitList) <=0}">
                                <input id="${cart.cartGroup}" checked="checked" type="checkbox">
                            </c:if>
                            <label for="shop01"></label>
                        </div>
                        <p class="shop-title-shopname fl">${cart.cartGroupName}</p>
                        <p class="shop-title-edit fr">
                            <input type="text" name="sale_price" lang="${cart.cartGroup}" class="num"
                                   onblur="moneyCheck(this)" value="${cart.salePrice}">
                        </p>
                        <p class="shop-title-link fr"><span class="red">价格：</span></p>
                        <p class="shop-title-link fr"><span class="red"><i>￥</i>${cart.saleTotalPrice}<i class="postage">(${cart.totalCount}个)</i></span>
                        </p>
                    </div>
                    <c:forEach items="${cart.noWishesBuyFruitList}" var="fruit">
                        <div class="cart-shop-items">
                            <div class="cart-shop-item">
                                <div class="shop-checkbox shop-item-checkbox" onclick="checkItemStatus(this)">
                                    <input id="${fruit.fruitCode}" type="checkbox" name="nobuy_${cart.cartGroup}"
                                           value="${fruit.fruitCode}">
                                    <label for="shop01-1"></label>
                                </div>
                                <a class="shop-item-img" href="${fruit.filePath}">
                                    <img onerror="javascript:this.src='http://p.sangepg.com/images/favicon.ico'"
                                         src="${fruit.smallImage}" alt="">
                                </a>
                                <div class="shop-item-info">
                                    <p href="href="${fruit.filePath}"" class="shop-item-name">
                                            ${fruit.typeName}-${fruit.brandName}
                                    </p>
                                    <p class="shop-item-option">
                                        <span><i>重量：</i>${fruit.weight}g</span>
                                    </p>
                                    <p class="shop-item-price">
                                        <span class="shop-item-num">价格：<i>￥</i>${fruit.totalPrice}</span>
                                        <span class="shop-item-count">x1</span>
                                    </p>
                                </div>

                            </div>
                        </div>
                    </c:forEach>
                    <c:forEach items="${cart.wishesBuyFruitList}" var="fruit">
                        <div class="cart-shop-items">
                            <div class="cart-shop-item">
                                <div class="shop-checkbox shop-item-checkbox" onclick="checkItemStatus(this)">
                                    <input id="${fruit.fruitCode}" checked="checked" type="checkbox"
                                           name="buy_${cart.cartGroup}" value="${fruit.fruitCode}">
                                    <label for="shop01-1"></label>
                                </div>
                                <a class="shop-item-img" href="${fruit.filePath}">
                                    <img onerror="javascript:this.src='http://p.sangepg.com/images/favicon.ico'"
                                         src="${fruit.smallImage}" alt="">
                                </a>
                                <div class="shop-item-info">
                                    <p href="#" class="shop-item-name">
                                            ${fruit.typeName}-${fruit.brandName}
                                    </p>
                                    <p class="shop-item-option">
                                        <span><i>重量：</i>${fruit.weight}g</span>
                                    </p>
                                    <p class="shop-item-price">
                                        <span class="shop-item-num">价格：<i>￥</i>${fruit.totalPrice}</span>
                                        <span class="shop-item-count">x1</span>
                                    </p>
                                </div>

                            </div>
                        </div>
                    </c:forEach>
                </div>
            </c:forEach>
        </c:if>
        <div class="contact_fixed cartBottom">
            <span class="red">合计：${totalPrice}<i class="postage">(${totalCount}个)</i></span>
            <%-- <a href="javascript:void(0)" id="goToShoppingCart" class="red_btn">去结算</a>--%>
        </div>
    </div>
    <span></span>
</article>
</body>
</html>

