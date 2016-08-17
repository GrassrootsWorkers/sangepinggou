<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="format-detection" content="telephone=no, email=no">
    <meta content="no" name="apple-mobile-web-app-capable">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="shortcut icon" type="image/x-icon" href="http://p.sangepg.com/images/favicon.ico">
    <meta name="keywords" content="三个苹果">
    <meta name="description" content="">
    <title>${fruit.brandName}-${fruit.typeName}</title>
    <link rel="stylesheet" href="http://s.sangepg.com/css/spellgroup.min.css?v=1.000">
    <link rel="stylesheet" href="http://s.sangepg.com/css/temp.css">
    <style>
        .i_price {
            color: #f55;
            font-size: 18px;
            font-family: Arial;
        }
        .order-numbers {
            display: inline-block;
            position: absolute;
            top: 1px;
            background-color: #f23030;
            line-height: 10px;
            font-style: normal;
            border-radius: 8px;
            padding: 0 4px;
            font-size: 8px;
            color: #fff;
            right: -1px;
            border: 1px solid #fff;
        }
    </style>
</head>
<body>
<article class="container autoScroll" id="container">
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
                <p class="marketValue">重 &nbsp;&nbsp;&nbsp;量：<i class="i_price">${fruit.weight} kg</i></p>
            </div>
            <#--
            <div class="goodsInfo clearfix">
                <p class="marketValue">市场价：<i class="i_price">&yen; ${fruit.marketPrice} 元/kg</i></p>
            </div>-->
            <div class="goodsInfo clearfix">
                <p class="marketValue">采摘时间：<i class="i_price">${fruit.harvestTime?string('yyyy-MM-dd')}</i></p>
            </div>
            <div class="groupPrice clearfix">
                <p><i class="i_ico01"></i><span>市场价</span><i class="i_price">&yen;${fruit.marketPrice} 元/kg</i></p>
                <span id="optFunction" onclick="addCart('fruit.fruitCode')" data-id="1612" class="groupBtn"><span id="optname"></span>我要抽奖<i class="ico_circle1"></i><i class="ico_circle2"></i></span>
            </div>

        </div>
        <!--#include virtual="/block/${fruit.baseInfoPath}"-->

</article>


<div class="homeIcon">
    <a href="#" onclick="">
        <i class="order-numbers" id="carNum">2</i>
    </a>
</div>
<div class="myCenter">
    <a href="http://wx.360haoyao.com/wgroup/ucenter.action"></a>
</div>
</body>
</html>
