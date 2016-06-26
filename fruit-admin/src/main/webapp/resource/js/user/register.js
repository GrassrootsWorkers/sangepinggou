var commonSymbol = "[\\,\\`\\~\\!\\@\\#\\$\\%\\\\^\\&\\*\\(\\)\\-\\_\\=\\+\\[\\{\\]\\}\\\\|\\;\\:\\‘\\’\\“\\”\\<\\>\\/?]+";
var mobileFlag = false;
var paswFlag = false;
var countdown = 60;
function hideOtherTips(id) {
    var value = jQuery("#" + id).val();
    if (value == "") {
        showTip(id);
    }
}
function checkMobileOnBlur(id) {
    var mobileValue = jQuery("#" + id).val();
    if (mobile(mobileValue)) {
        jQuery("#" + id+"_div").removeClass("input_error");
        mobileFlag = true;
        showDes(id);
    } else {
        jQuery("#" + id+"_div").addClass("input_error");
        mobileFlag = false;
        jQuery("#" + id + "_error").show();
        if (mobileValue == "") {
            jQuery("#" + id + "_error").text("手机号不能为空");
        } else {
            jQuery("#" + id + "_error").text("手机格式不正确");
        }

    }
}
function showTip(id) {
    jQuery("#" + id + "_tip").show();
    jQuery("#" + id + "_desc").hide();
    jQuery("#" + id + "_error").hide();
}
function showDes(id) {
    jQuery("#" + id + "_tip").hide();
    jQuery("#" + id + "_desc").show();
    jQuery("#" + id + "_error").hide();
}
function showError(id) {
    jQuery("#" + id + "_tip").hide();
    jQuery("#" + id + "_desc").hide();
    jQuery("#" + id + "_error").show();
}
function hideAll(id) {
    jQuery("#" + id + "_tip").hide();
    jQuery("#" + id + "_desc").hide();
    jQuery("#" + id + "_error").hide();
}
function showMobileOff(id) {
    showTip(id);
}
function mobile(value) {
    var regMobile = /^(13[0-9]||15[012356789]||18[0123456789]||147||145||17[0-9])\d{8}$/;
    if (!regMobile.test(value)) {
        return false;
    }
    return true;
}
function validateCodeError(id) {

    var codeValue = jQuery('#' + id).val();
    if (codeValue == "") {
        jQuery('#' + id+"_div").addClass("input_error");
        jQuery("#" + id + "_error").text("输入六位的验证码");
        return;
    }
    var code = /^[0-9]{6}$/;
    if (!code.test(codeValue)) {
        jQuery("#" + id + "_error").text("输入六位的验证码");
        jQuery('#' + id+"_div").addClass("input_error");
        showError(id);
    } else {
        jQuery('#' + id+"_div").removeClass("input_error");
        hideAll(id);
    }
}
function showTime(o) {
    if (countdown == 1) {
        $(o).removeAttr("disabled");
        $("#show_time").hide();
        countdown = 60;
    } else {
        countdown--;
        var showDesc = "校验码已发出，你可以在" + countdown + "秒后要求系统重新发";
        $(o).attr("disabled", true);
        $("#show_time").show();
        $("#show_time").html(showDesc);
        setTimeout(function () {
            showTime(o)
        }, 1000)
    }
}
function sendValidateCode(o,id1, id2, type) {
    var mobileValue = jQuery("#" + id1).val();
    if (mobileValue == "") {
        showError(id2);
        jQuery("#" + id2 + "_error").text("请输入手机号");
        return false;
    }
    if (mobile(mobileValue)) {
        hideAll(id2);
        $.ajax({
            url: "/admin/user/validate/"+mobileValue,
            type: "GET",
            cache: false,
            data: {
                codeType: type
            },
            success: function (data) {
                if (data.success) {
                    showTime(o);
                } else {
                    showError(id2);
                    jQuery("#" + id2 + "_error").text(data.msg);
                    return false;
                }
            }
        });
    } else {
        showError(id2);
        jQuery("#" + id2 + "_error").text("请输入正确的手机号");
        return false;
    }

}

function checkPasswordOnBlur(id) {
    var b = check_pwd1();
    if (b != 0) {
        paswFlag = false;
        jQuery("#"+id+"_div").addClass("input_error");
        //jQuery("#password2").attr("readonly", "readonly");
    } else {
        jQuery("#"+id+"_div").removeClass("input_error");
        showDes(id);
        paswFlag = true;
    }
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

}

