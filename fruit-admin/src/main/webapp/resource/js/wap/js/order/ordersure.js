/* ���ﳵ��Cookie�� */
var cookiekey = 'cartItem';

/**
 * ��ʼ������ȷ��ҳ��
 */
function initOrderSurePage(){
	var code = $('#usedVoucherCode').val();
	computeVoucher(code);
	initInvoiceArea();
}

/**
 * �л�ʡ��ʱ��֤
 */
function checkProvince(){
	if($('#provinceId').val() != $('#siteId').val()){
		jConfirm('������ַ���ջ�ʡ��'+ $('#siteName').val() +'��һ�£��޷��µ����л�ʡ�ݣ���ʹ�����ﳵ�еĲ�����Ʒ�۸�仯���߲��ܹ���',
				 '�޷��޸ĵ�ַ', function(yes){
			if(yes){
				if(isEmpty($('#editAId').val())){
					$('#provinceId').val($('#siteId').val());
				}else{
					$('#provinceId').val($('#oldProvinceId').val());
				}
				getAllCitys();
			}else{
				var provinceName = $("#provinceId").find("option:selected").text();
				$.cookie('provinceId', $('#provinceId').val(), {path: '/', domain: '.111.com.cn', expires: 15});
				$.cookie('provinceName', provinceName, {path: '/', domain: '.111.com.cn', expires: 15});
				window.location.href = 'http://buy.m.111.com.cn/cart/shoppingcart/queryshoppingcart.action';
				return;
			}
		}, '�޸ĵ�ַ', '����ʡ��');
	}
}

/**
 * �õ����е���
 */
function getAllCitys(){
	var provinceId = $("#provinceId").val();
	if(provinceId != 0){
		$.ajax({
			url:"/interfaces/order/cityListAjax.action?provinceId="+provinceId,
			type:"POST",
			dataType:"text",
			success:function(data){
				if(data != ""){
					var cityhtml="";
					cityhtml+="<option value='0'>��ѡ����/��</option>";
					var json=eval('('+data+')');
					for(var i=0;i<json.length;i++){
						cityhtml+="<option value='"+json[i].cityId+"'>"+json[i].cityName+"</option>";
					}			    
				    $("#cityId").html(cityhtml);
				}else{
					$("#cityId").html("<option value='0'>��ѡ����/��</option>");
				}
		    }					
		});
	}else {				
		$("#cityId").html("<option value='0'>��ѡ����/��</option>");
	}
	$("#countyId").html("<option value='0'>��ѡ����/��</option>");
}

/**
 * �õ����е���
 */
function getAllCountys(){
	var cityId = $("#cityId").val();
	if(cityId != 0){
		$.ajax({
			url:"/interfaces/order/countyListAjax.action?cityId="+cityId,
			type:"POST",
			dataType:"text",
			success:function(data){
				if(data!=""){
				var countyhtml="";
				countyhtml+="<option value='0'>��ѡ����/��</option>";
					var json=eval('('+data+')');
					for(var i=0;i<json.length;i++){
						countyhtml+="<option value='"+json[i].countyId+"'>"+json[i].countyName+"</option>";
					}			    
				    $("#countyId").html(countyhtml);
				}else{
					$("#countyId").html("<option value='0'>��ѡ����/��</option>");
				}
			
		    }					
		});
	}else {				
		$("#countyId").html("<option value='0'>��ѡ����/��</option>");
	}
}

/**
 * ����ջ���ַ��Ϣ
 */
function checkAcceptAddress(){
	if(isEmpty($('#sendPeople').val())){
		jAlert('�ջ�����������Ϊ��');
		$('#sendPeople').val('');
		return false;
	}
	if(isEmpty($('#mobile').val())){
		jAlert('�ֻ����벻��Ϊ��');
		$('#mobile').val('');
		return false;
	}else{
		if(!checkMobile($('#mobile').val())){
			jAlert('�ֻ������ʽ����ȷ');
			return false;
		}
	}
	if($("#provinceId").val()==0 || $("#cityId").val()==0 || $("#countyId").val()==0){
		jAlert('��ѡ���ͻ�����');
		return false;
	}
	if(isEmpty($('#detailAddress').val())){
		jAlert('�ջ���ַ����Ϊ��');
		$('#detailAddress').val('');
		return false;
	}else{
		if($('#detailAddress').val().length < 5){
			jAlert('�ջ���ַ������5������');
			return false;
		}
	}
	return true;
}

