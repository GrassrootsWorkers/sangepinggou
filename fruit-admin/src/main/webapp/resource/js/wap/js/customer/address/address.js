function showcity(){
	var provinceid = $("#province").val();
 	if(provinceid != null && provinceid != 0){
    	var url = "http://my.m.111.com.cn/ucenter/findCity.action?provinceid="+provinceid;
		jQuery.getScript(url, function() {
	    	if(citys == null){
	         	return ;
	     	}
	     	var html = '<option value="0">请选择城市</option>';
	    	for(var i=0 ; i<citys.length ; i++){
	        	var city = citys[i];
	       		var con = "<option value='"+city.id+"'>"+city.name+"</option>";
	       		html += con;
	    	}
	     	$("#city").html(html);
		});
	}
}
function showcounty(){
	var cityid = $("#city").val();
	if(cityid != null && cityid != 0){
    	var url = "http://my.m.111.com.cn/ucenter/findCounty.action?cityid="+cityid;
    	jQuery.getScript(url, function() {
        	if(countys  == null){
         		return ;
         	}
         	var html = '<option value="0">请选择区县</option>';
         	for(var i=0 ; i<countys .length ; i++){
         		var county = countys [i];
         		var con = "<option value='"+county.id+"'>"+county.name+"</option>";
         		html += con;
       		}
       		$("#county").html(html);
     	});
  	}
}
function checkName() {
	var name = $.trim($("#realName").val());
	if (name == "" || name.length == 0 || name.length > 20) {
		jAlert("请填写收货人姓名，不超过6个字");
		$("#realName").focus();
		return false;
	}
	return true;
}
function checkArea() {
	if ($("#province").val() == 0 || $("#city").val() == 0
			|| $("#county").val() == 0) {
		jAlert("请选择收货地区");
		$("#province").focus();
		return false;
	}
	return true;
}
function checkAddress() {
	var addr = $.trim($("#address").val());
	if (addr == "" || addr.length < 5) {
		jAlert("请填写正确详细地址");
		$("#address").focus();
		return false;
	}
	return true;
}

function checkBoth() {
	var phone = $("#mobile").val();
	var tel = $("#tel").val();
	if ((phone == "" || phone.length == 0)
			&& (tel == "" || tel.length == 0)) {
		jAlert("请填写手机或电话信息");
		$("#mobile").focus();
		return false;
	} else if (phone.length > 0 && tel.length == 0) {
		if (!checkPhone()) {
			jAlert("请填写正确手机号码");
			$("#mobile").focus();
			return false;
		}
	} else if (phone.length == 0 && tel.length > 0) {
		if (!checkTel()) {
			jAlert("固定电话请参考021-12345678");
			$("#tel").focus();
			return false;
		}

	} else {
		if (!checkPhone()) {
			jAlert("请填写正确手机号码");
			$("#mobile").focus();
			return false;
		}
		if (!checkTel()) {
			jAlert("固定电话请参考021-12345678");
			$("#tel").focus();
			return false;
		}
	}
	return true;
}
function checkTel() {
	var regTel = /^(\d{3,4}\-?)?\d{7,8}$/;
	var tel = $("#tel").val();
	if (!regTel.test(tel)) {
		jAlert("固定电话请参考021-12345678");
		return false;
	}
	return true;
}
function checkPhone() {
	var regPhone = /^(13[0-9]|15[012356789]|18[012356789]|147|145)\d{8}$/;
	var phone = $("#mobile").val();
	if (!regPhone.test(phone)) {
		jAlert("请填写正确手机号码");
		return false;
	}
	return true;
}
	function saveAddress() {
	if (!checkName())
		return false;
	if (!checkBoth())
		return false;
	if (!checkArea())
		return false;
	if (!checkAddress())
		return false;
	var name = $.trim($("#realName").val());
	var province = $("#province").find("option:selected").text();
	var city = $("#city").find("option:selected").text();
	var county = $("#county").find("option:selected").text();
	var provinceid = $.trim($("#province").val());
	var cityid = $.trim($("#city").val());
	var countyid = $.trim($("#county").val());
	var address = $.trim($("#address").val());
	var phone = $.trim($("#mobile").val());
	var tel = $.trim($("#tel").val());
	var addressType = 0;
	if($("#isDefault").prop("checked") == true){
		addressType= 1;
	}
	$.ajax({
		url : "http://my.m.111.com.cn/ucenter/addPtAddress.action",
		type : "POST",
		cache : false,
		data : {
			"realName" : encodeURIComponent(name),
			"provinceName" : encodeURIComponent(province),
			"cityName" : encodeURIComponent(city),
			"countyName" : encodeURIComponent(county),
			"province" : provinceid,
			"city" : cityid,
			"county" : countyid,
			"address" : encodeURIComponent(address),
			"mobile" : phone,
			"tel" : tel,
			"addressType":addressType
		},
		success : function(data) {
			if (data == 1) {
				parent.location.href = "http://my.m.111.com.cn/ucenter/addressList.action";
			} else if (data == 2) {
				jAlert("请填写收货人姓名，不超过6个字");
			} else if (data == 3) {
				jAlert("请选择收货地区");
			} else if (data == 4) {
				jAlert("请填写正确详细地址");
			} else if (data == 5) {
				jAlert("请填写正确手机号码");
			} else if (data == 6) {
				jAlert("地址已满20条，无法再添加新地址");
			} else if (data == 7) {
				jAlert("系统繁忙，请稍后再试");
			} else if (data == 8) {
				jAlert("系统繁忙，请稍后再试");
			} 
		}
	});
}
	
