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

    <div class="content bg pdt40" style="min-height: 600px;">
        <form id="qr_form" method="POST" action="/admin/qr/apply">
            <div class="pro_tit">
                <div class="name">申请二维码</div>
            </div>
            <div class="row_w pd30">
                <section style="margin-left: 15px; margin-right: 15px; padding-top: 15px; padding-bottom: 0px;">
                    <label>
                        <span style="float: left"><i>*</i>申请类型：</span>
                    </label>
                    <div class="com_select">
                        <select id="fruit_type" name="type" class="select2" onchange="changeType()"  style="width:150px">
                            <option value="">请选择</option>
                            <c:forEach items="${types}" var="t">
                                <c:if test="${reserved.type == t.code}">
                                <option selected ="selected" value="${t.code}">${t.name}</option>
                            </c:if>
                                <c:if test="${reserved.type != t.code}">
                                    <option  value="${t.code}">${t.name}</option>
                                </c:if>

                            </c:forEach>
                        </select>
                    </div>

                    <label>
                        <span><i>*</i>品牌：</span>
                    </label>
                    <div class="com_select">
                        <select id="brand_id" name="brandId" style="width:150px" onchange="changeBrand()">
                        </select>
                    </div>
                    <label>
                        <span><i>*</i>品种：</span>
                    </label>
                    <div class="com_select">
                        <select id="variety_id" name="varietyId" style="width:150px">
                        </select>
                    </div>
                </section>
            </div>
            <div class="row_w pd30 ">
                <section style="margin-left: 15px; margin-right: 15px; padding-top: 15px; padding-bottom: 0px;">
                    <label>
                        <span><i>*</i>申请多少张：</span>
                        <input type="text" name="applyCount" class="bgef" value="${reserved.applyCount}"/><span class="color00">&nbsp;张</span>
                    </label>
                </section>
            </div>
            <div class="row_w pd30 ">
                <section style="margin-left: 15px; margin-right: 15px; padding-top: 15px; padding-bottom: 0px;">
                    <label>
                        <span><i>*</i>申请描述：</span>
                        <textarea name ="applyDesc" class="bgef">${reserved.applyDesc}</textarea>

                    </label>
                </section>
            </div>
            <div class="row_w pd30">
                <input type="hidden" id="apply_status" name="status" value="0">
                <input type="hidden" id="apply_id" name="id" value="${reserved.id}">
            </div>
            <section style="margin-left: 15px; margin-right: 15px; padding-top: 15px; padding-bottom: 0px;">
                <div class="pro_bottom_btn">
                    <a href="javascript:;" class="save" onclick="submitQr(0)">保存</a>
                    <a href="javascript:;" class="up" onclick="submitQr(1)">提交审核</a>
                </div>
            </section>
        </form>


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
    $(function(){
        loadBrand('${reserved.type}',${reserved.brandId});
        loadVariety(${reserved.brandId},${reserved.varietyId});
        $('select').select2({placeholder: '请选择', allowClear: true});
        $('#fruit_type').select2({placeholder: '请选择', allowClear: true, minimumResultsForSearch: Infinity});
    });
    function submitQr(status){
        $("#apply_status").val(status);
        if(status ==0){
            $("#qr_form").ajaxSubmit({
                type: "POST",
                url:"/admin/qr/ajaxApply",
                dataType: "json",
                data:{
                },
                success: function(data){
                    if(data.tip =='success'){
                        jQuery("#apply_id").val(data.id);
                        o_confirm('bg_fixed_bg_noBtn','win_box_noBtn','win_close_noBtn',"保存成功");

                    }else if(data.tip == 'login'){
                        window.location.href="http://www.sangepg.com/jsp/user/user_login.html";
                    }else{
                        o_confirm('bg_fixed_bg_noBtn','win_box_noBtn','win_close_noBtn',"系统繁忙");
                    }

                }
            });
        }else{
            $("#qr_form").submit();
        }

    }
    function changeType() {
        $('#brand_id').select2({placeholder: '请选择', allowClear: true});
        $('#brand_id').select2('val', '');
        var fruitType = $("select[name='type']").val();//学期编号值

        $('#brand_id').html('<option value="">请选择</option>');
        loadBrand(fruitType,0);
    }
    function loadBrand(fruitType,brandId) {
        //根据teacherCode,semesterCode(学期编号)  查询 课程计划表--->科目(课程计划ID)
        var url = "/admin/commons/brand";
        $.ajax({
            type: "get",
            url: url,
            data: {"type": fruitType},
            dataType: "json",
            async: false,
            success: function (msg) {
                $.each(msg, function (i, val) {
                    if(val.id == brandId){
                        $("#brand_id").append("<option   selected='selected' value=" + val.id + ">" + val.name + "</option>");
                    }else{
                        $("#brand_id").append("<option value=" + val.id + ">" + val.name + "</option>");
                    }

                });
            }
        });
    }
    function changeBrand() {
        $('#variety_id').select2({placeholder: '请选择', allowClear: true});
        $('#variety_id').select2('val', '');
        var brandId = $("select[name='brandId']").val();//学期编号值
        $('#variety_id').html('<option value="">请选择</option>');
        loadVariety(brandId);
    }
    function loadVariety(brandId,varietyId) {
        //根据teacherCode,semesterCode(学期编号)  查询 课程计划表--->科目(课程计划ID)
        var url = "/admin/commons/variety";
        $.ajax({
            type: "get",
            url: url,
            data: {"brandId": brandId},
            dataType: "json",
            async: false,
            success: function (msg) {
                $.each(msg, function (i, val) {
                    if(val.id == varietyId){
                        $("#variety_id").append("<option selected='selected' value=" + val.id + ">" + val.name + "</option>");
                    }else{
                        $("#variety_id").append("<option value=" + val.id + ">" + val.name + "</option>");
                    }

                });
            }
        });
    }
</script>
</body>

</html>