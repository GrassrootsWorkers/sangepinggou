/* 购物车中Cookie键 */
var cookiekey = 'cartItem';

/**
 * 初始化订单确认页面
 */
function initOrderSurePage(){
	var code = $('#usedVoucherCode').val();
	computeVoucher(code);
	initInvoiceArea();
}

/**
 * 切换省份时验证
 */
function checkProvince(){
	if($('#provinceId').val() != $('#siteId').val()){
		jConfirm('订单地址与收货省份'+ $('#siteName').val() +'不一致，无法下单；切换省份，会使您购物车中的部分商品价格变化或者不能购买。',
				 '无法修改地址', function(yes){
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
		}, '修改地址', '更改省份');
	}
}

/**
 * 得到所有的市
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
					cityhtml+="<option value='0'>请选择市/区</option>";
					var json=eval('('+data+')');
					for(var i=0;i<json.length;i++){
						cityhtml+="<option value='"+json[i].cityId+"'>"+json[i].cityName+"</option>";
					}			    
				    $("#cityId").html(cityhtml);
				}else{
					$("#cityId").html("<option value='0'>请选择市/区</option>");
				}
		    }					
		});
	}else {				
		$("#cityId").html("<option value='0'>请选择市/区</option>");
	}
	$("#countyId").html("<option value='0'>请选择区/县</option>");
}

/**
 * 得到所有的县
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
				countyhtml+="<option value='0'>请选择区/县</option>";
					var json=eval('('+data+')');
					for(var i=0;i<json.length;i++){
						countyhtml+="<option value='"+json[i].countyId+"'>"+json[i].countyName+"</option>";
					}			    
				    $("#countyId").html(countyhtml);
				}else{
					$("#countyId").html("<option value='0'>请选择区/县</option>");
				}
			
		    }					
		});
	}else {				
		$("#countyId").html("<option value='0'>请选择区/县</option>");
	}
}

/**
 * 检查收货地址信息
 */
function checkAcceptAddress(){
	if(isEmpty($('#sendPeople').val())){
		jAlert('收货人姓名不能为空');
		$('#sendPeople').val('');
		return false;
	}
	if(isEmpty($('#mobile').val())){
		jAlert('手机号码不能为空');
		$('#mobile').val('');
		return false;
	}else{
		if(!checkMobile($('#mobile').val())){
			jAlert('手机号码格式不正确');
			return false;
		}
	}
	if($("#provinceId").val()==0 || $("#cityId").val()==0 || $("#countyId").val()==0){
		jAlert('请选择送货区域');
		return false;
	}
	if(isEmpty($('#detailAddress').val())){
		jAlert('收货地址不能为空');
		$('#detailAddress').val('');
		return false;
	}else{
		if($('#detailAddress').val().length < 5){
			jAlert('收货地址不少于5个汉字');
			return false;
		}
	}
	return true;
}

/**
 * 保存收货地址
 * 切换地址时，需更新地址时间
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
				jAlert("已存在相同收货地址");
				return;
			}else if(data == "limitCount"){
				jAlert("很抱歉，您的常用地址已满20个无法保存");
				return;
			}else if(data == "ERROR"){
				jAlert("信息保存失败");
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
 * 编辑地址
 */
function editAddress(selectedAId, id){
	window.location.href = "/interfaces/wap/initAddAddress.action?selectedAId="+selectedAId+"&editAId="+id;
}

/**
 * 删除收货地址
 */