/**
 * �����ջ���ַ
 * �л���ַʱ������µ�ַʱ��
 */
function saveAcceptAddress(id, selectedAId){
	if(!checkAcceptAddress()){
		return;
	}
	if(!id || id == "") {
		id = 0;
	}
	var sendPeople = $("#sendPeople").val();
	var send_ContactMobile = $("#mobile").val();
	var send_Province = $("#provinceId").val();
	var send_ProvinceName = $("#provinceId").find("option:selected").text();
	var send_City = $("#cityId").val();
	var send_CityName = $("#cityId").find("option:selected").text();
	var send_County = $("#countyId").val();
	var send_CountyName = $("#countyId").find("option:selected").text();
	var send_Address = $("#detailAddress").val();
	var isSetDefault = 0; 
	if($("#defaultAddress").prop("checked"))
		isSetDefault = 1;
	$.ajax({
		url: "/front/order/saveAcceptAddress.action",
		type: "POST",
		dataType: "text",
		data: "realName="+encodeURIComponent(encodeURIComponent(sendPeople))
			 +"&province="+send_Province
			 +"&city="+send_City
			 +"&county="+send_County
			 +"&provinceName="+encodeURIComponent(encodeURIComponent(send_ProvinceName))
			 +"&cityName="+encodeURIComponent(encodeURIComponent(send_CityName))
			 +"&countyName="+encodeURIComponent(encodeURIComponent(send_CountyName))
			 +"&address="+encodeURIComponent(encodeURIComponent(send_Address))
			 +"&mobile="+send_ContactMobile
			 +"&isDefault="+isSetDefault
			 +"&id="+id
			 +"&cookiekey="+cookiekey
			 +"&saveType=1",
		success:function(data){
			if(data == "isexit"){
				jAlert("�Ѵ�����ͬ�ջ���ַ");
				return;
			}else if(data == "limitCount"){
				jAlert("�ܱ�Ǹ�����ĳ��õ�ַ����20���޷�����");
				return;
			}else if(data == "ERROR"){
				jAlert("��Ϣ����ʧ��");
				return;
			}else{
				var obj=eval('('+data+')');
				if(id == 0){
					window.location.href = '/interfaces/wap/orderSure.action?selectedAId='+obj.selectId;
				}else{
					window.location.href = '/interfaces/wap/initSelectAddress.action?selectedAId='+selectedAId;
				}
			}
	    }					
	}); 
}

/**
 * �༭��ַ
 */
function editAddress(selectedAId, id){
	window.location.href = "/interfaces/wap/initAddAddress.action?selectedAId="+selectedAId+"&editAId="+id;
}

/**
 * ɾ���ջ���ַ
 */
function deleteAddress(id, selectedAId){
	if(isEmpty(id)){
		return;
	}
	jConfirm('ȷ��ɾ���ջ���ַ?', '', function(yes){
		if(yes){
			$.ajax({
				url: "/interfaces/wap/delAddress.action",
				type: "POST",
				dataType: "text",
				data: {editAId: id, cookiekey: cookiekey},
				success: function(data){
					var obj = eval('(' + data + ')');
					if(obj.notLogin != undefined){
						jAlert('���ȵ�¼!', '', function(){
							window.location.href = "http://passport.m.111.com.cn/sso/login.action";
						});
						return;
					}
					if(obj.error != undefined){
						jAlert('��������!');
						return;
					}
					if(obj.fail != undefined){
						jAlert('ϵͳ����!');
						return;
					}
					if(obj.success != undefined){
						jAlert('ɾ���ɹ�!', '', function(){
							if(id == selectedAId){
								window.location.href = '/interfaces/wap/initSelectAddress.action?selectedAId='+obj.sysSetAddrId;
							}else{
								window.location.href = '/interfaces/wap/initSelectAddress.action?selectedAId='+selectedAId;
							}							
						});
					}
				}
			});
		}
	});
}

