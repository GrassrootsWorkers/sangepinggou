function phoneCode(){
	$("#phoneNum").siblings(".er-tips").hide();
    if (!checkPhone()) return false;
    $("#btnCode").hide();
    $.post("http://my.m.111.com.cn/ucenter/phoneAuthCode.action", "phone=" + $("#phoneNum").val(), function(res) {
        if ("success" == res) {
            $("#countdown").show();
            var i = 61;
            var intervalT = setInterval(function() {
                i--;
                $("#timer").html(i);
                if (i <= 0) {
                    clearInterval(intervalT);
                    $("#timer").html(60);
                    $("#btnCode").show();
                    $("#countdown").hide();
                }
            }, 1050);
        }else{
        	jAlert(res);
        	$("#btnCode").show();
            return false;
        }
    });
}
function checkPhone() {
	var regPhone = /^(13[0-9]|15[012356789]|18[012356789]|147|145)\d{8}$/;
    var phone = $("#phoneNum").val();
    if (!regPhone.test(phone) || phone == "") {
    	jAlert("请输入正确的手机号");
   		return false;
    }
    return true;
}

function checkCode() {
    var code = $("#phonecode").val();
    if (code == "" || code.length == 0) {
    	jAlert("请输入验证码");
        return false;
    }
    return true;
}

function submitAuthPhone(){
    if (!checkPhone()) return false;
    if (!checkCode()) return false;
    var phone = $("#phoneNum").val();
    var sms = $("#phonecode").val();
    $.ajax({ url: "http://my.m.111.com.cn/ucenter/phoneAuth.action", type: "POST", data: {"phone": phone, "sms":sms}, success: function(data) {
			var json = eval('(' + data + ')')[0];            
        	if (json.success) {
     	   	location.href = "http://my.m.111.com.cn/ucenter/phoneAuthSuc.action";
        	}else{
     	   	if("system" == json.elem) {
    			location.href = "http://my.m.111.com.cn/ucenter/phoneAuthFail.action";
           	}else{
           		jAlert(json.msg);
       			return false;
           	}
        	}
    }});
}