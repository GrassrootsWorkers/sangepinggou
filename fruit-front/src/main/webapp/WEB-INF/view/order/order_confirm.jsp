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
    <title>订单确认页</title>
    <link rel="stylesheet" href="http://s.sangepg.com/css/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="http://s.sangepg.com/css/360/login.css">
    <link rel="stylesheet" href="http://s.sangepg.com/css/360/global.css">
    <link rel="stylesheet" href="http://s.sangepg.com/css/360/alerts.css">
</head>
<body >
<article id="container">
    <header id="gHeader" style="margin-top: 0px;" class="clearfix">
        <div class="h_prev"><a href="javascript:historyUtils.back();"></a>
        </div>
        <h1 style="margin-top: 0px;">此时此刻您的感受</h1>
    </header>
    <div class="container add_address">
        <form class="" role="form">
            <div class="form-group">
                <input type="text" autofocus="autofocus" value="" required="" id="sendPeople" name="addressInfo.realName" placeholder="收货人姓名" class="form-control u_name">
            </div>


            <div class="bottom_btn">
                <div class="form-group">
                    <a href="javascript:void(0);" onclick="" class="btn-block">保存</a>
                </div>
            </div>

        </form>
    </div>
</article>
<script src="http://s.sangepg.com/js/jquery/jquery-3.0.0.min.js"></script>
<script src="http://s.sangepg.com/js/jquery/jquery.cookie.js"></script>
<script src="http://s.sangepg.com/js/jquery/jquery.alerts.js"></script>
<script src="http://s.sangepg.com/js/bootstrap/bootstrap.min.js"></script>
<script>
    $(function () {
        jAlert("eeee")
    });
</script>


</body>

</html>
