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
    <title>完善信息</title>
    <link rel="stylesheet" href="http://s.sangepg.com/css/bootstrap/css/bootstrap.css">
    <%--  <link rel="stylesheet" href="http://s.sangepg.com/css/360/alerts.css">--%>
    <script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
    <link rel="stylesheet" href="http://s.sangepg.com/css/360/global.css">
    <link rel="stylesheet" href="http://s.sangepg.com/css/360/cart_new.css">
</head>
<body style="background: #ebebeb">
<article id="container">
    <div class="col-lg-4" style="background: #fff">
        <br/>
        <div class="alert alert-warning" id="alert_div" style="display: none">
            <a class="close" data-dismiss="alert">
                &times;
            </a>
            <div id="alert_content"><strong>恭喜！</strong>提交成功。</div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">您的名字:</label>
            <div class="col-sm-10" style="display: inline-block; width: 250px;">
                <input type="text" class="form-control" id="name" name="partnerName" placeholder="请输入您的称名字">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">您的手机号:</label>
            <div class="col-sm-10" style="display: inline-block; width: 250px;">
                <input type="text" class="form-control" id="mobile" name="mobile" placeholder="请输入您的手机号">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">您的地址:</label>
            <div class="col-sm-10" style="display: inline-block; width: 250px;">
                <input type="text" class="form-control" id="address" name="address" placeholder="请输入您的水果店地址">
            </div>
        </div>
        <input type="hidden" id="lat" name="lat">
        <input type="hidden" id="lon" name="lon">
        <input type="hidden" id="open_id" name="openId" value="${openId}">

        <div class="bottom_btn">
            <div class="form-group">
                <a href="javascript:void(0);" onclick="submitInfo()" class="btn btn-block"><i
                        style="font-weight:bold;">提交资料</i></a>
            </div>
        </div>
    </div>
</article>

</body>
<script src="http://s.sangepg.com/js/jquery/jquery-3.0.0.min.js"></script>
<script src="http://s.sangepg.com/js/bootstrap/bootstrap.min.js"></script>
<script src="http://s.sangepg.com/js/jquery/jquery.cookie.js"></script>
<script src="http://s.sangepg.com/js/360/global.js"></script>
<%--<script src="http://s.sangepg.com/js/jquery/jquery.alerts.js"></script>--%>

<script>
    var repeat_flag = false;
    $(function () {
        wx.config({
            debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
            appId: 'wx81cb60beb04da0f8', // 必填，公众号的唯一标识
            timestamp: ${timestamp}, // 必填，生成签名的时间戳
            nonceStr: '${nonceStr}', // 必填，生成签名的随机串
            signature: '${sign}',// 必填，签名，见附录1
            jsApiList: ["getLocation"] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
        });
        wx.ready(function () {
            wx.getLocation({
                type: 'gcj02', // 默认为wgs84的gps坐标，如果要返回直接给openLocation用的火星坐标，可传入'gcj02'
                success: function (res) {
                    $("#lat").val(res.latitude); // 纬度，浮点数，范围为90 ~ -90
                    $("#lon").val(res.longitude); // 纬度，浮点数，范围为90 ~ -90
                }

            });

        });

    });
    function submitInfo() {
        if (repeat_flag) {
            $("#alert_div").show();
            $("#alert_content").html("<strong>提示！</strong>资料已经提交。");
            return;
        }
        $.ajax({
            url: "/front/partner/info",
            type: "post",
            dataType: "json",
            async: false,
            data: {
                mobile: jQuery("#mobile").val(),
                openId: jQuery("#open_id").val(),
                partnerName: jQuery("#name").val(),
                address: jQuery("#address").val(),
                lat: $("#lat").val(),
                lon: $("#lon").val()
            },
            success: function (data) {
                if (data.code == 200) {
                    $("#alert_div").show();
                    repeat_flag = true;
                } else {

                }
            }
        });
    }
</script>


</html>
