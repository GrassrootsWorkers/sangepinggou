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
    <link rel="stylesheet" href="http://s.sangepg.com/css/360/global.css">
    <link rel="stylesheet" href="http://s.sangepg.com/css/bootstrap/css/bootstrap.css">

    <link rel="stylesheet" href="http://s.sangepg.com/css/360/alerts.css">
    <link rel="stylesheet" href="http://s.sangepg.com/css/order.css">

</head>
<body style="background: #ebebeb" >
<article id="container">
    <header id="gHeader" style="margin-top: 0px;" class="clearfix">
        <div class="h_prev"><a href="javascript:historyUtils.back();"></a>
        </div>
        <h1 style="margin-top: 0px;">购物车</h1>
    </header>
    <div class="col-lg-4" style="background: #fff">
        <form class="form-horizontal" role="form">
            <br/>

            <div class="form-group">
                <label class="col-sm-2 control-label">收&nbsp;货&nbsp;人&nbsp;&nbsp;</label>
                <div class="col-sm-10" style="display: inline-block; width: 250px;">
                    <input type="text" class="form-control" id="name" placeholder="请输入收货人姓名">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">手机号码</label>
                <div class="col-sm-10" style="display: inline-block; width: 250px;">
                    <input type="text" class="form-control" id="mobile" placeholder="请输入收货人手机号">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">收货地址</label>
                <div class="col-sm-10" style="display: inline-block; width: 250px;">
                    <input type="text" class="form-control" id="address" placeholder="请输入收货人地址">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">苹果等级</label>
                <div class="col-sm-10" style="display: inline-block; width: 250px;">
                    <select class="form-control">
                        <option>一级(单重:250g-500g)</option>
                        <option>二级(单重:150g-250g)</option>
                        <option>三级(单重:100g-150g)</option>

                    </select>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">市场价格</label>
                <div class="col-sm-10" style="display: inline-block; width: 250px;">
                    <i class="i_price">&yen;&nbsp;8.12 元/kg</i>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">苹果重量</label>
                <div class="btn-group col-sm-10">
                    <button type="button" class="btn reduce">-</button>
                    <input class="num" autocomplete="off" min="0" max="50#else100" origiannum="1" status="0"
                           refmainitemid="0" itemtype="1" id="w7c100145340_1_0" maxlength="3" value="1kg"
                           onblur="prompt(this)" type="text">
                    <button type="button" class="btn add">+</button>
                </div>
            </div>

            <div class="bottom_btn">
                <div class="form-group" >
                    <a href="javascript:void(0);" onclick="" class="btn btn-block"><i
                            style="font-weight:bold;">合计:￥15</i></a>
                </div>
            </div>
        </form>
    </div>
</article>
<div class="col-lg-4" style="background: #fff">
    <div class="line_recom" id="user_ferry_data">
        <div class="bill">
            <div class="line_detail">
                <div class="title" onclick="">
                    <span class="name">烟台栖霞红富士(种植户-刘志)</span>
                    <span class="weight">5kg</span>
                    <span class="grade">一级</span>
                </div>
                <div class="line_intro" onclick="">
                    <div class="address">
                        <span class="place">北京市昌平区育知东路龙1</span>
                        <span class="place">刘志(186****2693)收</span>
                    </div>
                </div>
            </div>
            <div class="price form-group" onclick=""><i  style="font-weight:bold;">￥1500</i></div>
        </div>
    </div>
</div>
</body>
<script src="http://s.sangepg.com/js/jquery/jquery-3.0.0.min.js"></script>
<script src="http://s.sangepg.com/js/bootstrap/bootstrap.min.js"></script>
<script src="http://s.sangepg.com/js/jquery/jquery.cookie.js"></script>
<script src="http://s.sangepg.com/js/jquery/jquery.alerts.js"></script>

<script>
    $(function () {

    });
</script>


</html>
