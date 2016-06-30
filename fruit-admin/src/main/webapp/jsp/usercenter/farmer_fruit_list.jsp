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
            <div class="top_nav_con_l top_nav_con_l_tab">
                <ul>
                    <li class="active"><a href="个人中心_我的收藏列表.html">我的水果</a></li>
                    <li><a href="个人中心_我的发布项目列表.html">我的资料</a></li>
                    <li><a href="个人中心_我的洽谈.html">我的二维码</a></li>
                </ul>
            </div>
            <div class="top_nav_con_r personal_r_tab">
                <div class="personal_pro">
                    <div class="bottom_tips">
                        <div class="r">
                            <div class="r_total">
                                总计：<i>${count}</i>
                            </div>
                        </div>
                    </div>
                    <ul class="r_con_ul">
                        <c:if test="${fruits !=null}">
                            <c:forEach items="${fruits}" var="f">
                                <li>
                                    <div class="l">
                                        <c:if test="${f.image ==null}">
                                            <img src="http://s.sangepg.com/images/no_picture.png"/>
                                        </c:if>

                                        <c:if test="${f.image !=null}">
                                            <img src="${f.image}"/>
                                        </c:if>
                                    </div>
                                    <div class="c">
                                        <div class="list_tag">
                                            <span>是否卖出：</span>
                                            <span>${f.ifSale}</span>
                                        </div>
                                        <div class="list_tag">
                                            <span>重量：</span>
                                            <span>${f.weight}kg</span>
                                        </div>
                                        <div class="list_tag">
                                            <span>采摘日期：</span>
                                            <span><f:formatDate value="${f.harvestTime}" pattern="yyyy-MM-dd"/></span>
                                            <span>成熟度：</span>
                                            <span>${f.maturingStatus}成</span>
                                        </div>

                                        <div class="list_tag">
                                            <span>市场价格：</span>
                                            <span>${f.marketPrice}元</span>
                                        </div>
                                      <%--  <div class="list_bt_tag">
                                            <span class="pro_time"><f:formatDate value="${f.addTime}" pattern="yyyy-MM-dd"/></span>
                                            <span class="pro_eye">29292</span>
                                            <span class="pro_share">分享</span>
                                        </div>--%>
                                    </div>
                                    <div class="r">
                                       <img src="http://s.sangepg.com/images/app_img.png">
                                    </div>
                                </li>
                            </c:forEach>
                        </c:if>
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
            </div>
            <div style="clear: both;"></div>
        </div>
    </div>
    <!-- /Container -->
    <div style="clear:both;"></div>
    <!--#include virtual="/html/footer/register_footer.html"-->
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
    $(function($){
        $(".top_nav_con_l").height($(".top_nav_con_r").height()+27);
        $('.personal_r_tab ul.personal_collection li').click(function(){
            //获得当前被点击的元素索引值
            var Index = $(this).index();
            //给菜单添加选择样式
            $(this).addClass('active').siblings().removeClass('active');
            //显示对应的div
            $('.personal_r_tab').children('div').eq(Index).show().siblings('div').hide();
        });
    });
    function toPage(pageIndex) {
        window.location.href = "/admin/fruit/list?farmerId=${query.farmerId}&pageIndex="+pageIndex;
    }
</script>

</body>

</html>