/**
 * ѡ���ջ���ַ
 */
function selectAddress(jObj, addressId, provinceId, provinceName){
	var rs = true;
	if(provinceId != $('#siteId').val()){
		jConfirm('������ַ���ջ�ʡ��'+$('#siteName').val()+'��һ�£��޷��µ����л�ʡ�ݣ���ʹ�����ﳵ�еĲ�����Ʒ�۸�仯���߲��ܹ���',
			 '�޷��޸ĵ�ַ', function(yes){
			 	 if(!yes){
			 		 $.cookie('provinceId', provinceId, {path: '/', domain: '.111.com.cn', expires: 15});
			 		 $.cookie('provinceName', provinceName, {path: '/', domain: '.111.com.cn', expires: 15});
			 		 window.location.href = "http://buy.m.111.com.cn/cart/shoppingcart/queryshoppingcart.action";
			 		 return;
			 	 }
			 },'ȡ ��', '����ʡ��');
		rs = false;
	}
	if(rs){
		if(isEmpty(addressId)){
			jAlert("�ջ���ַ�б���س���");
			return;
		}
		$.ajax({
			url:"/front/order/saveAcceptAddress.action",
			type:"POST",
			dataType:"text",
			data:"id="+addressId+"&cookiekey="+cookiekey+"&saveType=2",
			success:function(data){
				if(data == "ERROR"){
					jAlert("��Ϣ����ʧ��");
					return;
				}else{
					var obj = eval('('+data+')');
					if(obj.emptycart != undefined){
						jAlert(obj.emptycart);
						window.location.href='http://buy.m.111.com.cn/cart/shoppingcart/queryshoppingcart.action';
						return;
					};
					if($(jObj).hasClass("selected")){
						return;
					}
					$("li").removeClass("selected");
					$(jObj).addClass("selected");
					window.location.href = "/interfaces/wap/orderSure.action";
/* 							if(obj.wrong!=undefined){
						$("#addressSaveTip").hide();
						$("#addressSaveButton").show();
						$("#saveAddressHref").show();
						alert(obj.wrong);
						return;
					};
					if(obj.isnotstock!=undefined){
						$("#addressSaveTip").hide();
						$("#addressSaveButton").show();
						$("#saveAddressHref").show();
						var notstock=obj.isnotstock;
						alert("������Ʒ�Ŀ���������㣺"+notstock.substring(0,notstock.length-1)+"�������Ը������͵�ַ��ͨ���޸Ĺ��ﳵȡ������Ʒ��");
						return;
					};
					if(obj.isnotArrive!=undefined){
						$("#addressSaveTip").hide();
						$("#addressSaveButton").show();
						$("#saveAddressHref").show();
						alert("�Բ���"+obj.isnotArrive+"�������͵���ѡ����ջ���ַ�������Ը������͵�ַ��ͨ���޸Ĺ��ﳵȡ������Ʒ��");
						return;
					};
					if(obj.theFei==undefined||obj.details==undefined){
						$("#addressSaveTip").hide();
						$("#addressSaveButton").show();
						$("#saveAddressHref").show();
						alert("ϵͳ��æ��");
						return;
					};
					if($("#ReceiptInfo").hasClass("newUser"))$("#ReceiptInfo").removeClass("newUser");
					if(obj.detailsInfo!=undefined){
						$("#detailsInfo").val(JSON.stringify(obj.detailsInfo));
					};
					var selectId=0;
					if(obj.selectId!=undefined)selectId=obj.selectId;
					if(obj.address!=undefined){
						converUserAddress(obj.address,selectId);
						//changeUserAddressSelect(obj.address,selectId);
					};
					if(obj.theallmoneyHidden!=undefined){
						$("#theallmoneyHidden").val(obj.theallmoneyHidden);
					}
					if(obj.notStockTcIds!=undefined){
						$("#notStockTcIds").val(obj.notStockTcIds);
					}
					if(obj.notStockItemIds!=undefined){
						$("#notStockItemIds").val(obj.notStockItemIds);
					}
					if(obj.allGoodsmoneyHidden!=undefined){
						$("#allGoodsmoneyHidden").val(obj.allGoodsmoneyHidden);
						$("#theItemAllMoney").html(obj.allGoodsmoneyHidden);
					}
					if(obj.promotionDiscount!=undefined){
						$("#promotionDiscount").val(obj.promotionDiscount);
					}
					selectUserAddressJs();
					if(obj.changedSite!=undefined){
						$("#changeStoreTipSpan").insertAfter($("#"+selectId+"_addressSpan"));
						$("#changeStoreTipSpan").show();
					}else{
						$("#changeStoreTipSpan").hide();
					}
					$("#theFei").val(obj.theFei);
					$("#orderTheFei").html(obj.theFei);
					$("#theFeiHidden").val(changeTwoDecimal_f(parseFloat(obj.theFei)));
					$("#theAllMoney").html(changeTwoDecimal_f(parseFloat($("#theallmoneyHidden").val())+parseFloat($("#theFeiHidden").val())
								-parseFloat($("#promotionDiscount").val())));
					converItemListInfo(obj.details);
					converUserPayTypeAndInvoiceInfo(obj);
					displayAreaUserCoupon(obj);
					closeEditAddress();
					Clear_Voucher();
					Clear_Balance();
					checksubmit(); */
				}
		    }
		});
	}
}

