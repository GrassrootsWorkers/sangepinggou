<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="shortcut icon" type="image/x-icon" href="http://p.sangepg.com/images/favicon.ico">
    <!-- Title -->
    <title>三个苹果</title>
    <meta name="Keywords" content="亿万中产阶级托付的平台">
    <meta name="Description" content="农村城市的纽带，吃出放心，吃出健康">
    <!-- Favicon -->
    <!-- Stylesheets -->
    <!-- jQuery -->
    <link href="http://admin.sangepg.com/css/commons.css" rel="stylesheet" type="text/css">
    <link href="http://admin.sangepg.com/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="http://admin.sangepg.com/css/personal.css" rel="stylesheet" type="text/css">
    <link href="http://admin.sangepg.com/css/project.css" rel="stylesheet" type="text/css">
    <script src="http://admin.sangepg.com/js/jquery/jquery-3.0.0.js"></script>
</head>

<body onload="window.print()">
<c:forEach items="${qrList}" var="qr" varStatus="index">
    <div id="qr_${index.index}">
        <table width="100px" border="0" cellpadding="0" cellspacing="0" style="page-break-after:always">
            <tr>
                <td>
                    <img  src="${qr}"/>
                </td>
            </tr>
        </table>
    </div>

</c:forEach>
</body>
</html> 