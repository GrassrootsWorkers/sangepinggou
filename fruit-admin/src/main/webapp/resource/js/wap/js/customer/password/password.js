function getCode(){
    $("#btnCode").hide();
    var phone = $("#phone").html().trim();
	$.ajax({ url: "http://my.m.111.com.cn/ucenter/sendPasswordSms.action", type: "POST", data: {"phone": phone}, success: function(data) {
		var res = eval('(' + data + ')')[0];   
        if (res.success) {
            $("#countdown").show();
            var i = 59;
            var intervalT = setInterval(function() {
                i--;
                $("#timer").html(i);
                if (i <= 0) {
                    clearInterval(intervalT);
                    $("#timer").html(59);
                    $("#btnCode").show();
                    $("#countdown").hide();
                }
            }, 1000);
         }else{
        	jAlert(res.msg);
        	$("#btnCode").show();
            return false;
        }
       } 
    });
 }
function checkCode() {
    var code = $("#phonecode").val();
    if (code == "" || code.length == 0) {
    	jAlert("请输入验证码");
        return false;
    }
    return true;
}
function submitPassword(){
    var phone =	$("#phone").html().trim();
    var sms = $("#phonecode").val();
    $.ajax({ url: "http://my.m.111.com.cn/ucenter/checkPhoneCode.action", type: "POST", data: {"phone": phone, "sms":sms}, success: function(data) {
			var json = eval('(' + data + ')')[0];            
        	if (json.success) {
        		var phoneurl="http://my.m.111.com.cn/ucenter/toUpdatePassword.action";
     	   		location.href = phoneurl;
        	}else{
     	   	if("system" == json.elem) {
     	   		var s = json.msg;
     	   		jAlert(s);
	      		return false;
           	}else{
               	if("sms"==json.elem){
               		jAlert("校验码错误或失效，请检查或重新获取");
               		return false;
               	}
               	if("code"==json.elem){
               		jAlert("验证码错误");
               		return false;
               	}
               	if("phone"==json.elem){
               		var s = json.msg;
               		jAlert(s);
               		return false;
               	}
       			return false;
           	}
        }
    }});
}
function changType(){
	var type = 0;
	if($("#showPassword").attr("checked") == true){
		type = 1;
	}
	var pass = document.getElementById("password");
	if(type == 0){
		pass.type = "password";
	}else{
		pass.type = "text";
	}
}
function checkPassword(){
	var pw=$("#password").val();
	 if (pw == "" || pw.length == 0) {
		 jAlert("请输入密码");
        return false;
    }
    var regpassword = /^([0-9a-zA-Z_]{6,16})$/;
    if (!regpassword.test(pw) ) {
    	 jAlert("格式错误， 6-16位字符，可由英文、数字和字符组成");
        return false;
    }
    return true;
};

function updatePassword(){
    if (!checkPassword()) return false;
    var password = $("#password").val();
    var sms=$("#sms").val();
    var phone=$("#phone").val();
    $.ajax({ url: "http://my.m.111.com.cn/ucenter/updatePassword.action", type: "POST", data: {"password":password,"sms":sms,"phone":phone}, success: function(data) {
			var json = eval('(' + data + ')')[0];            
        	if (json.success) {
     	   	location.href = "http://my.m.111.com.cn/ucenter/myCenter.action";
        	}else{
     	   	if("system" == json.elem) {
     	   		jAlert("系统繁忙，请稍后再试");
	      		return false;
           	}else{
           		if("password"==json.elem){
           			jAlert("请输入密码");
           			return false;
           		}
           		if("toPassword"==json.elem){
           			location.href = "http://my.m.111.com.cn/ucenter/toPassword.action";
           		}
       			return false;
           	}
        	}
    }});
}


$(function(){
	changType();
});