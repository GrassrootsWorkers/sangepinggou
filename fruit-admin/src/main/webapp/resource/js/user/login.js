var commonSymbol = "[\\,\\`\\~\\!\\@\\#\\$\\%\\\\^\\&\\*\\(\\)\\-\\_\\=\\+\\[\\{\\]\\}\\\\|\\;\\:\\‘\\’\\“\\”\\<\\>\\/?]+";
function userLogin() {
    if (!validateValueIfEmpty()) {
        return false;
    } else {
        $.ajax({
            url: "/admin/user/login",
            type: "post",
            cache: false,
            data: {
                mobile: jQuery("#mobile").val(),
                validateCode: jQuery("#vcd").val(),
                password: jQuery("#pwd").val()

            },
            success: function (data) {
                if (data.success) {
                    window.location.href = "/admin/fruit/list?pageIndex=1&farmerId="+data.userId;
                } else {
                    if (data.elem == "mobile") {
                        jQuery("#mobile_div").addClass("input_error");
                        jQuery("#mobile_desc").text(data.msg);
                        jQuery("#mobile_desc").show();
                    }
                    if (data.elem == "pwd") {
                        jQuery("#pwd_div").addClass("input_error");
                        jQuery("#pwd_desc").text(data.msg);
                        jQuery("#pwd_desc").show();
                    }
                    if (data.elem == "vcd") {
                        jQuery("#vcd_div").addClass("input_error");
                        jQuery("#vcd_desc").text(data.msg);
                        jQuery("#vcd_desc").show();
                    }
                    if (data.elem == "error") {
                        jQuery("#error_desc").text(data.msg);
                        jQuery("#error_desc").show();
                    }
                }
            }
        });
    }

}
function validateValueIfEmpty() {
    var userName = jQuery("#mobile").val();
    var password = jQuery("#pwd").val();
    var vcd = jQuery("#vcd").val();
    var flag = true;
    if (userName == "") {
        jQuery("#mobile_desc").show();
        jQuery("#mobile_div").addClass("input_error");
        flag = false;
    }
    if (password == null) {
        jQuery("#pwd_div").addClass("input_error");
        jQuery("#pwd_desc").show();
        flag = false;
    }
    if (vcd == "") {
        jQuery("#vcd_div").addClass("input_error");
        jQuery("#vcd_desc").show();
        flag = false;
    }
    return flag;
}

function checkMobileOnBlur(id) {
    var mobileValue = jQuery("#" + id).val();
    if (mobile(mobileValue)) {
        jQuery("#" + id+"_div").removeClass("input_error");
        jQuery("#" + id + "_desc").hide();
    } else {
        jQuery("#" + id + "_desc").show();
        jQuery("#" + id+"_div").addClass("input_error");
        if (mobileValue == "") {
            jQuery("#" + id + "_desc").text("手机号不能为空");
        } else {
            jQuery("#" + id + "_desc").text("手机格式不正确");
        }

    }
}
function validatePassword(id){
    var p = jQuery("#pwd").val();
    var flag= pwd(p);
    if(flag !=0){
        jQuery("#" + id+"_div").addClass("input_error");
        jQuery("#"+id+"_desc").show();
        jQuery("#"+id+"_desc").text("密码不正确");
    }else{
        jQuery("#"+id+"_desc").hide();
        jQuery("#" + id+"_div").removeClass("input_error");
    }
}

function pwd(p) {
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
    var n = "d*" + commonSymbol + "";
    var i = "\\\d+[A-Za-z]|[A-Za-z]+[0-9]+|[A-Za-z]+" + commonSymbol
        + "[0-9]+|[A-Za-z]+[0-9]+" + commonSymbol + "|" + n + "";
    var l = new RegExp(i);
    if (!l.test(p)) {
        return 10;
    }
    return 0;
}

function validateCode(id){
    var code = jQuery("#"+id).val();
    if(code == ""){
        jQuery("#"+id+"_desc").show();
        jQuery("#"+id+"_desc").text("请填写验证码！");
        jQuery("#" + id+"_div").addClass("input_error");
    }else{
        jQuery("#"+id+"_desc").hide();
        jQuery("#" + id+"_div").removeClass("input_error");
    }

}
function mobile(value) {
    var regMobile = /^(13[0-9]||15[012356789]||18[0123456789]||147||145||17[0-9])\d{8}$/;
    if (!regMobile.test(value)) {
        return false;
    }
    return true;
}




