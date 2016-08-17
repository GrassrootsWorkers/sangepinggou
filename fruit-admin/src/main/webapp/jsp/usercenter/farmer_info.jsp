<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
    <!-- Stylesheets -->
    <link href="/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="http://admin.sangepg.com/css/personal.css?t=1" rel="stylesheet" type="text/css">
    <link href="http://admin.sangepg.com/css/commons.css" rel="stylesheet" type="text/css">
    <!-- select2 -->
    <link rel="stylesheet" href="/plugins/select2/select2.css">
    <script src="http://admin.sangepg.com/js/jquery/jquery-3.0.0.js"></script>
</head>
<body class="bgf6">
<!-- Container -->
<div class="loginmain">
    <!--#include virtual="/html/header/header.html"-->
    <!--#include virtual="/html/user/user_info.html"-->
    <div class="content bg pb120">
        <form method="post" id="save_user_info" action="/admin/user/update">
            <div class="personal_top_nav">
                <ul>
                    <li class="h40">
                        <a href="http://www.sangepg.com/admin/fruit/list?pageIndex=1&farmerId=${farmer.id}">
                            &lt;返回个人中心</a>
                        <a href="http://www.sangepg.co/help/help.html" class="r_help"><i></i>帮助中心</a>
                    </li>
                    <li class="h70 w540">
                        <h1>设置头像</h1>
                        <a href="javascript:;" class="div_file">编辑
                            <input type="file" id="imgFile" name="imgFile" class="div_file" onchange="imageChange()">
                        </a>
                    </li>

                    <li class="h70 w540">
                        <h1>密码修改</h1>
                        <a class="btn_r" href="javascript:;" onclick="showChangePasswordDiv()">修改</a>
                    </li>
                    <li class="w540">
                        <h1>详细信息</h1>

                        <label>
                            <span class="personal_span">手&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;机：</span>
                            <p style="margin:0px 0">${farmer.mobile}</p>
                            <input type="hidden" name="mobile" value="${farmer.mobile}"/>
                          <%--  <a href="javascript:;" onclick="showChangMobilDiv()">修改</a>--%>
                        </label>
                        <label>
                            <span class="personal_span">真实姓名：</span>
                            <input class="w442" name="name" id="real_name_div" value="${farmer.name}"/>
                            <input type="hidden" name="id" value="${farmer.id}"/>
                        </label>
                        <label>
                            <span class="personal_span">身份证号：</span>
                            <input class="w442" id="id_card" name="idCard" onblur="isCardNo()" placeholder="18位身份证号"
                                   value="${farmer.idCard}"/>
                        </label>
                        <label>
                            <span class="personal_span">种植经验：</span>
                            <input class="w442" id="experience_age" name="experienceAge"
                                   value="${farmer.experienceAge}年" placeholder="多少年的种植经验"/>
                        </label>
                        <label>
                            <span class="personal_span">家庭住址：</span>
                            <input class="w442" id="home_address" name="homeTown" value="${farmer.homeTown}"
                                   placeholder="您目前所在的村的名字"/>
                        </label>
                    </li>
                </ul>
                <input type="hidden" name="userImage" id="userImg"/>
                <button type="submit" onclick=" return submitForm()">确&nbsp;&nbsp;定</button>
            </div>
            <div>

            </div>
        </form>
        <div style="clear: both;"></div>
    </div>
    <!-- /Container -->
    <!--修改密码-->
    <div class="bg_fixed_password" style="display: none;">
        <div class="edit_pass_box">
            <i class="close_box" onclick="cancelChangePassword()"></i>
            <h2>密码修改</h2>
            <input type="password" id="old_password" placeholder="请输入原始密码"/>
            <input type="password" id="new_password1" placeholder="设定密码"/>
            <input type="password" id="new_password2" placeholder="确认密码"/>

            <div class="bottom_btn">
                <h2 id="password_error"></h2>
                <a href="javascript:;" onclick="cancelChangePassword()" class="cancel">取消</a>
                <a href="javascript:;" onclick="changePassword()" class="save">保存</a>
            </div>
        </div>
    </div>

    <!--修改手机-->
    <div class="bg_fixed_phone" style="display: none">
        <div class="edit_phone_box">
            <i ID="update_mobile" class="close_box"></i>
           <span class="step1" style="display: none">
               <p>输入图片验证码</p>
               <label>
                   <input id="picture_code"/><img id="code_img"
                                                  onclick="this.src='/servlet/validateCodeServlet?t='+(new Date()).getTime()"/><a
                       onclick="refreshIcon()" class="refresh_icon"></a>
               </label>
               <span id="error_tip_1"></span>
               <a class="btn" onclick="nextStep(1)">确定提交</a>
               <div style="clear: both"></div>
           </span>
           <span class="step2" style="display: none">
               <p>为了账号安全,先要验证手机有效性</br>
                   一条包含有验证的短信已发送至手机：</br>
                   <i>${farmer.mobile}</i>
               </p>
               <label>
                   <input id="old_checkCode"/><span id="span_old">60秒后重新发送</span><input style="display: none"
                                                                                        id="btn_old"
                                                                                        onclick="validatePicture()"
                                                                                        type="button" value="短信">
               </label>
               <span id="error_old_checkCode"></span>
               <a class="btn_l" onclick="cancelNext(2)">取消</a><a class="btn_r" onclick="nextStep(2)">下一步</a>
                <div style="clear: both"></div>
           </span>

            <span class="step3" style="display: none">
               <div class="step_con">
                   <span class="step_bar">
                       <p class="completed"></p>
                   </span>

                   <span class="step_name">
                       <p class="l">输入新手机</p>
                       <p class="c">验证新手机</p>
                       <p class="r">完成</p>
                   </span>
               </div>
               <label>
                   <input id="changed_mobile" type="text" placeholder="输入您的手机号"/>
               </label>
                   <span id="error_tip_mobile"></span>
               <a class="btn_l" onclick="cancelNext(3)">取消</a><a class="btn_r" onclick="nextStep(3)">下一步</a>
                 <div style="clear: both"></div>
           </span>


            <span class="step4" style="display: none">
             <div class="step_con">
                   <span class="step_bar">
                       <p class="completed" style="width: 260px;"></p>
                   </span>

                   <span class="step_name">
                       <p class="l">输入新手机</p>
                       <p class="c">验证新手机</p>
                       <p class="r">完成</p>
                   </span>
             </div>
               <label>
                   <p>一条包含有验证码的短信已发送至手机：<i id="new_mobile"></i></p>
                   <input id="new_checkCode"/><span id="span_new">60秒后重新发送</span><input style="display: none"
                                                                                        id="btn_new"
                                                                                        onclick="getValidate('other')"
                                                                                        type="button" value="短信">
               </label>
                   <span id="error_new_checkCode"></span>
                 <span id="error_tip_change_mobile"></span>
               <a class="btn_l" onclick="cancelNext(4)">取消</a><a class="btn_r" onclick="nextStep(4)">下一步</a>
                 <div style="clear: both"></div>
           </span>


           <span class="step5" style="display: none">
                <div class="step_con">
                   <span class="step_bar">
                       <p class="completed" style="width: 530px;"></p>
                   </span>

                   <span class="step_name">
                       <p class="l">输入新手机</p>
                       <p class="c">验证新手机</p>
                       <p class="r">完成</p>
                   </span>
                </div>
              <p class="success">恭喜您,已成功修改手机号!</p>
              <a class="succ_btn" onclick="nextStep(5)">完成</a>
                <div style="clear: both"></div>
           </span>


        </div>
    </div>

    <!--#include virtual="/html/footer/my_footer.html"-->