/**
 * Ĭ��ѡ��(����֧����ʽ���Ż�ȯҳ��)
 */
function defaultSelected(id){
	$('#'+id).addClass('cur').find('span').show();
}

/**
 * ѡ��֧����ʽ
 */
function selectPayMode(obj){
	$('.zf_box li').removeClass('cur').children('span').hide();
	$(obj).addClass('cur').find('span').show();
	window.location.href = '/interfaces/wap/orderSure.action?payMode='+$(obj).attr('id')+'&'+commonForwardParams('paymode');
}

/**
 * ѡ���Ż�ȯ
 */
function selectVoucher(obj){
    $('.yuj_box li').removeClass('cur').children('span').hide();
    $(obj).addClass('cur').find('span').show();
    $('#usedVoucherCode').val($(obj).attr('id'));
}

/**
 * ȡ��ѡ���Ż�ȯ
 */
function cancelVoucher(){
	var uvc = $('#usedVoucherCode').val();
	if(!isEmpty(uvc)){
		$('#'+uvc).removeClass('cur').find('span').hide();
		$('#usedVoucherCode').val('');
	}
}

/**
 * ���ѡ���Ż�ȯ
 */
function endSelectVoucher(){
	var uvc = $('#usedVoucherCode').val();
	var uvn = $('#uvn_'+uvc).text();
	var params = commonForwardParams('voucher');
	if(isEmpty(uvc)){
		window.location.href = '/interfaces/wap/orderSure.action?'+params;
	}else{
		window.location.href = '/interfaces/wap/orderSure.action?usedVoucherCode='+uvc+'&usedVoucherName='+encodeURI(encodeURI(uvn))+'&'+params;
	}
}

/**
 * �����Ż�ȯʹ�ü��㶩����ؽ��
 */
