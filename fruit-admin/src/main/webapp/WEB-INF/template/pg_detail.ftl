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
        <#--
        <div class="goodsInfo clearfix">
            <p class="marketValue">市场价：<i class="i_price">&yen; ${fruit.marketPrice} 元/kg</i></p>
        </div>-->
            <div class="goodsInfo clearfix">
                <p class="marketValue">采摘时间：<i class="i_price">${fruit.harvestTime?string('yyyy-MM-dd')}</i></p>
            </div>
            <div class="goodsInfo clearfix">
                <p class="groupPrice">市场价：<i class="i_price">&yen;${fruit.marketPrice} 元/kg</i></p>
            </div>

        </div>
        <!--#include virtual="${fruit.baseInfoPath}"-->

</article>

<div class="homeIcon">
    <a href="#" onclick="giveThumbs(${fruit.farmerId})"></a>
</div>
<div class="myCenter">
    <a href="#" onclick="toReview('${fruit.fruitCode}',${fruit.farmerId})"></a>
</div>
<script src="http://s.sangepg.com/js/jquery/jquery-3.0.0.min.js"></script>
<script src="http://s.sangepg.com/js/jquery/jquery.cookie.js"></script>
<script type="text/javascript" src="http://s.sangepg.com/js/360/global.js"></script>
<script>
    var praiseCount = 1;
    $(function () {
        pageView("${fruit.fruitCode}",${fruit.farmerId});
    });
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
        var mobile = jQuery.cookie("m");
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
