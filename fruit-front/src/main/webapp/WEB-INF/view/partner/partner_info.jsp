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
    <link rel="stylesheet" href="http://s.sangepg.com/css/360/alerts.css">
</head>
<body style="background: #ebebeb" >
<article id="container">
    <header id="gHeader" style="margin-top: 0px;" class="clearfix">
        <div class="h_prev"><a href="javascript:historyUtils.back();"></a>
        </div>
        <h1 style="margin-top: 0px;">完善信息</h1>
    </header>
    <div class="col-lg-4" style="background: #fff">
        <form class="form-horizontal" role="form" action="/front">
            <br/>
            <div class="form-group">
                <label class="col-sm-2 control-label">法人姓名:</label>
                <div class="col-sm-10" style="display: inline-block; width: 250px;">
                    <input type="text" class="form-control" id="name" placeholder="水果店地址">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">法人手机号:</label>
                <div class="col-sm-10" style="display: inline-block; width: 250px;">
                    <input type="text" class="form-control" id="mobile" placeholder="手机号">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">地址:</label>
                <div class="col-sm-10" style="display: inline-block; width: 250px;">
                    <input type="text" class="form-control" id="address" placeholder="水果店地址">
                </div>
            </div>
            <div class="bottom_btn">
                <div class="form-group" >
                    <a href="javascript:void(0);" onclick="save()" class="btn btn-block"><i
                            style="font-weight:bold;">合计:￥15</i></a>
                </div>
            </div>
        </form>
    </div>
</article>

</body>
<script src="http://s.sangepg.com/js/jquery/jquery-3.0.0.min.js"></script>
<script src="http://s.sangepg.com/js/bootstrap/bootstrap.min.js"></script>
<script src="http://s.sangepg.com/js/jquery/jquery.cookie.js"></script>
<script src="http://s.sangepg.com/js/jquery/jquery.alerts.js"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script>
    var open_id = jQuery.cookie("open_id");
    $.ajax({
        type: "post",
        url: "http://m.sangepg.com/front/wx/commons/ticket",
        data: {
            "account": "partner",
            "url":"http://m.sanggepg.com/view/partner/partner_info.jsp"
        },
        async: false,
        dataType:"json",
        success: function (data) {
            if(data.success){
                wx.config({
                    debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
                    appId: 'wx81cb60beb04da0f8', // 必填，公众号的唯一标识
                    timestamp: data.timestamp, // 必填，生成签名的时间戳
                    nonceStr: data.nonceStr, // 必填，生成签名的随机串
                    signature: data.sign,// 必填，签名，见附录1
                    jsApiList: ["openLocation","getLocation"] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
                });
            }

        }
    });

    $(function () {
        wx.ready(function(){
            jConfirm('获取您当前位置', '', function(yes){
                        if(yes){
                            wx.getLocation({
                                type: 'wgs84', // 默认为wgs84的gps坐标，如果要返回直接给openLocation用的火星坐标，可传入'gcj02'
                                success: function (res) {
                                    var latitude = res.latitude; // 纬度，浮点数，范围为90 ~ -90
                                    var longitude = res.longitude; // 经度，浮点数，范围为180 ~ -180。
                                    var speed = res.speed; // 速度，以米/每秒计
                                    var accuracy = res.accuracy; // 位置精度
                                    jAlert(latitude)
                                }
                            });
                        }else{
                            jAlert(false);
                        }
                    }, '允许', '禁止');
        });
    });
</script>


</html>
