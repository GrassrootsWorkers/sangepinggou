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
    <link rel="stylesheet" href="http://s.sangepg.com/css/floatbanner.css">
</head>
<body>
<div class="float_banner" id="floatBanner">
    <div class="logo"></div>
    <div class="banner_label">
        <p>贴心服务尽在微信公众号</p>
        <p class="sm"><span>放心</span><span>实惠</span><span>快捷</span></p></div>
    <a class="close_btn" onclick="close_div()"></a>
    <a href="https://mp.weixin.qq.com/mp/profile_ext?action=home&__biz=MzA5MzYzOTgyOQ==&sn=67d7f1b106ec79c6265f5ce2bbc52f1&scene=1##wechat_redirect" class="open" pt_pos_lid="float_banner_download">立即关注</a>
</div>
<article class="container autoScroll" id="container">
    <header id="gHeader" style="margin-top: 0px;height:47px"  class="clearfix">
        <div class="h_prev"><a href="javascript:historyUtils.back();"></a></div>
        <h1 style="margin-top: 0px;">水果的真实信息</h1>
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
                <p class="dt">${fruit.brandName}${fruit.varietyName}&nbsp;<i class="i_price" id="praise"></i></p>
            </div>
            <div class="goodsInfo clearfix">
                <p class="marketValue">重 &nbsp;&nbsp;&nbsp;量：<i class="i_price">${fruit.weight} g/${fruit.unit}</i></p>
            </div>

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
<!--评论相关-->


<div class="homeIcon">
    <a href="#" onclick="giveThumbs(${fruit.farmerId})"></a>
</div>
<div class="myCenter">
    <a href="#" onclick="toReview('${fruit.fruitCode}',${fruit.farmerId})"></a>
</div>
<!--购物车相关-->
<div class="mycart">
    <a href="#" onclick="listCart()">
        <i class="order-numbers" id="carNum"></i>
    </a>
</div>

<script src="http://s.sangepg.com/js/jquery/jquery-3.0.0.min.js"></script>
<script src="http://s.sangepg.com/js/jquery/jquery.cookie.js"></script>
<script type="text/javascript" src="http://s.sangepg.com/js/360/global.js"></script>
<script>
    var praiseCount = 1;
    $(function () {
        pageView("${fruit.fruitCode}",${fruit.farmerId});
        var mobile = jQuery.cookie("mobile");
        if (mobile == null) {
            //从系统里生成一个手机号：
            var tempMobile = getNowFormatDate();
            jQuery.cookie('mobile', tempMobile , {
                path: '/', domain: 'sangepg.com', expires: 1
            });
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
    function getNowFormatDate() {
        var date = new Date();
        var month = date.getMonth() + 1;
        var strDate = date.getDate();
        if (month >= 1 && month <= 9) {
            month = "0" + month;
        }
        if (strDate >= 0 && strDate <= 9) {
            strDate = "0" + strDate;
        }
        var currentDate = date.getYear()  +""+ month  +""+ strDate + date.getHours()  + "" +date.getMinutes() + date.getSeconds();
        return currentDate;
    }

    function pageView(fruitCode,farmerId){
        $.ajax({
            type: "post",
            url: "http://m.sangepg.com/front/cart/pageView",
            data: {
                "fruitCode": fruitCode,
                "farmerId": farmerId
            },
            async: true,
            dataType:"json",
            success: function (data) {
                praiseCount = data.praiseCount;
                $("#praise").html(praiseCount+"人点赞")
            }
        });
    }
    function giveThumbs(farmerId){
        praiseCount = praiseCount+1;
        $("#praise").html(praiseCount + "人点赞")
        $.ajax({
            type: "post",
            url: "http://m.sangepg.com/front/review/praise",
            data: {
                "farmerId": farmerId
            },
            async: true,
            dataType: "json"

        });
    }
    function toReview(fruitCode,farmerId){
        var mobile = jQuery.cookie("mobile");
        if (mobile == null) {
            jQuery.cookie('ReturnUrl', 'http://m.sangepg.com/front/review/otReview?fruitCode='+fruitCode+'&farmerId='+farmerId ,
                    { path: '/', domain: 'sangepg.com', expires: 1}
            );
            window.location.href = "http://m.sangepg.com/user/user_login.html";
        } else{
            window.location.href = 'http://m.sangepg.com/front/review/toReview?fruitCode='+fruitCode+'&farmerId='+farmerId;
        }
    }
    function close_div(){
        $("#floatBanner").hide();
    }
</script>
</body>
</html>