function deleteAddress(id, selectedAId){
	if(isEmpty(id)){
		return;
	}
	jConfirm('确认删除收货地址?', '', function(yes){
		if(yes){
			$.ajax({
				url: "/interfaces/wap/delAddress.action",
				type: "POST",
				dataType: "text",
				data: {editAId: id, cookiekey: cookiekey},
				success: function(data){
					var obj = eval('(' + data + ')');
					if(obj.notLogin != undefined){
						jAlert('请先登录!', '', function(){
							window.location.href = "http://passport.m.111.com.cn/sso/login.action";
						});
						return;
					}
					if(obj.error != undefined){
						jAlert('参数错误!');
						return;
					}
					if(obj.fail != undefined){
						jAlert('系统错误!');
						return;
					}
					if(obj.success != undefined){
						jAlert('删除成功!', '', function(){
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
 * 选择收货地址
 */
function selectAddress(jObj, addressId, provinceId, provinceName){
	var rs = true;
	if(provinceId != $('#siteId').val()){
		jConfirm('订单地址与收货省份'+$('#siteName').val()+'不一致，无法下单；切换省份，会使您购物车中的部分商品价格变化或者不能购买。',
			 '无法修改地址', function(yes){
			 	 if(!yes){
			 		 $.cookie('provinceId', provinceId, {path: '/', domain: '.111.com.cn', expires: 15});
			 		 $.cookie('provinceName', provinceName, {path: '/', domain: '.111.com.cn', expires: 15});
			 		 window.location.href = "http://buy.m.111.com.cn/cart/shoppingcart/queryshoppingcart.action";
			 		 return;
			 	 }
			 },'取 消', '更改省份');
		rs = false;
	}
	if(rs){
		if(isEmpty(addressId)){
			jAlert("收货地址列表加载出错");
			return;
		}
		$.ajax({
			url:"/front/order/saveAcceptAddress.action",
			type:"POST",
			dataType:"text",
			data:"id="+addressId+"&cookiekey="+cookiekey+"&saveType=2",
			success:function(data){
				if(data == "ERROR"){
					jAlert("信息保存失败");
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
						alert("以下商品的库存数量不足："+notstock.substring(0,notstock.length-1)+"。您可以更改配送地址或通过修改购物车取消该商品。");
						return;
					};
					if(obj.isnotArrive!=undefined){
						$("#addressSaveTip").hide();
						$("#addressSaveButton").show();
						$("#saveAddressHref").show();
						alert("对不起，"+obj.isnotArrive+"不能配送到您选择的收货地址。您可以更改配送地址或通过修改购物车取消该商品。");
						return;
					};
					if(obj.theFei==undefined||obj.details==undefined){
						$("#addressSaveTip").hide();
						$("#addressSaveButton").show();
						$("#saveAddressHref").show();
						alert("系统繁忙！");
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
 * 默认选中(用于支付方式、优惠券页面)
 */
function defaultSelected(id){
	$('#'+id).addClass('cur').find('span').show();
}

/**
 * 选择支付方式
 */
function selectPayMode(obj){
	$('.zf_box li').removeClass('cur').children('span').hide();
	$(obj).addClass('cur').find('span').show();
	window.location.href = '/interfaces/wap/orderSure.action?payMode='+$(obj).attr('id')+'&'+commonForwardParams('paymode');
}

/**
 * 选择优惠券
 */
function selectVoucher(obj){
    $('.yuj_box li').removeClass('cur').children('span').hide();
    $(obj).addClass('cur').find('span').show();
    $('#usedVoucherCode').val($(obj).attr('id'));
}

/**
 * 取消选择优惠券
 */
function cancelVoucher(){
	var uvc = $('#usedVoucherCode').val();
	if(!isEmpty(uvc)){
		$('#'+uvc).removeClass('cur').find('span').hide();
		$('#usedVoucherCode').val('');
	}
}

/**
 * 完成选择优惠券
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
 * 根据优惠券使用计算订单相关金额
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
					if (parseFloat(obj.price) > theAllMoney){ //如果优惠券金额>订单金额，则优惠券使用金额为订单金额，而非优惠券面额
			        	$("#vouchermoneyHidden").val(theAllMoney); 
			        	$("#useCouponCodeHidden").val(code);
			        }else{
			            $("#vouchermoneyHidden").val(obj.price);
			            $("#useCouponCodeHidden").val(code);
			        }
				    //本次使用余额的“金额”逻辑：（已扣减过优惠后的金额，用户余额）中的小者,min{ （订单总金额-返现-礼品卡-优化券），用户账户余额 }
					//订单金额-优惠券金额=已扣减过优惠后的金额
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
 * 是否需要发票按钮事件
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
 * 切换发票类型事件
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
 * 初始化页面发票区域
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
 * 跳转到选择支付方式页面
 */
function forwardSelectPayMode(isCOD, isPOS){
	window.location.href = "/interfaces/wap/changePayMode.action?isCOD="+isCOD+"&isPOS="+isPOS+"&payMode="+$('#payMode').val()
			+"&selectedAId="+$('#selectedAId').val()+"&usedVoucherCode="+$('#usedVoucherCode').val()
			+"&usedVoucherName="+encodeURI(encodeURI($('#usedVoucherName').val()));
}

/**
 * 跳转到选择优惠券页面
 */
function forwardSelectVoucher(){
	window.location.href = "/interfaces/wap/initVoucherPage.action?usedVoucherCode="+$('#usedVoucherCode').val()
			+"&selectedAId="+$('#selectedAId').val()+"&payMode="+$('#payMode').val();
}

/**
 * 跳转到使用账户余额页面
 */
function forwardAccountAmtPay(){
	window.location.href = "/interfaces/wap/initAccountAmtPage.action?theAllMoney="+$('#theAllMoney').text()
			+"&useAccountAmt="+$('#useAccountAmt').val()+"&payMode="+$('#payMode').val()+"&selectedAId="+$('#selectedAId').val()
			+"&usedVoucherCode="+$('#usedVoucherCode').val()+"&usedVoucherName="+encodeURI(encodeURI($('#usedVoucherName').val()));
}

/**
 * 修改订单所使用的余额
 */
function modifyAccountAmt(){
	$('#useAmtArea').hide();
	$('#inputAmtArea').show();
	$('#saveBtn').show();
}

/**
 * 保存修改的使用余额
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
 * 检查所使用的余额 
 */
function checkAccountAmt(){
	var useAmt = parseFloat($('#inputAmt').val());
	var accountAmt = parseFloat($('#customerAccount').val());
	var orderAmt = parseFloat($('#theAllMoney').val());
	if(useAmt > accountAmt){
		jAlert('使用金额不能超过可用余额'); 
		return false;
	}
	if(useAmt > orderAmt){
		jAlert('使用金额不能超过订单应付金额');
		return false;
	}
	return true;
}

/**
 * 转向订单确认页面
 */
function forwardOrderFromAccAmt(){
	window.location.href = "/interfaces/wap/orderSure.action?useAccountAmt="+$('#useAmt').text() 
			+"&payMode="+$('#payMode').val()+"&selectedAId="+$('#selectedAId').val()
			+"&usedVoucherCode="+$('#usedVoucherCode').val()+"&usedVoucherName="+encodeURI(encodeURI($('#usedVoucherName').val()));
}

/**
 * 从其他页面跳转到确认订单页面通用参数
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
 * 检查并保存发票信息
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
 * 保存发票信息
 */
function saveInvoice(){
	var invoiceType = $('#invoiceType').val();  //发票类型(0:个人,1:公司)
	var invoiceHeader = $('#invoiceTitle').val();
	if(invoiceType == '1'){
		if(isEmpty(invoiceHeader)){
			jAlert('请输入发票抬头');
			return false;
		}
	}else{
		invoiceHeader = '个人';
	}
	var invoiceContent = $('#invoiceContent').val();
	if(isEmpty(invoiceContent)){
		jAlert('请选择发票内容');
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
				jAlert("发票参数错误");
				return false;
			}
			$("#invoiceTypeHidden").val(invoiceType);
	    }					
	});
	return true;
}

/**
 * 提交订单
 */
function submitOrder(){
	var selectedAId = $('#selectedAId').val();
	if(isEmpty(selectedAId)){
		jAlert('请选择收货地址');
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
					if(obj.type=='1'){    //没有登录
						jAlert('您还未登录','', function(){
							window.location.href = "http://passport.m.111.com.cn/sso/login.action?ReturnUrl=http://buy.m.111.com.cn/interfaces/wap/orderSure.action";
							return;
						});
					}
					if(obj.type=='2'){    //72小时
						var msg = obj.msg;
						jAlert("您的商品:"+msg+"在72小时内购买数量超过限购数量无法下单，敬请谅解！");
						return;
					}
					if(obj.type=='3'){    //黑名单
						jAlert("您的账户存在异常无法下单，请联系客服!");
						return;
					}
					if(obj.type=='4'){    //运费发生变化
						$("#theFei").val(obj.msg);
						return;
					}
					if(obj.type=='5'){
						jAlert("对不起，"+obj.msg+"不能配送到您选择的收货地址。您可以更改配送地址或通过修改购物车取消该商品。");
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
 * 转向支付宝支付页面
 */
function forwardAlipay(orderId,bankCode){
	window.location.href = "http://buy.m.111.com.cn/payment/confirmWap.action?id="+orderId+"bankCode="+bankCode;
}

/**
 * 回到首页
 */
function returnHomePage(){
	window.location.href = "http://m.111.com.cn";
}
