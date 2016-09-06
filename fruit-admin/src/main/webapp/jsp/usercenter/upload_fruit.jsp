<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <link rel="stylesheet" href="/plugins/select2/select2.css">
    <script src="http://admin.sangepg.com/js/jquery/jquery-3.0.0.js"></script>

    <link href="http://admin.sangepg.com/css/personal.css" rel="stylesheet" type="text/css">
    <link href="http://admin.sangepg.com/css/project.css" rel="stylesheet" type="text/css">
    <link href="/plugins/bootstrap/css/bootstrap-datetimepicker.css" rel="stylesheet" type="text/css">
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

    <div class="content bg pdt40" style="min-height: 750px;">
        <form id="qr_form" method="POST" enctype="multipart/form-data" action="/admin/qr/loadFruitInfo">
            <div class="pro_tit">
                <div class="name">添加我的水果</div>
            </div>
            <div class="row_w pd30">
                <section style="margin-left: 15px; margin-right: 15px; padding-top: 1px; padding-bottom: 0px;">
                    <label>
                        <span style="float: left"><i>*</i>水果类型：</span>
                    </label>
                    <div class="com_select">
                        <select id="fruit_type" name="type" class="select2" onchange="changeType()" style="width:150px">
                            <option value="">请选择</option>
                            <c:forEach items="${types}" var="t">
                                <c:if test="${reserved.type == t.code}">
                                    <option selected="selected" value="${t.code}">${t.name}</option>
                                </c:if>
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
                </section>
            </div>
            <div class="row_w">
                <section style="margin-left: 15px; margin-right: 15px; padding-top: 1px; padding-bottom: 0px;">
                    <label>
                        <span><i>*</i>市场价格(元/kg)：</span>
                        <input  id="mark_price" name="marketPrice"  value="${reserved.marketPrice}" placeholder="填写实例:9.99"/>
                    </label>

                    <label>
                        <span><i>*</i>采摘时间：</span>
                        <input id="harvest_time" name="harvestTime"   value="<fmt:formatDate value="${reserved.harvestTime}" pattern="YYYY-MM-dd HH:mm:ss"/> " placeholder="水果采摘的日期"/>
                    </label>
                </section>
            </div>
           <%-- <div class="row_w">
                <section style="margin-left: 15px; margin-right: 15px; padding-top: 1px; padding-bottom: 0px;">
                    <label>
                        <span><i>*</i>申请的二维码链接：</span>
                        <input class="w442" id="test_url" name="testUrl" value="${reserved.testUrl}"
                               placeholder="http://www.sangepg.com/${reserved.type}/${reserved.token}10000010001.html"/>
                    </label>
                </section>
            </div>--%>

            <div class="row_w pd30 ">
                <section style="margin-left: 15px; margin-right: 15px; padding-top: 1px; padding-bottom: 0px;">
                    <label>
                        <span><i>*</i>上传重量(Excel文件)：</span>
                        <a href="javascript:;" class="file">
                            <input type="file" id="imgFile" name="excelFile" style="height:100px;width:100px;"/>
                        </a>
                    </label>
                    <label>

                        <c:if test="${reserved.filePath != null}">
                            <span><i id="file_tip">已经上传</i></span>
                        </c:if>
                        <c:if test="${reserved.filePath == null}">
                            <span><i id="file_tip"></i></span>
                        </c:if>
                    </label>
                </section>
            </div>
            <div class="row_w pd30 "></div>
            <div class="row_w pd30 ">
                <section style="margin-left: 15px; margin-right: 15px; padding-top: 1px; padding-bottom: 0px;">
                    <label>
                        <span><i>*</i>上传图片(压缩包)：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                        <a href="javascript:;" class="file">
                            <input type="file" id="picture" name="picture" style="height:100px;width:100px;"/>
                        </a>


                    </label>
                    <label>
                        <c:if test="${reserved.picturePath != null}">
                            <span><i id="pic_tip">已经上传</i></span>
                        </c:if>
                        <c:if test="${reserved.picturePath == null}">
                            <span><i id="pic_tip"></i></span>
                        </c:if>

                    </label>
                </section>
            </div>
            <div class="row_w pd30">
                <input type="hidden" id="file_path" name="filePath" value="${reserved.filePath}">
                <input type="hidden" id="picture_path" name="picturePath" value="${reserved.picturePath}">
                <input type="hidden" name="id" value="${reserved.id}">
            </div>
            <section style="margin-left: 15px; margin-right: 15px; padding-top: 1px; padding-bottom: 0px;">
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
<script src="/plugins/bootstrap/js/bootstrap-datetimepicker.js"></script>
<script src="/plugins/bootstrap/js/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="http://admin.sangepg.com/js/commons.js"></script>
<script src="http://admin.sangepg.com/js/personal.js" type="text/javascript"></script>
<script type="text/javascript" src="/plugins/select2/select2.full.min.js"></script>
<script src="http://admin.sangepg.com/js/jquery/jquery.form.js"></script>
<script type="text/javascript">
    $(function () {
        $('#harvest_time').datetimepicker({
            language:'zh-CN',
            format:'yyyy-mm-dd hh:ii:ss',
            weekStart: 1,
            todayBtn:  1,
            autoclose: 1,
            todayHighlight: 1,
            startView: 2,
            forceParse: 0,
            showMeridian: 1
        });
        loadBrand('${reserved.type}', ${reserved.brandId});
        loadVariety(${reserved.brandId}, ${reserved.varietyId});
        $('select').select2({placeholder: '请选择', allowClear: true});
        $('#fruit_type').select2({placeholder: '请选择', allowClear: true, minimumResultsForSearch: Infinity});
    });
    $("#imgFile").change(function () {
        //异步表单提交 先提交图片
        jQuery("#qr_form").ajaxSubmit({
            type: "POST",
            url: "/admin/qr/uploadFile",
            dataType: "json",
            data: {
                fileType: "file"
            },
            success: function (data) {
                if (data.success) {
                    $("#file_path").val(data.tip);
                    $("#file_tip").html("上传成功！");
                } else {
                    if (data.tip == 'login') {
                        window.location.href = "http://www.sangepg.com/jsp/user/user_login.html";
                    } else {
                        alert(data.tip);
                    }
                }
            }
        });

    });
    $("#picture").change(function () {
        jQuery("#imgFile").val("");
        jQuery("#qr_form").ajaxSubmit({
            type: "POST",
            url: "/admin/qr/uploadPicture",
            dataType: "json",
            data: {
                fileType: "images"
            },
            success: function (data) {
                if (data.success) {
                    $("#picture_path").val(data.tip);
                    $("#pic_tip").html("上传成功");
                } else {
                    if (data.tip == 'login') {
                        window.location.href = "http://www.sangepg.com/jsp/user/user_login.html";
                    } else {
                        alert(data.tip);
                    }
                }
            }
        });
    });
    function validateDate() {
        var fruitType = $("select[name='type']").val();
        if (fruitType == "") {
            alert("请先选择水果类型");
            return false;
        }
        var brandId = $("select[name='brandId']").val();
        if (brandId == "") {
            alert("请选择水果品牌");
            return false;
        }
        var varietyId = $("select[name='varietyId']").val();
        if (varietyId == "") {
            alert("请选择水果种类");
            return false;
        }
        var harvest_time = $("#harvest_time").val();
        if(harvest_time == ""){
            alert("请填写采摘时间");
            return false;
        }
        if(new Date(harvest_time) > new Date()){
            alert("采摘时间不能大于今天");
            return false;
        }
        var price = $("#mark_price").val();
        if(!validateNDouble(price)){
            alert("市场价输入错误，要填写到几分");
            return false;
        }
        if ($("#test_url").val() == "") {
            alert("请填写您申请的二维码链接");
            return false;
        }
        if ($("#file_path").val() == "") {
            alert("上传重量(Excel文件)");
            return false;
        }
        if ($("#picture_path").val() == "") {
            alert("上传图片(压缩包)");
            return false;
        }
        return true;
    }
    function submitQr(index) {
        if (!validateDate()) {
            return;
        }
        if (index == 1) {
            $("#qr_form").submit()
        } else {
            jQuery("#qr_form").ajaxSubmit({
                type: "POST",
                url: "/admin/qr/ajaxAddFruitInfo",
                dataType: "json",
                data: {
                    fileType: "images"
                },
                success: function (data) {
                    if (data.success) {
                        alert("保存成功");
                    } else {
                        if (data.tip == 'login') {
                            window.location.href = "http://www.sangepg.com/jsp/user/user_login.html";
                        } else {
                            alert(data.tip);
                        }
                    }
                }
            });
        }
    }
    function changeType() {
        $('#brand_id').select2({placeholder: '请选择', allowClear: true});
        $('#brand_id').select2('val', '');
        var fruitType = $("select[name='type']").val();//学期编号值

        $('#brand_id').html('<option value="">请选择</option>');
        loadBrand(fruitType, 0);
    }
    function loadBrand(fruitType, brandId) {
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
                    if (val.id == brandId) {
                        $("#brand_id").append("<option   selected='selected' value=" + val.id + ">" + val.name + "</option>");
                    } else {
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
    function loadVariety(brandId, varietyId) {
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
                    if (val.id == varietyId) {
                        $("#variety_id").append("<option selected='selected' value=" + val.id + ">" + val.name + "</option>");
                    } else {
                        $("#variety_id").append("<option value=" + val.id + ">" + val.name + "</option>");
                    }

                });
            }
        });
    }
</script>
</body>

</html>