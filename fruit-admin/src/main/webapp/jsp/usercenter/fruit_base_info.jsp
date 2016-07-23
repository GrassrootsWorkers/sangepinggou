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
    <!-- jQuery -->
    <link href="http://s.sangepg.com/css/commons.css" rel="stylesheet" type="text/css">
    <link href="http://s.sangepg.com/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="/plugins/select2/select2.css">
    <script src="http://s.sangepg.com/js/jquery/jquery-3.0.0.js"></script>

    <link href="http://s.sangepg.com/css/personal.css" rel="stylesheet" type="text/css">
    <link href="http://s.sangepg.com/css/project.css" rel="stylesheet" type="text/css">
    <!--ckeditor-->
    <script type="text/javascript" src="/plugins/ckeditor/ckeditor.js"></script>
    <style>
        .span_class {
            float: left;
            line-height: 36px;
            color: #8d8d8d;
            font-weight: normal;
        }
    </style>
    <script>
        $(function () {
            $('select').select2({placeholder: '请选择', allowClear: true});
        });
    </script>
</head>

<body class="bgf6">
<!-- Container -->
<div class="main">
    <!--#include virtual="/html/header/header.html"-->
    <!--#include virtual="/html/user/user_info.html"-->
    <!--- 发布项目开始--->

    <div class="content bg pdt40" style="min-height: 1200px;">
        <form id="info_form" method="POST" action="/admin/fruit/info/add" accept-charset="utf-8">
            <div class="pro_tit">
                <div class="name">我的资料</div>
            </div>
            <div class="row_w"></div>
            <div class="row_w" style="margin-top: 20px">
                <label>
                    <span style="float: left"><i>*</i>水果类型：</span>
                </label>
                <div class="com_select">
                    <select id="fruit_type" name="type" class="select2" onchange="changeType()" style="width:150px">
                        <option value="">请选择</option>
                        <c:forEach items="${types}" var="t">
                            <option value="${t.code}">${t.name}</option>
                        </c:forEach>
                    </select>
                </div>

                <label>
                    <span><i>*</i>水果品牌：</span>
                </label>
                <div class="com_select">
                    <select id="brand_id" name="brandId" style="width:150px" onchange="changeBrand()">
                    </select>
                </div>
                <label>
                    <span><i>*</i>水果种类：</span>
                </label>
                <div class="com_select">
                    <select id="variety_id" name="varietyId" style="width:150px">
                    </select>
                </div>

            </div>
            <div class="row_w ">
                <label>
                    <span><i>*</i>生长期：&nbsp;&nbsp;&nbsp;</span>
                    <input type="text" id="growth_period" name="growthPeriod" class="bgef" placeholder="100天"/>
                </label>
                <label>
                    <span><i>*</i>含水量：</span>
                    <input type="text" id="water_rate" name="waterRate" class="bgef" placeholder="100%"/>
                </label>
                <label>
                    <span><i>*</i>含糖量：</span>
                    <input type="text" id="sugar_rate" name="sugarRate" class="bgef" placeholder="100%"/>
                </label>
            </div>
            <div class="row_w">
                <input type="hidden" id="farmer_id" name="farmerId" value="${farmer.id}">
                <input type="hidden" id="info_id" name="id">
                <input type="hidden" id="h_farmer_desc" name="farmerDesc">
                <input type="hidden" id="h_place_desc" name="productionPlaceDesc">
            </div>
        </form>
        <div class="row_w ">
            <label>
                <span style="margin-top: 5px;"><i>*</i>产地描述：</span>
            </label>
            <div class="span_class" style="margin-top: 5px;">
                <textarea rows="2" cols="1" id="place_desc" name="t_productionPlaceDesc"></textarea>
                <script type="text/javascript">
                    var placeDesc = CKEDITOR.replace("t_productionPlaceDesc", {
                        width: 800,
                        height: 200,
                        toolbarCanCollapse: true,
                        entities: false,
                        fillEmptyBlocks: false
                    });
                </script>
            </div>
        </div>
        <div class="row_w ">
            <label>
                <span style="margin-top: 30px;"><i>*</i>自我描述：</span>
            </label>
            <div class="span_class" style="margin-top: 35px;">
                <textarea rows="2" cols="1" id="farmer_desc" name="t_farmerDesc"></textarea>
                <script type="text/javascript">
                    var farmerDesc = CKEDITOR.replace("t_farmerDesc", {
                        width: 800,
                        height: 200,
                        toolbarCanCollapse: true,
                        entities: false,
                        fillEmptyBlocks: false
                    });
                </script>
            </div>

        </div>
        <div class="row_w ">
            <div class="pro_bottom_btn">
                <a href="javascript:;" class="save" onclick="submitInfo(0)">保存</a>
                <a href="javascript:;" class="up" onclick="submitInfo(1)">保存并发布</a>
            </div>
        </div>
    </div>

    <!--- 发布项目结束--->
    <div style="clear:both;"></div>
    <!--#include virtual="/html/footer/my_footer.html"-->
