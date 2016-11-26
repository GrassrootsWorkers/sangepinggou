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
    <title>评论页</title>
    <link rel="stylesheet" href="http://s.sangepg.com/plugins/raty/css/jquery.raty.css">
    <script src="http://s.sangepg.com/js/jquery/jquery-3.0.0.min.js"></script>
    <script src="http://s.sangepg.com/plugins/raty/js/jquery.raty.js"></script>
    <link rel="stylesheet" href="http://m.sangepg.com/plugins/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="http://s.sangepg.com/css/360/global.css">
    <link rel="stylesheet" href="http://s.sangepg.com/css/360/alerts.css">
    <style>
        .bottom_btn a {
            display: block;
            width: 90%;
            padding: 15px;
            background-color: #f55;
            color: #fff;
            text-align: center;
            margin: 0 auto;
            font-size: 18px;
            line-height: normal;
        }
        .panel-body {
            padding: 12px 15px;
        }
        .panel {
            margin-bottom: 10px;
            background-color: #fff;
            border: 1px solid transparent;
            border-radius: 4px;
            -webkit-box-shadow: 0 1px 1px rgba(0, 0, 0, .05);
            box-shadow: 0 1px 1px rgba(0, 0, 0, .05);
        }
    </style>

</head>
<body>
<div id="container">
    <header id="gHeader" style="margin-top: 0px;" class="clearfix">
        <div class="h_prev"><a href="javascript:historyUtils.back();"></a> </div>
        <h1 style="margin-top: 0px;">此时此刻您的感受</h1>
    </header>
    <div class="panel panel-default">

        <table class="table" >

            <tr align="center"><td>评价人数</td><td>总评</td><td>口感</td><td>甜度</td><td>水分</td></tr>
            <tr align="center"><td>${avg.reviewCount}人</td><td>${avg.avgStarLevel}分</td><td>${avg.avgTasteScore}分</td><td>${avg.avgSugarScore}分</td><td>${avg.avgWaterScore}分</td></tr>
        </table>
    </div>
    <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title">
                口感
            </h3>
        </div>
        <div class="panel-body">
            <div id="taste_star"  ></div>
        </div>

    </div>
    <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title">
                甜度
            </h3>
        </div>
        <div class="panel-body">
            <div id="sugar_star"  ></div>
        </div>
    </div>
    <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title">
                水分
            </h3>
        </div>
        <div class="panel-body">
            <div id="water_star"></div>
        </div>
    </div>
    <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title">
                给个总评吧&nbsp;<small>评分有利于给你提供更好的水果</small>
            </h3>
        </div>
        <div class="panel-body">
            <div id="star"></div>
        </div>
    </div>
    <div class="clear"></div>
    <div class="bottom_btn">
        <div class="form-group">
            <a href="javascript:void(0);" onclick="saveReview()" class="btn-block">保存</a>
        </div>
    </div>
</div>


</body>

<script src="http://s.sangepg.com/js/jquery/jquery.cookie.js"></script>
<script src="http://s.sangepg.com/js/jquery/jquery.alerts.js"></script>
<script src="http://s.sangepg.com/js/bootstrap/bootstrap.min.js"></script>
<script type="text/javascript" src="http://s.sangepg.com/js/360/global.js"></script>
<script>
    $(function(){
        $('#taste_star').raty(
                {
                    half: false,
                    starOff: 'http://p.sangepg.com/images/h5/review/taste.png',
                    starOn : 'http://p.sangepg.com/images/h5/review/taste_1.png',
                    scoreName: 'tasteScore',
                }
        );
        $('#sugar_star').raty(
                {
                    half: false,
                    starOff: 'http://p.sangepg.com/images/h5/review/taste.png',
                    starOn : 'http://p.sangepg.com/images/h5/review/taste_1.png',
                    scoreName: 'sugarScore'
                }
        );
        $('#water_star').raty(
                {
                    half: false,
                    starOff: 'http://p.sangepg.com/images/h5/review/taste.png',
                    starOn : 'http://p.sangepg.com/images/h5/review/taste_1.png',
                    scoreName: 'waterScore'

                }
        );
        $('#star').raty(
                {
                    half: false,
                    starOff: 'http://p.sangepg.com/images/h5/review/taste.png',
                    starOn : 'http://p.sangepg.com/images/h5/review/taste_1.png',
                    scoreName: 'starLevel'
                }
        );
    });
    var if_review = false;
    function saveReview(){
        if(if_review){
            jAlert("您已经评论，谢谢");
            return ;
        }
        var mobile = jQuery.cookie("m");
        if (mobile == null) {
            jQuery.cookie('ReturnUrl', 'http://m.sangepg.com/front/review/otReview?fruitCode=${fruitCode}&farmerId= ${farmer.id}' ,
                    { path: '/', domain: 'sangepg.com', expires: 1}
            );
            window.location.href = "http://m.sangepg.com/user/user_login.html";
        }
        var data ={farmerId: ${farmer.id},fruitCode:'${fruitCode}'};
        $("input").each(function(index,value){
           var name = $(value).attr("name");
           var content = $(value).val();
            if(content =="") content = 5;
           data[name] = content;
        });
        $.ajax({
            type: "post",
            url: "http://m.sangepg.com/front/review/review",
            data: data,
            async: true,
            dataType: "json",
            success: function (data) {
                var flag = data.msg;
                if("success" == flag){
                    if_review = true;
                    jAlert("感谢您的评论");
                    //调到首页
                    window.location.href = "http://m.sangepg.com";
                }else{
                    window.location.href = "http://m.sangepg.com/user/user_login.html";
                }
            }

        });

    }
</script>
</html>
