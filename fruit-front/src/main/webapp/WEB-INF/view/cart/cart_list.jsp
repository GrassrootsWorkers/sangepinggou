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
                            <input type="text" name="sale_price" class="num" lang = "${cart.cartGroup}" onblur="moneyCheck(this)" value="${cart.salePrice}">
                        </p>
                        <p class="shop-title-link fr"><span class="red">单价：</span></p>
                        <p class="shop-title-link fr"><span class="red"><i>￥</i>${cart.saleTotalPrice}<i class="postage">(${cart.totalCount}个)</i></span>
                        </p>
                    </div>
                    <c:forEach items="${cart.noWishesBuyFruitList}" var="fruit">
                        <div class="cart-shop-items">
                            <div class="cart-shop-item">
                                <div class="shop-checkbox shop-item-checkbox" onclick="checkItemStatus(this)">
                                    <input id="${fruit.fruitCode}" type="checkbox" lang="${cart.cartGroup}" name="nobuy_${cart.cartGroup}"
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
                                        <span class="shop-item-num">支付金额：<i>￥</i>${fruit.totalPrice}</span>
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
                                           name="buy_${cart.cartGroup}" lang="${cart.cartGroup}" value="${fruit.fruitCode}">
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
                                        <span class="shop-item-num">支付金额：<i>￥</i>${fruit.totalPrice}</span>
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
          <a href="javascript:void(0)" id="goToShoppingCart" onclick="scan_pay(${totalPrice})" class="red_btn">扫&nbsp;码&nbsp;支&nbsp;付</a>
        </div>
    </div>
    <span></span>
</article>
</body>
<script src="http://s.sangepg.com/js/jquery/jquery-3.0.0.min.js"></script>
<script src="http://s.sangepg.com/js/jquery/jquery.cookie.js"></script>
<script src="http://s.sangepg.com/js/jquery/jquery.alerts.js"></script>
<script src="http://s.sangepg.com/js/bootstrap/bootstrap.min.js"></script>
<script src="http://s.sangepg.com/js/360/global.js"></script>
<script src="http://s.sangepg.com/js/cart/cart.js"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"/>
<script type="text/javascript">
    var timestamp = 0;
    var nonceStr = "";
    var signature = "";

    function scan_pay(total_money){
        var url = "http://m.sangepg.com/front/token/ticket?account=page&url=http://m.sangepg.com/front/cart/list";
        $.get(url, {}, function (data) {
            var json = eval(data);
            timestamp = json.timestamp;
            nonceStr = json.nonceStr;
            signature = json.signature;
        });
        wx.config({
            debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
            appId: 'wx23be1b9cbc0f1324', // 必填，公众号的唯一标识
            timestamp: timestamp , // 必填，生成签名的时间戳
            nonceStr: nonceStr, // 必填，生成签名的随机串
            signature: signature,// 必填，签名，见附录1
            jsApiList: [] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
        });
        wx.ready(function(){
            wx.scanQRCode({
                needResult: 1, // 默认为0，扫描结果由微信处理，1则直接返回扫描结果，
                scanType: ["qrCode","barCode"], // 可以指定扫二维码还是一维码，默认二者都有
                success: function (res) {
                    var result = res.resultStr; // 当needResult 为 1 时，扫码返回的结果
                    //

                }
            });
        });

    }
</script>
</html>