</div>
<!-- /Container -->
<!-- 置顶图标 -->
<ul id="jump">
    <li><a id="top" href="#top"></a></li>
</ul>
<script src="http://s.sangepg.com/js/commons.js"></script>
<script src="http://s.sangepg.com/js/personal.js" type="text/javascript"></script>
<script type="text/javascript" src="/plugins/select2/select2.full.min.js"></script>
<script src="http://s.sangepg.com/js/jquery/jquery.form.js"></script>
<script type="text/javascript">

    function submitInfo(status) {
        var nameValue = $("select[name='type']").val();
        if (nameValue == "-1") {
            alert("请选择要添加那类水果");
            return;
        }
        nameValue = $("select[name='brandId']").val();
        if (nameValue == "-1") {
            alert("请选择要添加水果的品牌");
            return;
        }
        nameValue = $("select[name='varietyId']").val();
        if (nameValue == "-1") {
            alert("请选择要添加水果的品种");
            return;
        }
        var waterRate = $("#water_rate").val().replace("%", "");
        $("#water_rate").val(waterRate);
        if (waterRate >= 100) {
            alert("含水量填写错误，请填写小于100的数");
            return;
        }
        var sugarRate = $("#sugar_rate").val().replace("%", "");
        $("#sugar_rate").val(sugarRate);
        if (sugarRate >= 100) {
            alert("含糖量填写错误，请填写小于100的数");
            return;
        }
        if (!validateIfNumber($("#water_rate").val())) {
            alert("含水量输入不对，请输入数字");
            return;
        }
        if (!validateIfNumber($("#sugar_rate").val())) {
            alert("含糖量输入不对，请输入数字");
            return;
        }
        if (!validateIfNumber($("#growth_period").val())) {
            alert("生长期输入不对，请输入数字");
            return;
        }
        var placeDescContent = placeDesc.document.getBody().getHtml();
        if ("<br>" == placeDescContent) {
            alert("请填写产地描述！");
            return;
        } else {
            $("#h_place_desc").val(encodeURI(placeDescContent));
        }
        var farmerDescContent = farmerDesc.document.getBody().getHtml();
        if ("<br>" == farmerDescContent) {
            alert("请填写自我描述");
            return;
        } else {
            $("#h_farmer_desc").val(encodeURI(farmerDescContent));
        }
        if (status == 0) {
            $("#info_form").ajaxSubmit({
                type: "POST",
                url: "/admin/fruit/info/ajaxAdd",
                dataType: "json",
                data: {},
                success: function (data) {
                    if (data.tip == 'success') {
                        jQuery("#info_id").val(data.id);
                        alert("保存成功");
                    } else if (data.tip == 'login') {
                        window.location.href = "http://www.sangepg.com/jsp/user/user_login.html";
                    } else {
                        alert("系统繁忙");
                    }
                }
            });
        } else {
            $("#info_form").submit();
        }

    }
    function changeType() {
        $('#brand_id').select2({placeholder: '请选择', allowClear: true});
        $('#brand_id').select2('val', '');
        var fruitType = $("select[name='type']").val();//学期编号值

        $('#brand_id').html('<option value="">请选择</option>');
        loadBrand(fruitType);
    }
    function loadBrand(fruitType) {
        var url = "/admin/commons/brand";
        $.ajax({
            type: "get",
            url: url,
            data: {"type": fruitType},
            dataType: "json",
            async: false,
            success: function (msg) {
                $.each(msg, function (i, val) {
                    $("#brand_id").append("<option value=" + val.id + ">" + val.name + "</option>");
                });
            }
        });
    }
    function changeBrand() {
        $('#variety_id').select2({placeholder: '请选择', allowClear: true});
        $('#variety_id').select2('val', '');
        var brandId = $("select[name='brandId']").val();
        $('#variety_id').html('<option value="">请选择</option>');
        loadVariety(brandId);
    }
    function loadVariety(brandId) {
        var url = "/admin/commons/variety";
        $.ajax({
            type: "get",
            url: url,
            data: {"brandId": brandId},
            dataType: "json",
            async: false,
            success: function (msg) {
                $.each(msg, function (i, val) {
                    $("#variety_id").append("<option value=" + val.id + ">" + val.name + "</option>");
                });
            }
        });
    }
</script>
</body>

</html>