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
    	jAlert("��������֤��");
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
               		jAlert("У��������ʧЧ����������»�ȡ");
               		return false;
               	}
               	if("code"==json.elem){
               		jAlert("��֤�����");
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
		 jAlert("����������");
        return false;
    }
    var regpassword = /^([0-9a-zA-Z_]{6,16})$/;
    if (!regpassword.test(pw) ) {
    	 jAlert("��ʽ���� 6-16λ�ַ�������Ӣ�ġ����ֺ��ַ����");
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
     	   		jAlert("ϵͳ��æ�����Ժ�����");
	      		return false;
           	}else{
           		if("password"==json.elem){
           			jAlert("����������");
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