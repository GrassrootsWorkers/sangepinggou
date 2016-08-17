function checkNameOnBlur() {
    //$("#div_name").addClass('input_error');
    var inputMobile = $("#userMobile").val();
    if (isMobileMethod(inputMobile)) {
        return true;
    } else {
        $("#mobile_error").show();
        return false;
    }
}
//验证是否正确手机号
function isMobileMethod(mobile) {
    var regPhone = /^(13[0-9]|15[0-9]|18[0-9]|147|145|17[0-9])\d{8}$/;
    if (regPhone.test(mobile)) {
        return true;
    } else {
        return false;
    }
}


var startNumber = 60;

function reObtainCode() {
    startNumber--;
    $("#timer").html("");
    if (startNumber <= 0) {
        clearInterval(interval);
        startNumber = 60;
        $("#countdown").hide();
        $("#fastLoginCode").show();
    } else {
        $("#timer").html(startNumber);
    }
}


//获取手机验证码
function obtainMobileCode() {
    if (checkNameOnBlur()) {
        //AJAX 发送手机验证码
        jQuery.ajax({
            type: "POST",
            dataType: "text",
            async: false,
            cache: false,
            url: "http://m.sangepg.com/front/user/getCode",
            data: "mobile=" + $.trim($("#userMobile").val()),
            success: function(res) {
                var json = eval("(" + res + ")");
                if (!json.success) {
                    myAlert(json.msg);
                } else {
                    $("#countdown").show();
                    $("#fastLoginCode").hide();
                    interval = setInterval(reObtainCode, 1000);
                }
            }
        });
    }
}

(function() {
    var cuserName = jQuery.cookie("mobile");
    if (cuserName && cuserName.trim() != "" && isMobileMethod(cuserName)) {
        $("#userMobile").val(cuserName);
        $("#userMobile").closest("div").addClass("input_close");
    }
    $("#userMobile").focus(function() {
        $("#mobile_error").hide();
        $("#mobile_empty").hide();
        $("#code_empty").hide();
        $("#code_error").hide();
        if (jQuery.trim($(this).val()) != "") {
            $("#userMobile").closest("div").removeClass("input_error").removeClass("input_ok").addClass("input_close");
        }
    }).blur(function() {
        if (jQuery.trim($(this).val()) == "") {
            $("#userMobile").closest("div").removeClass("input_close").removeClass("input_ok").addClass('input_error');
            $("#mobile_empty").show();
            return false;
        }
        if (!isMobileMethod(jQuery.trim($(this).val()))) {
            $("#mobile_error").show();
            $("#userMobile").closest("div").removeClass("input_close").removeClass("input_ok").addClass('input_error');
            return false;
        } else {
            $("#userMobile").closest("div").removeClass("input_close").removeClass("input_error").addClass('input_ok');
            checkBtn();
        }
    }).keyup(function() {
        if (jQuery.trim($(this).val()).length < 11) {
            $("#userMobile").closest("div").removeClass("input_error").removeClass("input_ok").addClass("input_close");
            $("#mobile_error").hide();
            $("#mobile_empty").hide();
            $("#fastLoginSubmit").addClass('disabled');

        }else if(jQuery.trim($(this).val()).length == 11&&isMobileMethod(jQuery.trim($(this).val()))){
            $("#userMobile").closest("div").removeClass("input_error").removeClass("input_close").addClass("input_ok");
            $("#mobile_error").hide();
            $("#mobile_empty").hide();
            checkBtn();
        }else{
            $("#userMobile").closest("div").removeClass("input_close").removeClass("input_ok").addClass("input_error");
            $("#mobile_error").show();
            $("#fastLoginSubmit").addClass('disabled');
        }
    });
    $("#mobileValidateCodeUL").focus(function() {
        $(this).parent().removeClass("input_error").removeClass("input_ok");
        $("#code_empty").hide();
        $("#code_error").hide();
    }).blur(function() {
        if (jQuery.trim($(this).val()) == "") {
            $(this).parent().addClass("input_error").removeClass("input_ok");
            $("#code_empty").show();
            return false;
        }
        checkBtn();
    }).keyup(function() {
        if (jQuery.trim($("#mobileValidateCode").val()).length >= 6){
            checkBtn();
        }
    }).keydown(function(event) {
        switch (event.keyCode) {
            case 10:
            case 13:
                $("#fastLoginSubmit").trigger("click");
                break;
        }
    });
})();

function checkBtn() {
    if ($("#userMobile").val() && $("#mobileValidateCode").val()) {
        validateSimpleLoginCode();
    }
}

function clearUserMobile() {
    $("#userMobile").val("");
    $("#userMobile").closest("div").removeClass("input_error").removeClass("input_ok").addClass("input_close");
    $("#mobile_error").hide();
    $("#mobile_empty").hide();
}

//验证快捷登陆密码是否正确
function validateSimpleLoginCode() {
    var userMobile = $.trim($("#userMobile").val());
    var loginCode = $.trim($("#mobileValidateCode").val());
    jQuery.ajax({
        type: "post",
        url: "http://m.sangepg.com/front/user/checkSimpleLoginCode",
        data: {
            "loginMobile": userMobile,
            "loginCode": loginCode
        },
        async: false,
        success: function(data) {
            var json = data;
            if (json.success) {
                $("#mobileValidateCode").parent().removeClass('input_error').addClass('input_ok');
                $("#fastLoginSubmit").removeClass('disabled');
                $("#code_error").hide();
            } else {
                $("#mobileValidateCode").parent().removeClass('input_ok').addClass('input_error');
                $("#code_error").show();
            }
        }
    });
}