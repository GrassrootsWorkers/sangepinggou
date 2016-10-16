<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="format-detection" content="telephone=no, email=no">
    <meta content="no" name="apple-mobile-web-app-capable">
    <meta name="viewport"  content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="shortcut icon" type="image/x-icon" href="http://p.sangepg.com/images/favicon.ico">
    <meta name="keywords" content="三个苹果">
    <meta name="description" content="放心健康的水果">
    <title>${fruit.brandName}-${fruit.typeName}</title>
    <link rel="stylesheet" href="http://s.sangepg.com/css/spellgroup.min.css?v=1.000">
    <link rel="stylesheet" href="http://s.sangepg.com/css/temp.css">
    <link rel="stylesheet" href="http://s.sangepg.com/css/360/global.css">
</head>
<body>
<article class="container autoScroll" id="container">
    <header id="gHeader" class="clearfix">
        <div class="h_prev"><a href="javascript:historyUtils.back();"></a></div>
        <h1>水果的真实信息</h1>
    </header>
    <div class="wrapper">
        <div class="goodsDetail">
            <div class="slider" id="slider">
                <ul class="slideList swipe-wrap clearfix">
                    <li class="item">
                        <img src="${fruit.image}" alt="${fruit.brandName}">
                    </li>
                </ul>
                <div class="icolist" id="sliderPos"></div>
                <span class="doneSpan"></span>
            </div>
            <div class="goodsName">
                <p class="dt">${fruit.brandName}${fruit.varietyName}</p>
            </div>
            <div class="goodsInfo clearfix">
                <p class="marketValue">重 &nbsp;&nbsp;&nbsp;量：<i class="i_price">${fruit.weight} g/${fruit.unit}</i></p>
            </div>
        <#--
        <div class="goodsInfo clearfix">
            <p class="marketValue">市场价：<i class="i_price">&yen; ${fruit.marketPrice} 元/kg</i></p>
        </div>-->
            <div class="goodsInfo clearfix">
                <p class="marketValue">采摘时间：<i class="i_price">${fruit.harvestTime?string('yyyy-MM-dd')}</i></p>
            </div>
            <div class="groupPrice clearfix"> <#--<i class="i_ico01"></i>-->
                <p><span>市场价</span><i class="i_price">&yen;${fruit.marketPrice} 元/kg</i></p>
                <span id="optFunction" onclick="addCart('${fruit.fruitCode}')" data-id="1612" class="groupBtn">
                    <span id="optname"></span>放入篮子<i class="ico_circle1"></i><i class="ico_circle2"></i>
                </span>
            </div>

        </div>
        <!--#include virtual="${fruit.baseInfoPath}"-->

</article>

<div class="homeIcon">
    <a href="#" onclick="listCart()">
        <i class="order-numbers" id="carNum"></i>
    </a>
</div>
<div class="myCenter">
    <a href="http://m.sangepg.com/user/user_login.html"></a>
</div>
<script src="http://s.sangepg.com/js/jquery/jquery-3.0.0.min.js"></script>
<script src="http://s.sangepg.com/js/jquery/jquery.cookie.js"></script>
<script type="text/javascript" src="http://s.sangepg.com/js/360/global.js"></script>
<script>
    $(function () {
        var mobile = jQuery.cookie("mobile");
        if (mobile == null) {
            jQuery.cookie('ReturnUrl', '${fruit.filePath}' , {
                path: '/', domain: 'sangepg.com', expires: 1
            });
        } else {
            $(".myCenter").hide();
        }
        ifAdd("${fruit.fruitCode}");
    });
    function addCart(code) {
        var mobile = jQuery.cookie("mobile");
        if (mobile == null) {
            window.location.href = "http://m.sangepg.com/user/user_login.html";
        } else {
            $.ajax({
                type: "post",
                url: "http://m.sangepg.com/front/cart/add",
                data: {
                    "fruitCode": code
                },
                async: false,
                dataType:"json",
                success: function (data) {
                    if(data.success){
                        if(data.cartCount >0){
                            $("#carNum").html(data.cartCount);
                        }else{
                            $("#carNum").html("");
                        }
                        $("#optFunction").html("<span id='optname'></span>移出篮子<i class='ico_circle1'></i><i class='ico_circle2'></i>");
                        $("#optFunction").attr("onclick","delCart('"+code+"')");
                    }else{
                        if(data.msg=="login"){
                            window.location.href = "http://m.sangepg.com/user/user_login.html";
                        }
                    }

                }
            });
        }
    }
    function ifAdd(fruitCode){
        $.ajax({
            type: "post",
            url: "http://m.sangepg.com/front/cart/ifAdd",
            data: {
                "fruitCode": fruitCode
            },
            async: false,
            dataType:"json",
            success: function (data) {
                if(data.success){
                    if(data.cartCount >0){
                        $("#carNum").html(data.cartCount);
                    }else{
                        $("#carNum").html("");
                    }

                    $("#optFunction").html("<span id='optname'></span>移出篮子<i class='ico_circle1'></i><i class='ico_circle2'></i>");
                    $("#optFunction").attr("onclick","delCart('"+fruitCode+"')");
                }
            }
        });
    }
    function delCart(fruitCode){
        $.ajax({
            type: "post",
            url: "http://m.sangepg.com/front/cart/del",
            data: {
                "fruitCode": fruitCode
            },
            async: false,
            dataType:"json",
            success: function (data) {
                if(data.success){
                    if(data.cartCount >0){
                        $("#carNum").html(data.cartCount);
                    }else{
                        $("#carNum").html("");
                    }
                    $("#optFunction").html("<span id='optname'></span>放入篮子<i class='ico_circle1'></i><i class='ico_circle2'></i>");
                    $("#optFunction").attr("onclick","addCart('"+fruitCode+"')");
                }else{
                    if(data.msg =="login"){
                        window.location.href = "http://m.sangepg.com/user/user_login.html";
                    }
                }

            }
        });
    }
    function listCart(){
        window.location.href="http://m.sangepg.com/front/cart/list";
    }
</script>
</body>
</html>