</div>
<!-- /Container -->
<!-- 置顶图标 -->
<ul id="jump">
    <li><a id="top" href="#top"></a></li>
</ul>
<!-- jQuery -->

<script src="http://admin.sangepg.com/js/commons.js"></script>
<script src="http://admin.sangepg.com/js/jquery/jquery.form.js" type="text/javascript"></script>
<script src="http://admin.sangepg.com/js/ajaxfileupload.js" type="text/javascript"></script>
<script src="/plugins/bootstrap/js/bootstrap.js" type="text/javascript"></script>
<script type="text/javascript" src="/plugins/select2/select2.full.min.js"></script>
<script type="text/javascript" src="/plugins/select2/i18n/zh-CN.js"></script>
<script type="text/javascript">
    var origSelOjbs;
    var stepFlag = 1;
    var countdown = 60;
    var countdown1 = 60;
    $(function () {
        $("#code_img").trigger("click");
        $("#update_mobile").click(function () {
            $(".bg_fixed_phone").fadeOut("slow");
            $(".edit_phone_box").fadeIn("slow");
            $(".step" + stepFlag).hide();

        })
    });
    function submitForm() {
        var realName = $("#real_name_div").val();
        if (realName == "") {
            alert("请填写您的真实姓名");
            return false;
        }
        var card = $("#id_card").val();
        if (card == "") {
            alert("请填写您的身份证号");
            return false;
        }
        var age = $("#experience_age").val();
        $("#experience_age").val(age.replace("年", ""));
        return true;
    }
    function isCardNo() {
        var card = $("#id_card").val();
        // 身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X
        var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
        if (reg.test(card) === false) {
            alert("请再核对下您的身份证号");
            return false;
        }
    }

    /**
     * 选择上传图片，点击选择按钮触发
     */
    function imageChange() {
        if ($("#imgFile").val()) {
            //异步表单提交 先提交图片
            $("#save_user_info").ajaxSubmit({
                type: "POST",
                url: "/admin/user/uploadIcon",
                dataType: "json",
                success: function (data) {
                    if (data.success) {
                        $("#user_icon").attr("src", data.tip);
                        $("#userImg").val(data.tip);
                    } else {
                        if (data.tip == "login") {
                            window.location.href = "http://www.sangepg.com/jsp/user/user_login.html";
                        } else {
                            alert("请重新选择JPG格式的图片");
                        }
                    }

                }
            });
        }
    }

    function setSex(id) {
        $("#userSex").val(id);
    }
    function refreshIcon() {
        $("#code_img").trigger("click");
    }
    function showChangMobilDiv() {
        $(".bg_fixed_phone").show();
        if (stepFlag > 1) {
            $(".step" + stepFlag).show();
        } else {
            $(".step1").show();
        }

    }
    function cancelNext(index) {
        $(".step" + (index + 1)).hide();
        $(".bg_fixed_phone").hide();
    }
    function nextStep(index) {
        if (index == 1) {
            validatePicture();
        } else if (index == 3) {

            getNextStatus();
        } else if (index == 2) {
            var checkCode = $("#old_checkCode").val();
            if (checkCode == "") {
                $("#error_old_checkCode").html("验证码不能为空！");
            } else {
                hideOrShowDiv(2);
            }

        } else if (index == 4) {
            var newCheckCode = $("#new_checkCode").val();
            if (newCheckCode == "") {
                $("#error_new_checkCode").html("验证码不能为空！");
            } else {
                changeMobile();
            }
            //调用服务器进行修改手机号！
        } else {
            hideOrShowDiv(index);
        }


    }
    function hideOrShowDiv(index) {
        $(".step" + index).hide();
        if (index <= 4) {
            $(".step" + (index + 1)).show();
            stepFlag = index + 1;
        } else {
            stepFlag = 1;
            $(".bg_fixed_phone").hide();
        }
    }
    function validatePicture() {
        var validateCode = $("#picture_code").val();
        if (validateCode == '') {
            $("#error_tip_1").html("验证码不能为空！")
            return false;
        }
        jQuery.ajax({
            url: "/user/mobile/" + validateCode,
            type: "GET",
            async: true,
            cache: false,
            success: function (data) {
                if (data.flag != 'success') {
                    $("#error_tip_1").html(data.msg);
                } else {
                    hideOrShowDiv(1);
                    showTimeOld();
                }

            }
        });
    }
    function getNextStatus() {
        var mobile = $("#changed_mobile").val();
        if (!mobileValidate(mobile)) {
            $("#error_tip_mobile").html("手机号格式不正确！");
            return false;
        }
        $('#new_mobile').html(mobile.substr(0, 3) + "****" + mobile.substr(7, 11));
        $("#error_tip_mobile").html("");
        jQuery.ajax({
            url: "/user/sendCode",
            type: "GET",
            cache: false,
            data: {
                mobile: mobile,
                codeType: "other"
            },
            success: function (data) {
                if (data.success) {
                    showTimeNew();
                    hideOrShowDiv(3);
                } else {
                    $("#error_tip_mobile").html(data.msg);
                }
            }
        });
    }
    function getValidate(type) {
        $("#btn_new").hide();
        var mobile = $("#changed_mobile").val();
        if (!mobileValidate(mobile)) {
            $("#error_tip_mobile").html("手机号格式不正确！");
            return false;
        }
        jQuery.ajax({
            url: "/user/sendCode",
            type: "GET",
            cache: false,
            data: {
                mobile: mobile,
                codeType: type
            },
            success: function (data) {
                if (data.success) {
                    if (id == "new") {
                        showTimeNew();
                    } else {
                        showTimeOld();
                    }

                } else {
                    $("#error_tip_mobile").html(data.msg);
                }
            }
        });
    }
    function showTimeOld() {
        if (countdown == 1) {
            $("#span_old").html("");
            $("#btn_old").show();
            countdown = 60;
        } else {
            $("#span_old").html(countdown + "秒后重试！");
            $("#btn_old").hide();
            countdown--;
            setTimeout(function () {
                showTimeOld()
            }, 1000)
        }
    }
    function showTimeNew() {
        if (countdown1 == 1) {
            $("#span_new").html("");
            $("#btn_new").show();
            countdown1 = 60;
        } else {
            $("#span_new").html(countdown1 + "秒后重试！");
            $("#btn_new").hide();
            countdown1--;
            setTimeout(function () {
                showTimeNew()
            }, 1000)
        }
    }
    function mobileValidate(value) {
        var regMobile = /^(13[0-9]||15[012356789]||18[0123456789]||147||145||17[0-9])\d{8}$/;
        if (!regMobile.test(value)) {
            return false;
        }
        return true;
    }
    function changeMobile() {
        var picCheckCode = $("#picture_code").val();
        var newMobile = $("#changed_mobile").val();
        var oldCheckCode = $("#old_checkCode").val();
        ;
        var newCheckCode = $("#new_checkCode").val();
        jQuery.ajax({
            url: "/admin/user/change/mobile",
            type: "POST",
            cache: false,
            data: {
                picCheckCode: picCheckCode,
                oldCheckCode: oldCheckCode,
                newCheckCode: newCheckCode,
                mobile: newMobile
            },
            success: function (data) {
                if ("success" == data.flag) {
                    hideOrShowDiv(4);
                } else {
                    $("#error_tip_change_mobile").html(data.msg);
                }
            }
        });
    }
    function showChangePasswordDiv() {
        $(".bg_fixed_password").show();
        $(".edit_pass_box").show();
    }
    function cancelChangePassword() {
        $(".bg_fixed_password").hide();
        $(".edit_pass_box").hide();
    }
    function changePassword() {
        var oldPwd = $("#old_password").val();
        var newPwd1 = $("#new_password1").val();
        var newPwd2 = $("#new_password2").val();
        if (newPwd1 != newPwd2) {
            showPassError("新密码不一致");
            return false;
        }
        if (checkPasswordOnBlur()) {
            jQuery.ajax({
                url: "/admin/user/change/password",
                type: "POST",
                cache: false,
                data: {
                    oldPassword: oldPwd,
                    newPassword: newPwd1

                },
                success: function (data) {
                    if (data.flag) {
                        cancelChangePassword();
                        alert("修改密码成功！");
                        //showPassError("修改密码成功！");
                    } else {
                        if (data.msg == "login") {
                            window.location.href = "http://www.sangepg.com/jsp/user/user_login.html";
                        } else {
                            showPassError(data.msg);
                        }

                    }
                }
            });
        }

    }

    function checkPasswordOnBlur() {
        var b = check_pwd();
        if (b == 1) {
            showPassError("密码不能为空");
            return false;
        } else {
            if (b == 2) {
                showPassError("密码为6-20位字符");
                return false;
            } else {
                if (b == 3) {
                    showPassError("密码为6-20位字符");
                    return false;
                } else {
                    if (b == 4) {
                        showPassError("密码中不允许有空格");
                        return false;
                    } else {
                        if (b == 5) {
                            showPassError("密码不能全为数字");
                            return false;
                        } else {
                            if (b == 6) {
                                showPassError("密码不能全为字母，请包含至少1个数字或符号 ");
                                return false;
                            } else {
                                if (b == 7) {
                                    showPassError("密码不能全为符号");
                                    return false;
                                } else {
                                    if (b == 8) {
                                        showPassError("密码不能全为相同字符或数字");
                                        return false;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
    var commonSymbol = "[\\,\\`\\~\\!\\@\\#\\$\\%\\\\^\\&\\*\\(\\)\\-\\_\\=\\+\\[\\{\\]\\}\\\\|\\;\\:\\‘\\’\\“\\”\\<\\>\\/?]+";
    function check_pwd() {
        var p = $("#new_password1").val();
        if (p == "") {
            return 1;
        }
        if (p.length > 20) {
            return 2;
        }
        if (p.length < 6) {
            return 3;
        }
        var m = /\s+/;
        if (m.test(p)) {
            return 4;
        }
        var k = /^[0-9]+$/;
        if (k.test(p)) {
            return 5;
        }
        var j = /^[a-zA-Z]+$/;
        if (j.test(p)) {
            return 6;
        }
        var o = /^[^0-9A-Za-z]+$/;
        if (o.test(p)) {
            return 7;
        }
        if (isSameWord(p)) {
            return 8;
        }
        var n = "d*" + commonSymbol + "";
        var i = "\\\d+[A-Za-z]|[A-Za-z]+[0-9]+|[A-Za-z]+" + commonSymbol
                + "[0-9]+|[A-Za-z]+[0-9]+" + commonSymbol + "|" + n + "";
        var l = new RegExp(i);
        if (!l.test(p)) {
            return 10;
        }
        return 0;
    }
    function isSameWord(g) {
        var e;
        if (g != null && g != "") {
            e = g.charCodeAt(0);
            e = "\\" + e.toString(8);
            var f = "[" + e + "]{" + (g.length) + "}";
            var h = new RegExp(f);
            return h.test(g);
        }
        return true;
    }
    function showPassError(desc) {
        $("#password_error").html(desc);
    }
</script>


</body>

</html>