function computeVoucher(code){
	if(!isEmpty(code)){
		$.ajax({
			url:"/interfaces/order/findCouponByCode.action",
			type:"POST",
			dataType:"json",
			data:"couponCode="+code,
			success:function(data){
				if(data!=''){
					var obj = eval(data);
					$("#couponPrice").val(obj.price);
					var theAllMoney = parseFloat($("#theallmoneyHidden").val()) + parseFloat($("#theFei").val())
									- parseFloat($("#useAccountAmt").val()) - parseFloat($("#promotionDiscount").val());
					if (parseFloat(obj.price) > theAllMoney){ //����Ż�ȯ���>���������Ż�ȯʹ�ý��Ϊ�����������Ż�ȯ���
			        	$("#vouchermoneyHidden").val(theAllMoney); 
			        	$("#useCouponCodeHidden").val(code);
			        }else{
			            $("#vouchermoneyHidden").val(obj.price);
			            $("#useCouponCodeHidden").val(code);
			        }
				    //����ʹ�����ġ����߼������ѿۼ����Żݺ�Ľ��û����е�С��,min{ �������ܽ��-����-��Ʒ��-�Ż�ȯ�����û��˻���� }
					//�������-�Ż�ȯ���=�ѿۼ����Żݺ�Ľ��
					var voucherAmt = parseFloat($("#vouchermoneyHidden").val());
					if(voucherAmt > 0){
						$("#voucherAmtArea").show();
						$("#voucherAmt").html(formatMoneyAmt(voucherAmt));
					}
					var factmoney = parseFloat($("#theallmoneyHidden").val()) - voucherAmt  
								  + parseFloat($("#theFei").val()) - parseFloat($("#useAccountAmt").val()) - parseFloat($("#promotionDiscount").val());
					if(factmoney <= 0){
						$("#theAllMoney").html("0.00"); 
					}else{
						$("#theAllMoney").html(formatMoneyAmt(factmoney));
					}
				}
		    }					
		});
	}else{
		var factmoney = parseFloat($("#theallmoneyHidden").val()) + parseFloat($("#theFei").val()) 
					- parseFloat($("#useAccountAmt").val()) - parseFloat($("#promotionDiscount").val());
		if(factmoney <= 0){
			$("#theAllMoney").html("0.00"); 
		}else{
			$("#theAllMoney").html(formatMoneyAmt(factmoney));
		}
	}
}

/**
 * �Ƿ���Ҫ��Ʊ��ť�¼�
 */
function isNeedInvoice(){
	if($("#needInvoice").prop("checked")){
		$('#invoiceInfo').show();
		$('#invoiceCue').show();
	}else{
		$('#invoiceInfo').hide();
		$('#invoiceCue').hide();
	} 
}

/**
 * �л���Ʊ�����¼�
 */
function changeInvoiceType(){
	if($('#invoiceType').val() == '0'){
		$('#invoiceTitle').val('');
		$('#invoiceTitle').attr('disabled', true);
	}else{
		$('#invoiceTitle').attr('disabled', false);
	}
}

/**
 * ��ʼ��ҳ�淢Ʊ����
 */
function initInvoiceArea(){
	var isEnableDontInvoice = $('#isEnableDontInvoice').val();
	if(isEnableDontInvoice != 1){
		$('#invoiceInfo').show();
	}else{
		$("#needInvoice").prop("checked", false);
		var elem = document.querySelector('.js-switch');
		new Switchery(elem, { color: '#41b7f1' });
		defaults = {
		    color: '#999999', 
		    secondaryColor: '#dfdfdf', 
		    className: 'switchery', 
		    disabled: false, 
		    disabledOpacity: 0.5, 
		    speed: '0.1s'
		};				
	}
	$('#invoiceType').val('0');
	$('#invoiceTitle').val('');
	$('#invoiceTitle').attr('disabled', true);
	$('#invoiceContent').val('');
}

/**
 * ��ת��ѡ��֧����ʽҳ��
 */
function forwardSelectPayMode(isCOD, isPOS){
	window.location.href = "/interfaces/wap/changePayMode.action?isCOD="+isCOD+"&isPOS="+isPOS+"&payMode="+$('#payMode').val()
			+"&selectedAId="+$('#selectedAId').val()+"&usedVoucherCode="+$('#usedVoucherCode').val()
			+"&usedVoucherName="+encodeURI(encodeURI($('#usedVoucherName').val()));
}

/**
 * ��ת��ѡ���Ż�ȯҳ��
 */
function forwardSelectVoucher(){
	window.location.href = "/interfaces/wap/initVoucherPage.action?usedVoucherCode="+$('#usedVoucherCode').val()
			+"&selectedAId="+$('#selectedAId').val()+"&payMode="+$('#payMode').val();
}

/**
 * ��ת��ʹ���˻����ҳ��
 */