function updateAddress(id) {
	if (!checkName())
		return false;
	if (!checkArea())
		return false;
	if (!checkAddress())
		return false;
	if (!checkBoth())
		return false;
	var name = $.trim($("#realName").val());
	var province = $("#province").find("option:selected").text();
	var city = $("#city").find("option:selected").text();
	var county = $("#county").find("option:selected").text();
	var provinceid = $.trim($("#province").val());
	var cityid = $.trim($("#city").val());
	var countyid = $.trim($("#county").val());
	var address = $.trim($("#address").val());
	var phone = $.trim($("#mobile").val());
	var tel = $.trim($("#tel").val());
	var postcode = $.trim($("#postcode").val());
	var addressType = 0;
	if($("#isDefault").prop("checked") == true){
		addressType= 1;
	}
	$.ajax({
		url : "http://my.m.111.com.cn/ucenter/editPtAddress.action",
		type : "POST",
		cache : false,
		data : {
			"id" : id,
			"realName" : encodeURIComponent(name),
			"provinceName" : encodeURIComponent(province),
			"cityName" : encodeURIComponent(city),
			"countyName" : encodeURIComponent(county),
			"province" : provinceid,
			"city" : cityid,
			"county" : countyid,
			"address" : encodeURIComponent(address),
			"mobile" : phone,
			"tel" : tel,
			"addressType":addressType
		},
		success : function(data) {
			if (data == 1) {
				parent.location.href = "http://my.m.111.com.cn/ucenter/addressList.action";
			} else if (data == 2) {
				jAlert("请填写收货人姓名，不超过6个字");
			} else if (data == 3) {
				jAlert("请选择收货地区");
			} else if (data == 4) {
				jAlert("请填写正确详细地址");
			} else if (data == 5) {
				jAlert("请填写正确手机号码");
			} else if (data == 6) {
				jAlert("系统繁忙，请稍后再试");
			} else if (data == 7) {
				jAlert("系统繁忙，请稍后再试");
			}
		}
	});
}

function addAddress(){
	$.ajax({ url: "http://my.m.111.com.cn/ucenter/isLastAddress.action", type: "POST", cache: false, 
	 	success: function(data) {  
	 		if(data == 1){
	 			jAlert("地址已满20条，无法再添加新地址");
	 		}else{
	 			window.location.href="http://my.m.111.com.cn/ucenter/toAddAddress.action";
	 		}
 		}
 	});
}

function deleteAddress(obj){
	var addressid = obj;
	$.ajax({ url: "http://my.m.111.com.cn/ucenter/deleteAddress.action", type: "POST", cache: false, data: {"id":addressid},
	 	success: function(data) {  
	 		window.location.href = 'http://my.m.111.com.cn/ucenter/addressList.action';
 		}
 	});
}