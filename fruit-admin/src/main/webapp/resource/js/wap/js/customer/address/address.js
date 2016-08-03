function showcity(){
	var provinceid = $("#province").val();
 	if(provinceid != null && provinceid != 0){
    	var url = "http://my.m.111.com.cn/ucenter/findCity.action?provinceid="+provinceid;
		jQuery.getScript(url, function() {
	    	if(citys == null){
	         	return ;
	     	}
	     	var html = '<option value="0">��ѡ�����</option>';
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
         	var html = '<option value="0">��ѡ������</option>';
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
		jAlert("����д�ջ���������������6����");
		$("#realName").focus();
		return false;
	}
	return true;
}
function checkArea() {
	if ($("#province").val() == 0 || $("#city").val() == 0
			|| $("#county").val() == 0) {
		jAlert("��ѡ���ջ�����");
		$("#province").focus();
		return false;
	}
	return true;
}
function checkAddress() {
	var addr = $.trim($("#address").val());
	if (addr == "" || addr.length < 5) {
		jAlert("����д��ȷ��ϸ��ַ");
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
		jAlert("����д�ֻ���绰��Ϣ");
		$("#mobile").focus();
		return false;
	} else if (phone.length > 0 && tel.length == 0) {
		if (!checkPhone()) {
			jAlert("����д��ȷ�ֻ�����");
			$("#mobile").focus();
			return false;
		}
	} else if (phone.length == 0 && tel.length > 0) {
		if (!checkTel()) {
			jAlert("�̶��绰��ο�021-12345678");
			$("#tel").focus();
			return false;
		}

	} else {
		if (!checkPhone()) {
			jAlert("����д��ȷ�ֻ�����");
			$("#mobile").focus();
			return false;
		}
		if (!checkTel()) {
			jAlert("�̶��绰��ο�021-12345678");
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
		jAlert("�̶��绰��ο�021-12345678");
		return false;
	}
	return true;
}
function checkPhone() {
	var regPhone = /^(13[0-9]|15[012356789]|18[012356789]|147|145)\d{8}$/;
	var phone = $("#mobile").val();
	if (!regPhone.test(phone)) {
		jAlert("����д��ȷ�ֻ�����");
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
				jAlert("����д�ջ���������������6����");
			} else if (data == 3) {
				jAlert("��ѡ���ջ�����");
			} else if (data == 4) {
				jAlert("����д��ȷ��ϸ��ַ");
			} else if (data == 5) {
				jAlert("����д��ȷ�ֻ�����");
			} else if (data == 6) {
				jAlert("��ַ����20�����޷�������µ�ַ");
			} else if (data == 7) {
				jAlert("ϵͳ��æ�����Ժ�����");
			} else if (data == 8) {
				jAlert("ϵͳ��æ�����Ժ�����");
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
				jAlert("����д�ջ���������������6����");
			} else if (data == 3) {
				jAlert("��ѡ���ջ�����");
			} else if (data == 4) {
				jAlert("����д��ȷ��ϸ��ַ");
			} else if (data == 5) {
				jAlert("����д��ȷ�ֻ�����");
			} else if (data == 6) {
				jAlert("ϵͳ��æ�����Ժ�����");
			} else if (data == 7) {
				jAlert("ϵͳ��æ�����Ժ�����");
			}
		}
	});
}

function addAddress(){
	$.ajax({ url: "http://my.m.111.com.cn/ucenter/isLastAddress.action", type: "POST", cache: false, 
	 	success: function(data) {  
	 		if(data == 1){
	 			jAlert("��ַ����20�����޷�������µ�ַ");
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