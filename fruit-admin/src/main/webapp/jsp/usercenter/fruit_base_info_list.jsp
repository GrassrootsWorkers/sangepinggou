<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <!-- Meta Tags -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- Title -->
    <title>三个苹果</title>
    <meta name="Keywords" content="亿万中产阶级托付的平台">
    <meta name="Description" content="农村城市的纽带，吃出放心，吃出健康">
    <!-- Favicon -->
    <!-- Stylesheets -->
    <link href="http://s.sangepg.com/css/commons.css" rel="stylesheet" type="text/css">
    <link href="http://s.sangepg.com/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <script src="http://s.sangepg.com/js/jquery/jquery-3.0.0.js"></script>
    <link href="http://s.sangepg.com/css/personal.css" rel="stylesheet" type="text/css">
    <style>
        .delete_new {
            top: 30px;
            color: #bebebe;
            padding-left: 15px;
            height: 14px;
            line-height: 15px;
            position: absolute;
        }
    </style>
</head>

<body class="bgf6">
<!-- Container -->
<div class="main">
    <!--#include virtual="/html/header/header.html"-->
    <!--#include virtual="/html/user/user_info.html"-->
    <div class="content bg ">
        <div class="personal_top_nav">
            <div class="top_nav_con">
                <a href="javascript:;" class="notice">系统相关公告</a>
                <a href="javascript:;" class="r_help"><i></i>帮助中心</a>
            </div>
        </div>
        <div class="personal_con">
            <!--#include virtual="/html/user/user_left_menu.html"-->
            <div class="top_nav_con_r personal_r_tab">
                <c:if test="${information !=null}">
                    <div class="personal_pro">
                        <div class="message_top_nav" style="margin-bottom: 20px">
                            您共<i>${count}</i>条记录
                            <div class="r"><a href="http://www.sangepg.com/admin/fruit/info/toAdd">添加我的资料</a></div>
                        </div>
                        <ul class="sys_message_ul">
                            <c:forEach items="${information}" var="info">
                                <li class="sys_message_li" onchange="toEdit(${info.id})">
                                    <div class="c">
                                        <div class="tit">您种植的水果为：${info.typeName}-${info.brandName}-${info.varietyName}</div>
                                        <div style="color: #000;" class="reason">含水量：${info.waterRate}% -含糖量：${info.sugarRate}%</div>
                                        <div class="reason">
                                            修改时间：<f:formatDate value="${info.createTime}" pattern="yyyy-MM-dd"/>
                                        </div>
                                    </div>

                                    <div class="r">
                                        <span class="operation">
                                            <a class="delete_new"  href="http://www.sangepg.com/admin/fruit/info/toAdd?id=${info.id}" target="_blank"><font size="4">修改</font></a>
                                        </span>
                                    </div>
                                </li>
                            </c:forEach>

                            <div style="clear: both;"></div>
                        </ul>
                        <div class="pages">
                            <div class="pagination">
                                <c:if test="${pageIndex>=2}">
                                    <a onclick="toPage(${pageIndex-1})" class="current prev">
                                        <i></i>
                                        &lt;上一页
                                    </a>
                                </c:if>
                                <c:choose>
                                    <c:when test="${pageCount<=4}">
                                        <c:forEach begin="1" end="${pageCount}" step="1" var="index">
                                            <c:if test="${pageIndex==index}">
                                                <a href="#" class="current">${index}</a>
                                            </c:if>
                                            <c:if test="${pageIndex!=index}">
                                                <a onclick="toPage(${index})">${index} </a>
                                            </c:if>
                                        </c:forEach>
                                    </c:when>
                                    <c:when test="${pageIndex<=4}">
                                        <c:forEach var="index" begin="1" end="4" step="1">
                                            <c:if test="${pageIndex == index}">
                                                <a href="#" class="current">${index}</a>
                                            </c:if>
                                            <c:if test="${pageIndex != index}">
                                                <a onclick="toPage(${index})">${index} </a>
                                            </c:if>
                                        </c:forEach>
                                        <c:if test="${pageCount>4}">
                                            <span class="pagination-break">...</span>
                                        </c:if>
                                        <a onclick="toPage(${pageCount})">${pageCount} </a>
                                    </c:when>
                                    <c:when test="${(pageIndex+1) < pageCount}">
                                        <a onclick="toPage(1)">1</a>
                                        <span class="pagination-break">...</span>
                                        <c:forEach var="index" begin="${pageIndex-1}" end="${pageIndex+1}" step="1">
                                            <c:if test="${pageIndex == index}">
                                                <a href="#" class="current">${index} </a>
                                            </c:if>
                                            <c:if test="${pageIndex != index}">
                                                <a onclick="toPage(${index})">${index} </a>
                                            </c:if>
                                        </c:forEach>
                                        <c:if test="${(pageIndex+2) < pageCount}">
                                            <span class="pagination-break">...</span>
                                        </c:if>
                                        <c:if test="${(pageIndex+1) < pageCount}">
                                            <a onclick="toPage(${pageCount})">${pageCount} </a>
                                        </c:if>
                                    </c:when>
                                    <c:otherwise>
                                        <a onclick="toPage(1)">1</a>
                                        <span class="pagination-break">...</span>
                                        <c:forEach var="index" begin="${pageCount-3}" end="${pageCount}" step="1">
                                            <c:if test="${pageIndex == index}">
                                                <a href="#" class="current">${index} </a>
                                            </c:if>
                                            <c:if test="${pageIndex  != index}">
                                                <a onclick="toPage(${index})">${index} </a>
                                            </c:if>
                                        </c:forEach>
                                    </c:otherwise>
                                </c:choose>
                                <c:if test="${pageIndex < pageCount}">
                                    <a onclick="toPage(${pageIndex+1})" class="next" href="#">
                                        下一页 &gt;
                                        <i></i>
                                    </a>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </c:if>
            </div>
            <div style="clear: both;"></div>
        </div>
    </div>
    <!-- /Container -->
    <div style="clear:both;"></div>
    <!--#include virtual="/html/footer/my_footer.html"-->
</div>
<!-- /Container -->
<!--右侧固定图标-->
<!-- 置顶图标 -->
<ul id="jump">
    <li><a id="top" href="#top"></a></li>
</ul>
<!-- jQuery -->
<script src="http://s.sangepg.com/js/jquery.cookie.js"></script>
<script src="http://s.sangepg.com/js/commons.js"></script>
<script src="http://s.sangepg.com/js/personal.js" type="text/javascript"></script>
<script>
    $(function ($) {
        setCurrentTab("my_information");
        $(".top_nav_con_l").height($(".top_nav_con_r").height() + 27);
        $('.personal_r_tab ul.personal_collection li').click(function () {
            //获得当前被点击的元素索引值
            var Index = $(this).index();
            //给菜单添加选择样式
            $(this).addClass('active').siblings().removeClass('active');
            //显示对应的div
            $('.personal_r_tab').children('div').eq(Index).show().siblings('div').hide();
        });
    });
    function toPage(pageIndex) {
        window.location.href = "/admin/fruit/list?farmerId=${query.farmerId}&pageIndex=" + pageIndex;
    }
    function toEdit(id) {
        window.location.href = "/admin/qr/toApplyQr?id=" + id;
    }
</script>

</body>

</html>