function forwardAccountAmtPay(){
	window.location.href = "/interfaces/wap/initAccountAmtPage.action?theAllMoney="+$('#theAllMoney').text()
			+"&useAccountAmt="+$('#useAccountAmt').val()+"&payMode="+$('#payMode').val()+"&selectedAId="+$('#selectedAId').val()
			+"&usedVoucherCode="+$('#usedVoucherCode').val()+"&usedVoucherName="+encodeURI(encodeURI($('#usedVoucherName').val()));
}

/**
 * �޸Ķ�����ʹ�õ����
 */
function modifyAccountAmt(){
	$('#useAmtArea').hide();
	$('#inputAmtArea').show();
	$('#saveBtn').show();
}

/**
 * �����޸ĵ�ʹ�����
 */
function saveAccountAmt(){
	if(checkAccountAmt()){
		$('#useAmtArea').show();
		$('#inputAmtArea').hide();
		$('#saveBtn').hide();
		$('#useAmt').text($('#inputAmt').val());
	}
}

/**
 * �����ʹ�õ���� 
 */
function checkAccountAmt(){
	var useAmt = parseFloat($('#inputAmt').val());
	var accountAmt = parseFloat($('#customerAccount').val());
	var orderAmt = parseFloat($('#theAllMoney').val());
	if(useAmt > accountAmt){
		jAlert('ʹ�ý��ܳ����������'); 
		return false;
	}
	if(useAmt > orderAmt){
		jAlert('ʹ�ý��ܳ�������Ӧ�����');
		return false;
	}
	return true;
}

/**
 * ת�򶩵�ȷ��ҳ��
 */
function forwardOrderFromAccAmt(){
	window.location.href = "/interfaces/wap/orderSure.action?useAccountAmt="+$('#useAmt').text() 
			+"&payMode="+$('#payMode').val()+"&selectedAId="+$('#selectedAId').val()
			+"&usedVoucherCode="+$('#usedVoucherCode').val()+"&usedVoucherName="+encodeURI(encodeURI($('#usedVoucherName').val()));
}

/**
 * ������ҳ����ת��ȷ�϶���ҳ��ͨ�ò���
 */
function commonForwardParams(type){
	var params = '';
	if(type == 'paymode'){
		params += 'usedVoucherCode='+$('#usedVoucherCode').val()+'&usedVoucherName='+encodeURI(encodeURI($('#usedVoucherName').val()))
		+"&selectedAId="+$('#selectedAId').val() ;
	}else if(type == 'voucher'){
		params += 'payMode='+$('#payMode').val()+"&selectedAId="+$('#selectedAId').val();
	}
	return params;
}

/**
 * ��鲢���淢Ʊ��Ϣ
 */
function checkSaveInvoiceInfo(){
	var isEnableDontInvoice = $('#isEnableDontInvoice').val();
	if(isEnableDontInvoice == 1){
		var isNeedInvoice = $("#needInvoice").prop("checked");
		if(isNeedInvoice){
			return saveInvoice();
		}else{
			$.ajax({
				url: "/front/order/saveNotInvoice.action",
				type: "POST",
				async: false,
				dataType: "text",
				data: "id=" + $("#selectedAId").val(),
				success:function(data){
					if(data == '1'){
						$("#invoiceTypeHidden").val("3");
					}
			    }					
			});
			return true;
		}
	}else{
		return saveInvoice();
	}
}

/**
 * ���淢Ʊ��Ϣ
 */
function saveInvoice(){
	var invoiceType = $('#invoiceType').val();  //��Ʊ����(0:����,1:��˾)
	var invoiceHeader = $('#invoiceTitle').val();
	if(invoiceType == '1'){
		if(isEmpty(invoiceHeader)){
			jAlert('�����뷢Ʊ̧ͷ');
			return false;
		}
	}else{
		invoiceHeader = '����';
	}
	var invoiceContent = $('#invoiceContent').val();
	if(isEmpty(invoiceContent)){
		jAlert('��ѡ��Ʊ����');
		return false;
	}
	$.ajax({
		url: "/front/order/savePtInvoice.action",
		type: "POST",
		async: false,
		dataType: "text",
		data: "invoiceHead=" + encodeURIComponent(encodeURIComponent(invoiceHeader))
			+"&invoiceHeadTypeId=" + invoiceType 
			+ "&invoiceTypeId=" + invoiceType
			+"&invoiceConent=" + encodeURIComponent(encodeURIComponent(invoiceContent))
			+"&addressId=" + $("#selectedAId").val(),
		success:function(data){
			if(data != '1'){
				jAlert("��Ʊ��������");
				return false;
			}
			$("#invoiceTypeHidden").val(invoiceType);
	    }					
	});
	return true;
}

