	var commonSymbol = "[\\,\\`\\~\\!\\@\\#\\$\\%\\\\^\\&\\*\\(\\)\\-\\_\\=\\+\\[\\{\\]\\}\\\\|\\;\\:\\‘\\’\\“\\”\\<\\>\\/?]+";
	var spliter = ",";
	var clickFlag=false;
	var nowid;
	var totalid;
	var can1press=false;
	var emailafter;
	var emailbefor;
	var isShow=true;
	var isRed=true;
	var showCodeFlag=false;
	function checkWords() {
		var d = jQuery("#password").val();
		var c = jQuery("#name").val();
		if (c == undefined) {
			return false;
		}
		if (c.substring(0, c.length - 1) == d) {
			return true;
		}
		if (c.substring(0, c.length - 1) == d.substring(0, d.length - 1)) {
			return true;
		}
		if (c.substring(1, c.length) == d) {
			return true;
		}
		if (c.substring(1, c.length) == d.substring(1, d.legnth)) {
			return true;
		}
		if (d.substring(0, d.length - 1) == c) {
			return true;
		}
		if (d.substring(1, d.length) == c) {
			return true;
		}
	}
	function trim(b) {
		return b.replace(/(^\s*)|(\s*$)/g, "");
	}
	function ltrim(b) {
		return b.replace(/(^\s*)/g, "");
	}
	function rtrim(b) {
		return b.replace(/(\s*$)/g, "");
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
	function hideOtherTips(b) {
		jQuery("#email_tip").hide();
		jQuery("#name_tip").hide();
		jQuery("#password_tip").hide();
		jQuery("#password2_tip").hide();
		jQuery("#validcode_tip").hide();
		if (jQuery("#" + b + "").val() == "") {
			jQuery("#" + b + "_tip").show();
		}
	}
	function check_email() {
		var d = jQuery("#email").val();
		if (d == "") {
			return 1;
		}
		var c = /^\w[\w\$\^\(\)\[\]\{\}\.\-\+,]{0,100}@([a-zA-Z0-9][\w\-]{0,14}\.){1,4}[a-zA-Z]{2,6}$/;
		if (!c.test(d)) {
			return 2;
		}
		if (d.length > 100) {
			return 3;
		}
		if ((/@yahoo.cn$\b/).test(d.toLowerCase())
				|| (/@yahoo.com.cn$\b/).test(d.toLowerCase())) {
			return 4;
		}
		return 0;
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
		if (checkWords()) {
			return 9;
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
	function checkPassWordContent() {
		jQuery("#password").removeClass("redErorBodr");
		var b = jQuery("#password").val();
		if (b.length > 0) {
			changePassStrong();
		} else {
			hideOtherTips("password");
		}
	}
	function check_referer() {
		var b = $("#referer").val().replace(/(^ *)|( *$)/g, "");
		if (b != "") {
			if ($("#refererDesc").html().indexOf("image") == -1) {
				return 1;
			}
		}
	}
	function checkCodeOnBlur() {
		if ($("#validCode").val() == "") {
			jAlert("验证码不能为空");
			return false;
		} else {
			if ($("#validCode").val().length != 4) {
				jAlert("验证码长度必须是4位");
				return false;
			}
			return true;
		}
	}
	function checkEmailOnBlur() {
		clickFlag = false;
		var b = check_email();
		if (b == 1) {
			jAlert("Email不能为空");
			return false;
		} else {
			if (b == 2) {
				jAlert("邮箱格式错误");
				return false;
			} else {
				if (b == 3) {
					jAlert("邮箱长度不能超过100位");
					return false;
				} else {
					if (b == 4) {
						jAlert("由于雅虎中国邮箱即将停用，请使用其他邮箱注册");
						return false;
					} else {
						var result =false;
						$.ajax({
							   type: "POST",
							   dataType: "text",
							   async: false,
							   cache : false,
							   url: "/sso/customer/validateEmail.action",
							   data: "email=" + $.trim($("#email").val()),
							   success: function(backEmail){
								   if (backEmail == "0") {
				                    	jQuery("#email_tip").hide();
				                    	result =  true;
				                    } else {
				                    	showEmailError("邮箱已被注册，请重新输入或使用该邮箱<a href='/sso/login.action'>登录</a>");
				                    	result =  false;
				                    }
							   }
						});
						return result;
					}
				}
			}
		}
	}
	function check_Name() {
		var d = $("#name").val();
		var c = /^[0-9a-zA-Z\,\$\^\(\)\-\_\+\[\]\{\}\u4e00-\u9fa5]{3,20}$/;
		var regPhone = /^(13[0-9]|15[0-9]|18[0-9]|147)\d{8}$/;
		if (d == "") {
			return 1;
		}
		if (d.length < 4) {
			return 2;
		}
		if (d.length > 20) {
			return 3;
		}
		if (!c.test(d)) {
			return 4;
		}
		if(regPhone.test(d)){
			return 5;
		}
	}
	function checkNameOnBlur() {
		var b = check_Name();
		if (b == 1) {
			jAlert("用户名不能为空");
			return false;
		} else if(b == 2){
			jAlert("用户名为4-20位字符");
			return false;
		}else if(b == 3){
			jAlert("用户名为4-20位字符");
			return false;;
		}else if(b == 4){
			jAlert("用户名格式错误");
			return false;
		}else if(b == 5){
			jAlert("用户名不能为手机号");
			return false;
		}else{
			var result =false;
			$.ajax({
				   type: "POST",
				   dataType: "text",
				   async: false,
				   cache : false,
				   url: "/sso/customer/validateName.action",
				   data: "userName=" + encodeURIComponent(encodeURIComponent($("#name").val())),
				   success: function(res){
					   if (res == "0"){
							result =  true;
			        	} else if (res == "1"){
			        		jAlert("用户名已被注册，请重新输入或使用该用户名<a href='/sso/login.action'>登录</a>");
			        		result =  false;
			        	} else {
			        		jAlert("用户名包含非法字符:" + res);
			        		result =  false;
			        	}
				   }
			});
			return result;
		}
	}
	function checkPasswordOnBlur() {
		hideOtherTips("password");
		var b = check_pwd1();
		if (b != 0) {
			jQuery("#password2").attr("readonly", "readonly");
		}
		if (b == 1) {
			jAlert("密码不能为空");
			return false;
		} else {
			if (b == 2) {
				jAlert("密码为6-20位字符");
				return false;
			} else {
				if (b == 3) {
					jAlert("密码为6-20位字符");
					return false;
				} else {
					if (b == 4) {
						jAlert("密码中不允许有空格");
						return false;
					} else {
						if (b == 5) {
							jAlert("密码不能全为数字");
							return false;
						} else {
							if (b == 6) {
								jAlert("密码不能全为字母，请包含至少1个数字或符号 ");
								return false;
							} else {
								if (b == 7) {
									jAlert("密码不能全为符号");
									return false;
								} else {
									if (b == 8) {
										jAlert("密码不能全为相同字符或数字");
										return false;
									} else {
										if (b == 9) {
											jAlert("密码与用户名相似，为了您的账户安全请重新输入");
											return false;
										}else{
											jQuery("#password2").removeAttr("readonly");
											return true;
										} 
									}
								}
							}
						}
					}
				}
			}
		}
	}
	function checkPassword2OnBlur() {
		var b = check_pwd2();
		if (b == 1) {
			jAlert("确认密码不能为空 ");
			return false;
		} else {
			if (b == 2) {
				jAlert("两次密码输入不一致");
				return false;
			} else {
				hideOtherTips("none");
				return true;
			}
		}
	}
	function getPassPoint() {
		var c = jQuery("#password").val();
		var d = checkPassLength(c);
		d = d + checkPassSymbol(c);
		d = d + checkPassNumAndWord(c);
		d = d + (checkPassAll(c));
		d = d + checkPassAlpha(c);
		d = d + checkPassNum(c);
		return d;
	}
	function checkPassLength(b) {
		if (b.length > 6 && b.length < 8) {
			return 10;
		}
		if (b.length >= 8) {
			return 25;
		}
		return 0;
	}
	function checkPassSymbol(b) {
		if (getSymbolPattern(2).test(b)) {
			return 25;
		} else {
			if (getSymbolPattern(1).test(b)) {
				return 10;
			}
		}
		return 0;
	}
	function getSymbolPattern(f) {
		var e = "" + commonSymbol.substr(0, commonSymbol.length - 1) + "{" + f
				+ ",}";
		var d = new RegExp(e);
		return d;
	}
	var patternNumAlpha = /^(?=.*\d.*)(?=.*[a-zA-Z].*)./;
	function checkPassNumAndWord(b) {
		if (patternNumAlpha.test(b)) {
			return 5;
		}
		return 0;
	}
	function isDigit(c) {
		var d = /(?=.*[0-9])/;
		return getCompareResult(d, c);
	}
	function isBigWord(c) {
		var d = /(?=.*[A-Z])/;
		return getCompareResult(d, c);
	}
	function isSymbol(f) {
		var d = "(?=.*" + commonSymbol.substr(0, commonSymbol.length - 1) + ")";
		var e = new RegExp(d);
		return getCompareResult(e, f);
	}
	function isSmallWord(c) {
		var d = /(?=.*[a-z])/;
		return getCompareResult(d, c);
	}
	function getCompareResult(d, c) {
		if (d.test(c)) {
			return true;
		}
		return false;
	}
	function checkPassAll(b) {
		if (isDigit(b) && isBigWord(b) && isSmallWord(b) && isSymbol(b)) {
			return 5;
		}
		if (patternNumAlpha.test(b)) {
			if (isSymbol(b)) {
				return 3;
			} else {
				return 2;
			}
		}
		return 0;
	}
	function checkPassAlpha(d) {
		var f = /^[a-z]+$|^[A-Z]+$/;
		if (f.test(d)) {
			return 10;
		}
		var e = /.*?[A-Z]+?.*?[a-z]+?.*?|.*?[a-z]+?.*[A-Z]+?.*?/;
		if (e.test(d)) {
			return 25;
		}
		return 0;
	}
	function checkPassNum(b) {
		if (getNumPattern(3).test(b)) {
			return 20;
		}
		if (getNumPattern(1).test(b)) {
			return 10;
		}
		return 0;
	}
	function getNumPattern(f) {
		var e = "[0-9]{" + f + ",}";
		var d = new RegExp(e);
		return d;
	}
	function changePassStrong() {
		var c = jQuery("#password");
		if (check_pwd1() == 0) {
			jQuery("#password2").removeAttr("readonly");
			jQuery("#password2").css("background-color", c.css("background-color"));
		} else {
			jQuery("#password2").attr("readonly", "readonly");
			jQuery("#password2").css("background-color", "#D2D2D5");
		}
		if (c.val().length == 0) {
			jQuery("#pswdLevel").hide();
			hideOtherTips("password");
			return
		} else {
			jQuery("#password_tip").hide();
		}
		var d = getPassPoint(c);
		jQuery("#pswdLevel").attr("class", "").addClass("pswdLevelA").attr("style",
				"display:block");
		if (d >= 80) {
			jQuery("#pswdLevel").attr("class", "").addClass("pswdLevelC").attr(
					"style", "display:block");
			return
		}
		if (d >= 50) {
			jQuery("#pswdLevel").attr("class", "").addClass("pswdLevelB").attr(
					"style", "display:block");
			return
		}
	}
	function checkRefererLink() {
		var b = location.search;
		if (b.indexOf("rlink") != -1) {
			$("#referer").attr("readonly", "readonly");
		}
	}
	function passwordOnFocus() {
		var b = jQuery("#password");
		if (b.val() == "") {
			hideOtherTips("password");
			jQuery("#password_tip").show();
			return
		}
		checkPassWordContent();
		hideOtherTips("password2");
	};

	function emailOnkeyUp(){
		if(clickFlag==false){
			$("#myemail").remove();
			$("#email").after("<div id='myemail'></div>");
			if($("#myemail").html()){
				$("#myemail").css("display","block");
				$(".newemail").css("width",$("#myemail").width());
				can1press=true;
			}else{
				$("#myemail").css("display","none");
				can1press=false;
			}
		}
			var b=$("#email").val();
			if((b!=""||b!=null)&&clickFlag==false&&isShow==true){
				var a="";
				var i=new Array("@163.com","@qq.com","@126.com","@hotmail.com","@gmail.com","@sohu.com","@139.com","@sina.com.cn","@vip.sina.com","@msn.com");
				totalid=i.length;
				if(!(isEmail(b))){
					for(var h=0;h<i.length;h++){
						a=a+"<div class='newemail' style='width:170px; color:#6B6B6B; overflow:hidden;' onclick=''><font >";
						a=a+b+"</font>"+i[h]+"</div>";
				}
			}else{
				emailbefor=b.split("@")[0];
				emailafter="@"+b.split("@")[1];
				for(var h=0;h<i.length;h++){var j=i[h];if(j.indexOf(emailafter)==0){
					a=a+"<div class='newemail' style='width:170px; color:#6B6B6B; overflow:hidden;' onclick=''><font color='#D33022'>";
					a=a+emailbefor+"</font>"+i[h]+"</div>";
				}
			}
		}
		$("#myemail").html(a);
		if($("#myemail").html()){
			$("#myemail").css("display","block");
			$(".newemail").css("width",$("#myemail").width());
			can1press=true;
		}else{
			$("#myemail").css("display","none");
			can1press=false;
		}
		beforepress=b;
		}if(b==""||b==null){
			$("#myemail").html("");$("#myemail").css("display","none");
		}
	}
		
	function isEmail(b){
		if(b.indexOf("@")>0){
			return true;
		}else{
			return false;
		}
	}
	function checkXy(){
		if($("#xyCheckBox:checked").val()=="1"){
			return true;
		}
		jAlert("注册需要同意壹药网服务协议!")
		return false;
	}
function registerSubmit() {
	if(!checkNameOnBlur()){
		return ;
	}
	if(!checkEmailOnBlur()){
		return ;
	}
	var checkPassword = checkPasswordOnBlur();
	var checkPassword2 = checkPassword2OnBlur();
	var checkCode = checkCodeOnBlur();
	if(!checkXy()){
		return ;
	}
	var username = $.trim($("#name").val());
	var email = $.trim($("#email").val());
	var pass = $("#password").val();
	var vcode = $.trim($("#validCode").val());
	if (checkPassword && checkPassword2 && checkCode) {
		$.ajax({
			url : "/front/register/regist.action",
			type : "POST",
			async: false,
			cache : false,
			data : {
				userName : encodeURIComponent(username),
				email : email,
				password : pass,
				code : encodeURIComponent(vcode)
			},
			success : function(data) {
				var json = eval('(' + data + ')')[0];
				if (json.success) {
					window.location.href = '/sso/login.action';
				} else {
					jAlert(json.msg);
				}
			}
		});
	} else {
		return false;
	}
}
function getVceCode(){
	$("#vcdCode").attr("src","/sso/getSecurityCode.action?t=" + (new Date()).getTime());
}
	$(document).ready(function() {
		getVceCode();
		$('#name').bind({
//			  blur: function() {
//				 checkNameOnBlur();
//			  },
			  focus: function() {
				  hideOtherTips("name");
			  }
		});
		
		$('#email').bind({
//			keyup: function(){
//				emailOnkeyUp();	
//			}  ,
//			blur: function() {
//				checkEmailOnBlur();
//		    },
		    focus: function() {
			  hideOtherTips('email');
		   }
		});
		
		$('#password').bind({
//			  blur: function() {
//				  checkPasswordOnBlur();
//			  },
			  focus: function() {
				  passwordOnFocus();
			  },
			  copy: function() {
				  return false;
			  },
			  cut: function() {
				  return false;
			  }, 
			  paste: function() {
				  return false;
			  },
			  keyup: function(){
				  changePassStrong();
			  }
		});
		$('#password2').bind({
//			  blur: function() {
//				  checkPassword2OnBlur();
//			  },
			  focus: function() {
				  hideOtherTips('password2');
			  },
			  copy: function() {
				  return false;
			  },
			  cut: function() {
				  return false;
			  }, 
			  paste: function() {
				  return false;
			  }
		});
		$('#validCode').bind({
//			blur: function() {
//				checkCodeOnBlur();
//		    }
		});
		$('#confirmForm').bind({
			click: function() {
				registerSubmit();
		    }
		});
	});
	