<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <title>添加评论</title>
</head>
<body>
<input type="hidden" name="fruitCode" value="${fruitCode}">
<input type="hidden" name="farmerId" value="${farmerId}">
<c:forEach items="${reviews}" var="review">
    <input type="text" value="${review.content}}">
</c:forEach>

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