/**
 * �ύ����
 */
function submitOrder(){
	var selectedAId = $('#selectedAId').val();
	if(isEmpty(selectedAId)){
		jAlert('��ѡ���ջ���ַ');
		return;
	}
	if(checkSaveInvoiceInfo()){
		var allGoodsAmt = $("#theallmoneyHidden").val();
		var theFei = $("#theFei").val();
		var payTypeIdHidden = $('#payTypeIdHidden').val();
		var invoiceTypeHidden = $('#invoiceTypeHidden').val();
		var par = "allGoodsmoneyHidden="+allGoodsAmt+"&theFeiHidden="+theFei+"&selectId="+selectedAId+"&payTypeHidden="+payTypeIdHidden
				+"&payBankCodeHidden=500&invoiceHidden="+invoiceTypeHidden;
		var usedVoucherCode = $('#usedVoucherCode').val();
		if(!isEmpty(usedVoucherCode)){
			par += "&useCouponCodeHidden="+usedVoucherCode+"&couponCode="+usedVoucherCode;
		}
		var useAccountAmt = $('#useAccountAmt').val();
		if(parseFloat(useAccountAmt)>0){
			par += "&changeScore="+useAccountAmt;
		}
		par += "&isWapOrder=1&aa="+Math.random();
		$("#orderSubmitBtn").hide();		
		$.ajax({
			url:"/interfaces/wap/saveOrder.action",
			type:"POST",
			dataType:"text",
			data:par,
			success:function(data){
				var obj = eval('('+data+')');
				if(obj.flag=='1'){
					if(obj.msg=='onlyok'){
						window.location.href='/interfaces/wap/orderSuccess.action?orderId='+obj.orderid;
						return;
					}
				}
				if(obj.flag=='0'){
					$("#orderSubmitBtn").show();
					if(obj.type=='0'){
						jAlert(obj.msg);
						return;
					}
					if(obj.type=='1'){    //û�е�¼
						jAlert('����δ��¼','', function(){
							window.location.href = "http://passport.m.111.com.cn/sso/login.action?ReturnUrl=http://buy.m.111.com.cn/interfaces/wap/orderSure.action";
							return;
						});
					}
					if(obj.type=='2'){    //72Сʱ
						var msg = obj.msg;
						jAlert("������Ʒ:"+msg+"��72Сʱ�ڹ������������޹������޷��µ��������½⣡");
						return;
					}
					if(obj.type=='3'){    //������
						jAlert("�����˻������쳣�޷��µ�������ϵ�ͷ�!");
						return;
					}
					if(obj.type=='4'){    //�˷ѷ����仯
						$("#theFei").val(obj.msg);
						return;
					}
					if(obj.type=='5'){
						jAlert("�Բ���"+obj.msg+"�������͵���ѡ����ջ���ַ�������Ը������͵�ַ��ͨ���޸Ĺ��ﳵȡ������Ʒ��");
						return;
					}
					if(obj.type=='6'){
						jAlert(obj.msg, '', function(){
							window.location.href = 'http://buy.m.111.com.cn/cart/shoppingcart/queryshoppingcart.action';
							return;
						});
					}
					if(obj.type=='7'){
						jAlert(obj.msg);
						return;
					}
				}
		    }					
		});
	} 
}

/**
 * ת��֧����֧��ҳ��
 */
function forwardAlipay(orderId,bankCode){
	window.location.href = "http://buy.m.111.com.cn/payment/confirmWap.action?id="+orderId+"bankCode="+bankCode;
}

/**
 * �ص���ҳ
 */
function returnHomePage(){
	window.location.href = "http://m.111.com.cn";
}