function check_pwd1() {
    var p = $("#password").val();
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
function showPassError(b) {
    showError("password");
    jQuery("#password_error").text(b);
}
function checkPassword2OnBlur(id) {
    var b = check_pwd2();
    paswFlag = false;
    if (b == 1) {
        jQuery("#"+id+"_div").addClass("input_error");
        showPass2Error("确认密码不能为空 ");
        return false;
    } else {
        if (b == 2) {
            jQuery("#"+id+"_div").addClass("input_error");
            showPass2Error("两次密码输入不一致");
            return false;
        } else {
            showDes(id);
            jQuery("#"+id+"_div").removeClass("input_error");
            paswFlag = true;
            return true;
        }
    }
}

function check_pwd2() {
    var d = $("#password").val();
    var c = $("#password2").val();
    if (c == "") {
        return 1;
    }
    if (d != c) {
        return 2;
    }
    return 0;
}
function showPass2Error(b) {
    showError("password2");
    jQuery("#password2_error").text(b);
}

function register() {
    if (paswFlag == true && mobileFlag === true) {
        $.ajax({
            url: "/admin/user/register",
            type: "post",
            cache: false,
            data: {
                mobile: jQuery("#mobile").val(),
                validateCode: jQuery("#vcode").val(),
                password: jQuery("#password").val(),
                type: "0",
                nationCode: jQuery("#nation_code").val()
            },
            success: function (data) {
                if (data.success) {
                    jQuery("#user_center").attr("href", "/user/info/" + data.userId);
                    confirmSuccessTip('bg_fixed_register','win_box_register','win_close_register')
                } else {
                    $("#" + data.elem + "_error").show();
                    $("#" + data.elem + "_error").html(data.msg);
                }
            }
        });
    }


}
function checkOrgOnBlur(id) {
    if (jQuery('#' + id).val() == "") {
        jQuery('#' + id + "_error").show();
        return false;
    } else {
        jQuery('#' + id + "_error").hide();
        return true;
    }
}

function orgRegister() {
    jQuery("#register").attr("disabled","disabled");
    if (paswFlag == true && mobileFlag === true && checkOrgOnBlur("org_name")) {
        $.ajax({
            url: "/user/register",
            type: "post",
            cache: false,
            data: {
                mobile: jQuery("#mobile").val(),
                code: jQuery("#vcode").val(),
                password: jQuery("#password").val(),
                type: "1",
                nationCode: jQuery("#nation_code").val(),
                organization: jQuery("#org_name").val()
            },
            success: function (data) {
                if (data.success) {
                    jQuery("#user_center").attr("href", "/user/info/" + data.userId);
                    confirmSuccessTip('bg_fixed_register','win_box_register','win_close_register')
                } else {
                    o_confirm('bg_fixed_bg_noBtn','win_box_noBtn','win_close_noBtn',data.msg);
                }
            }
        });
    }
}
function confirmSuccessTip(bg_fixed,win_box,win_close){
    var o_bg_fixed = jQuery("."+bg_fixed);
    var o_win_box = jQuery("."+win_box);
    var o_win_close = jQuery("."+win_close);

    if(o_win_box.is(":hidden")==true){
        jQuery(o_win_box).fadeIn();
        jQuery(o_bg_fixed).css("display","block");
        jQuery(o_win_close).click(function(){
            jQuery(o_win_box).fadeOut("slow");
            jQuery(o_bg_fixed).css("display","none");
        })
    }
}
function checkRestPwdMobileOnBlur(id) {
    var mobileValue = jQuery("#" + id).val();
    if (!mobile(mobileValue)) {
        jQuery("#" + id + "_error").show();
        if (mobileValue == "") {
            jQuery("#" + id + "_error").text("手机号不能为空");
        } else {
            jQuery("#" + id + "_error").text("手机格式不正确");
        }
        return false;
    }
    return true;
}

function recoveredPassword() {
    if (paswFlag == true && checkRestPwdMobileOnBlur('mobile')) {
        $.ajax({
            url: "/user/resetPwd",
            type: "POST",
            cache: false,
            data: {
                userName: jQuery("#mobile").val(),
                mobile: jQuery("#mobile").val(),
                code: jQuery("#vcode").val(),
                password: jQuery("#password").val()
            },
            success: function (data) {
                if (data.success) {
                    window.location.href = "/jsp/user/user_login.html";
                } else {
                    o_confirm('bg_fixed_bg_noBtn','win_box_noBtn','win_close_noBtn',data.msg);
                }
            }
        });
    } else {
        return false;
    }
}
function fastLogin(){
    if (checkRestPwdMobileOnBlur('mobile')) {
        $.ajax({
            url: "/user/login/"+jQuery("#vcode").val(),
            type: "POST",
            cache: false,
            data: {
                userName: jQuery("#mobile").val()
            },
            success: function (data) {
                if (data.success) {
                    var returnUrl = $.cookie("returnUrl");
                    if(typeof  returnUrl != "undefined"){
                        window.location.href = returnUrl;
                    }else{
                        window.location.href = "http://www.binggou.com";
                    }

                } else {
                    jQuery("#"+data.elem).html(data.msg);
                }
            }
        });
    } else {
        return false;
    }
}