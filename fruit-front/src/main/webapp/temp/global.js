var g_loction = {
	domain: ".sangepg.com",
	pcDomain: "www.sangepg.com",
	mobileDomain: "m.sangepg.com",
	domainName: "sangepg.com"
}



function imgError(obj, pic) {
	obj.src = 'http://p.sangepg.com/images/' + pic;
	obj.onerror = null;
}

/**
 * 手机号码格式验证
 */
function checkMobile(no) {
	var re = /17\d{9}|13\d{9}|15\d{9}|18\d{9}|14\d{9}/;
	if (!re.test(no) || no.length != 11) {
		return false;
	}
	return true;
}

/**
 * 阻止冒泡事件
 */
function cancelBubble(e) {
	if (e.stopPropagation) {
		e.stopPropagation();
	} else {
		e.cancelBubble = true;
	}
}

/**
 * 格式化金额(保留两位小数)
 */
function formatMoneyAmt(x) {
	var f_x = parseFloat(x);
	if (isNaN(f_x)) {
		return false;
	}
	f_x = Math.round(f_x * 100) / 100;
	var s_x = f_x.toString();
	var pos_decimal = s_x.indexOf('.');
	if (pos_decimal < 0) {
		pos_decimal = s_x.length;
		s_x += '.';
	}
	while (s_x.length <= pos_decimal + 2) {
		s_x += '0';
	}
	return s_x;
}
/**
 * 格式化金额(保留一位小数)
 */
function formatMoney(x) {
	var f_x = parseFloat(x);
	if (isNaN(f_x)) {
		return false;
	}
	f_x = Math.round(f_x * 10) / 10;
	var s_x = f_x.toString();
	var pos_decimal = s_x.indexOf('.');
	if (pos_decimal < 0) {
		pos_decimal = s_x.length;
		s_x += '.';
	}
	while (s_x.length <= pos_decimal + 1) {
		s_x += '0';
	}
	return s_x;
}

function strToJson(str) {
	var toStr = str.toString();
	var json = eval('(' + toStr + ')');
	return json;
}

function flyToCart(fromDom, destinationId) {
	if (fromDom == null) {
		$(".goods-cart i").text(countCart());
		return;
	}
	var flyElm = fromDom.clone().css('opacity', '0.7').addClass("clone");
	flyElm.css({
		'z-index': 99999,
		'display': 'block',
		'position': 'absolute',
		'top': fromDom.offset().top + 'px',
		'left': fromDom.offset().left + 'px',
		'width': fromDom.width() + 'px',
		'height': fromDom.height() + 'px'
	});
	$('body').append(flyElm);
	flyElm.animate({
		top: $('#' + destinationId).offset().top,
		left: $('#' + destinationId).offset().left,
		width: 30,
		height: 30,
	}, 800, function() {
		$(".clone").remove();
		$("#" + destinationId).find("i").text(countCart());
		$(".btm_cart i").text(countCart());
	});
}
$(".p_top").click(function(e) {
	var open = $(".talk-bottom > ul");
	if (open.length > 0) {
		$(".talk_link").removeClass('btn_link_dis');
		$('.talk_ul').removeClass('open');
		$('.talk_bg').hide();
	}
	e.preventDefault();
	$(document.documentElement).animate({
		scrollTop: 0
	}, 200);
	$(document.body).animate({
		scrollTop: 0
	}, 200);
});
$(function() {
	var topBtn = $('.p_top'),
		topSearch = $('#jkc_header');
	if (topBtn.length > 0) {
		topBtn.hide();
		$(window).scroll(function() {
			if ($(this).scrollTop() > 100) {
				topBtn.fadeIn();
			} else {
				topBtn.fadeOut();
			}
		});
	}
	if (topSearch.length>0) {
		$(window).scroll(function() {
			if ($(this).scrollTop() > 50) {
				topSearch.addClass("fixd_header");
			} else {
				topSearch.removeClass("fixd_header");
			}
		});
	}
	if ($(".g-loadmodal").length > 0) {
		$(".g-loadmodal").fadeOut(1500, function() {
			$(".g-loadmodal").remove();
		});
	}
	$(".navi_btn a").click(function() {
		$(this).parent().next().slideToggle();
		return false;
	});
	$("body").click(function() {
		if($(".navi_box").length>0){
			$(".navi_box").slideUp();
		}
	});
	$("body").on('click', '.g-toggle', function(event) {
		event.preventDefault();
		var me = $(this);
		me.toggleClass('open');
		$(".toggle-nav").toggleClass('open');
	});
});

function getMailAddress(mailTail) {
	var address = "";
	$.ajax({
		url: 'http://s.sangepg.com/js/wap/json/mail.json',
		type: 'GET',
		dataType: 'json',
		async: false,
		success: function(data) {
			$.each(data, function(Index, info) {
				if (info["tail"] == mailTail) {
					address = info["value"];
					return false;
				}
			});
		}
	});
	$.ajaxSettings.async = false;
	return address;
}

function goHome() {
	var sourceUrl = $.cookie("sourceUrl");
	if (sourceUrl == null || sourceUrl == "") {
		window.location.href = "http://m"+g_loction.domain;
	} else {
		$.cookie('sourceUrl', null, {
			path: '/',
			domain: g_loction.domain
		});
		window.location.href = sourceUrl;
	}
}
String.prototype.startWith = function(str) {
	if (str == null || str == "" || this.length == 0 || str.length > this.length)
		return false;
	if (this.substr(0, str.length) == str)
		return true;
	else
		return false;
};
String.prototype.endWith = function(str) {
	if (str == null || str == "" || this.length == 0 || str.length > this.length)
		return false;
	if (this.substring(this.length - str.length) == str)
		return true;
	else
		return false;
};
/**
 * 验证图片验证码
 * @param obj
 * @param baseUrl
 * @param userName
 * @param picCode
 */
function validatePicCode(obj, baseUrl, userName, picCode) {
	$.ajax({
		url: "http://" + baseUrl + "/validate/validatePictureCode.action?userName=" + userName + "&picCode=" + picCode,
		type: 'post',
		dataType: 'text',
		async: false,
		success: function(data) {
			if (data == 'success') {
				obj.addClass("input_ok");
				obj.removeClass("input_error");
			} else {
				obj.addClass("input_error");
				obj.removeClass("input_ok");
			}
		}
	});
}
/**
 * 验证手机验证码
 * @param obj
 * @param baseUrl
 * @param userName
 * @param picCode
 */
function validatePhoneCode(obj, baseUrl, phone, phoneCode) {
	$.ajax({
		url: "http://" + baseUrl + "/validate/validatePhoneCode.action?phone=" + phone + "&phoneCode=" + phoneCode,
		type: 'post',
		dataType: 'text',
		async: false,
		success: function(data) {
			if (data == 'success') {
				obj.addClass("input_ok");
				obj.removeClass("input_error");
			} else if (data == "-1" || data == -1) {
				showTips("验证码错误超过三次，请重新获取手机验证码！");
			} else {
				obj.addClass("input_error");
				obj.removeClass("input_ok");
			}
		}
	});
}

/**
 * 验证手机验证码
 * @param obj
 * @param baseUrl
 * @param userName
 * @param picCode
 */
function validatePhoneCodeById(obj, baseUrl, userId, phoneCode) {
	$.ajax({
		url: "http://" + baseUrl + "/validate/validatePhoneCodeById.action?userId=" + userId + "&phoneCode=" + phoneCode,
		type: 'post',
		dataType: 'text',
		async: false,
		success: function(data) {
			if (data == 'success') {
				obj.addClass("input_ok");
				obj.removeClass("input_error");
			} else if (data == "-1" || data == -1) {
				showTips("验证码错误超过三次，请重新获取手机验证码！");
			} else {
				obj.addClass("input_error");
				obj.removeClass("input_ok");
			}
		}
	});
}

/**
 * 判断字符串是否为空
 */
function isEmpty(str) {
	if (null == str) {
		return true;
	} else {
		str = str.toString();
		if (str.replace(/[ ]/g, "").length == 0) {
			return true;
		} else {
			return false;
		}
	}
};

function search() {
	var keyword = $("#keyword").val();
	if (keyword != null && keyword != "") {
		saveSearchHistory(keyword);
		$("#wapSearchForm").attr("action", "http://m" + g_loction.domain + "/retrieve/search.action?from_type=1&keyword=" + encodeURI(encodeURI(keyword)));
	} else {
		return false;
	}
}

function mainSearch(a) {
	var keyword = $(a).find("input").val();
	if (keyword != null && keyword != "") {
		saveSearchHistory(keyword);
		$(a).attr("action", "http://m"+g_loction.domain+"/retrieve/search.action?keyword=" + encodeURI(encodeURI(keyword)));
	} else {
		return false;
	}
}

function saveSearchHistory(keyword) {
	keyword = keyword.trim();
	var result = "";
	var history = $.cookie("keyWordsHistory");
	if (history && history != "") {
		if (history != keyword && !history.startWith(keyword + "|") && history.indexOf("|" + keyword + "|") == -1 && !history.endWith("|" + keyword)) {
			result = keyword + "|" + history;
		} else {
			result = history;
		}
		if (result.split("|").length >= 10) {
			result = result.substring(0, result.lastIndexOf("|"));
		}
	} else {
		result = keyword;
	}
	$.cookie("keyWordsHistory", result, {
		expires: 30,
		path: "/",
		domain: g_loction.domain
	});
}
//显示提示
var g_tips = false;

function showTips(text,cb) {
	if (g_tips) {
		window.clearTimeout(g_tips);
		$(".showtips").remove();
		g_tips = false;
	}
	var newTips = $("<div>");
	$("body").append(newTips);
	newTips.addClass('showtips');
	newTips.text(text);
	window.setTimeout(function() {
		newTips.addClass('active');
	}, 10);
	g_tips = window.setTimeout(function() {
		$(".showtips").remove();
		if(cb){
			cb();
		}
	}, 2100);
}
//获取地址栏参数
function getUrlParam(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null) return unescape(r[2]);
	return null;
}
//获取字符串中的参数
function getStringParam(pString, name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = pString.match(reg);
	if (r != null) return unescape(r[2]);
	return null;
}
//返回系统
var historyUtils = {
	//向历史数组添加地址
	add: function(url, pathName) {
		var me = this;
		var historyArray = historyUtils.getLocal();
		if (!historyArray) {
			historyArray = [];
		}
		//读取cookie中的backurl
		if ($.cookie("history_back_Url")) {
			var backurl = $.cookie("history_back_Url") + "?isBackUrl=1";
			$.cookie("history_back_Url", "", {
				path: '/',
				domain: me.switchDomain(window.location.host),
				expires: "Session"
			});
			historyArray.push(backurl);
		}
		//读取地址栏的backurl
		if (getUrlParam("history_back_Url") && getUrlParam("history_back_Url") != undefined) {
			historyArray.push(getUrlParam("history_back_Url"));
			$.cookie("sourceUrl", getUrlParam("history_back_Url"), {
				path: '/',
				domain: me.switchDomain(window.location.host),
				expires: "Session"
			});
		}
		//登录页面特殊处理
		var hrefSplitArray = window.location.href.split("?");
		for (var i = 0; i < hrefSplitArray.length; i++) {
			var insertUrl = getStringParam(hrefSplitArray[i], "history_back_Url");
			if (insertUrl && insertUrl != undefined) {
				historyArray.push(insertUrl);
				pathName = insertUrl;
				$.cookie("sourceUrl", insertUrl, {
					path: '/',
					domain: me.switchDomain(window.location.host),
					expires: "Session"
				});
			}
		}
		if (historyArray.length>1) {
			var currentPage = historyArray.pop(),
				regPath = pathName.slice(0, pathName.lastIndexOf("."));
			if ((currentPage && currentPage.indexOf(regPath) == 0) || ($("#isBackUrl").length > 0 && $("#isBackUrl").val() == "0")) {
				//do nothing
			} else if (currentPage) {
				historyArray.push(currentPage); //历史里面没有现在传入的url，在加回去
			}
		}
		historyArray.push(url);
		if (historyArray.length > 50) {
			historyArray.shift();
		}
		historyUtils.saveLocal(historyArray);
	},
	//更新返回数组,查看是否需要保存上一个地址
	update: function() {
		var me = this,
			historyArray = historyUtils.getLocal(),
			currentPage = historyArray.pop();
		if (currentPage) {
			historyUtils.saveLocal(historyArray);
		}
	},
	//返回
	back: function() {
		var me = this,
			historyArray = [];
		historyArray = historyUtils.getLocal();
		if (jumpUrl[window.location.pathname] == 1) {
			var currentPage = window.location.href;
			var prevPage = historyArray?historyArray.pop():false;
		} else {
			var currentPage = historyArray.pop(); //去掉当前页面，pop取最后，类似stack
			var prevPage = historyArray.pop(); //pop取最后，类似stack
		}
		if (!prevPage) { //没有历史页面
			historyUtils.add(currentPage, currentPage); //将当前页面加入回数组中
			if (currentPage != window.location.href) {
				window.location.href = currentPage;
			} else {
				var hrefSplitArray = window.location.href.split("?");
				for (var i = 0; i < hrefSplitArray.length; i++) {
					var insertUrl = getStringParam(hrefSplitArray[i], "history_back_Url");
					if (insertUrl && insertUrl != undefined) {
						window.location.href = insertUrl;
					}
				}
			}
			return;
		}
		historyUtils.saveLocal(historyArray);
		window.location.href = prevPage;
	},
	//清空返回数组
	clearUp: function() {
		var me = this;
		$.cookie(historyUtils.key, "", {
			path: '/',
			domain: me.switchDomain(window.location.host),
			expires: "Session"
		});
	},
	getLocal: function() {
		var result = $.cookie(historyUtils.key);
		if (!result) {
			return null;
		}
		return JSON.parse(result);
	},
	saveLocal: function(data) {
		var me = this,
			localDomain = me.switchDomain(window.location.host);
		if (!localDomain) {
			localDomain = g_loction.domain;
		}
		$.cookie(historyUtils.key, JSON.stringify(data), {
			path: '/',
			domain: localDomain,
			expires: "Session"
		});
	},
	key: "_history_",
	switchDomain: function(hostName) {
		if (hostName.indexOf(g_loction.domain) != -1) {
			return g_loction.domain;
		}
		return hostName.slice(hostName.indexOf("."));
	}
}
//不记录的返回地址
var jumpUrl = {
	"/front/order/saveUserPayType.action": 1,
	"/member/transferToShop.action": 1,
	"/front/order/initAddAddress.action": 1,
	"/front/seller/initAddAddress.action": 1,
	"/passport/login.action": 1,
	"/member/activeCoupon.html": 1,
	"/cart/premerge/getPreCartGoods.action": 1,
	"/member/getVenderCouponList.action": 1
};
/**
 判断登录方法
 **/
//cookie获取用户信息
function getUserInfoFromCookie() {
	var userInfo = {};
	var userInfoCookie = $.cookie('UserInfo');
	if (userInfoCookie) {
		var userProps = userInfoCookie.split('&');
		for (var i = 0; i < userProps.length; i++) {
			var userProp = userProps[i];
			userProp = userProp.replace(/\"/g, "");
			if (userProp) {
				var userKeyValue = userProp.split('=');
				if (userKeyValue != null && userKeyValue.length == 2) {
					var userKey = userKeyValue[0];
					var userValue = userKeyValue[1];
					userInfo[userKey] = userValue;
				}
			}
		}
	}
	return userInfo;
}
//cookie检验是否登录
function cookieIsLogin(cb) {
	var userInfo = getUserInfoFromCookie(),
		userid = userInfo["UserId"];
	if (!userid || userid == '0' || userid == '-1') {
		cb(-1);
		return;
	} else {
		ajaxIsLogin(userInfo, cb);
	}
}
// Ajax检验登录
function ajaxIsLogin(userInfo, cb) {
	var userName = userInfo["UserName"],
		token = userInfo["Token"],
		moniPharmacistId = userInfo['moniPharmacistId'] || "";
	$.ajax({
		url: 'http://m'+g_loction.domain+'/wap/interfaces/isLogin.action',
		type: 'GET',
		data: {
			'userName': userName,
			'token': token,
			'moniPharmacistId': moniPharmacistId
		},
	}).done(function(data) {
		var state = parseInt(data);
		if (state < 1) {
			cb(-1)
		} else if (cb) {
			cb(1);
		}
	}).fail(function() {
		cb(1);
	});
}

// 获取浏览器信息
function getBrowserInfo() {
	var UA = window.navigator.userAgent.toLowerCase();
	var regTag = /android|iphone|windows phone|ipad/i;
	var sDevice = UA.match(regTag);
	var isClient = /youyi/.test(UA); //是否UYI的客户端
	var isWxBrowser = /MicroMessenger/i.test(UA);
	var info = {
		device: sDevice && sDevice.length > 0 ? "mobile" : "pc",
		os: sDevice && sDevice.length > 0 ? sDevice[0] : "pc",
		isClient: isClient,
		isWxBrowser: isWxBrowser
	};
	window.browserInfo = info;
	return info;
}


//调用APP方法
var jsBridge = false;

function callAppFunc(appFn, jsonData, callback) {
	var browserInfo = getBrowserInfo();
	var iosJSBridgeReady = false;
	//非APP环境不执行
	if (!browserInfo.isClient) {
		return;
	}
	//安卓环境
	if (browserInfo.os === "android") {
		try {
			if (jsonData) {
				window.android.commonFunction(appFn, JSON.stringify(jsonData));
			} else {
				window.android.commonFunction(appFn, "null");
			}
		} catch (e) {
			return false;
		}
	}
	//IOS环境
	if (browserInfo.os === "iphone") {
		if (jsBridge === false) {
			setupWebViewJavascriptBridge(function(bridge) {
				jsBridge = bridge;
				bridge.callHandler(appFn, jsonData, function(data) {
					if (callback) {
						callback(data);
					}
				});
				bridge.registerHandler('getAppShareData', function(data, responseCallback) {
					getAppShareData();
				});
			});
		} else {
			try {
				jsBridge.callHandler(appFn, jsonData, function(data) {
					if (callback) {
						callback(data);
					}
				});
				jsBridge.registerHandler('getAppShareData', function(data, responseCallback) {
					getAppShareData();
				});
			} catch (e) {
				window.setTimeout(function() {
					callAppFunc(appFn, jsonData, callback);
				}, 200);
			}
		}
	}
}

//IOS-webView-javascript-briged
function setupWebViewJavascriptBridge(callBack) {
	jsBridge = true;
	if (window.WebViewJavascriptBridge) {
		if (!WebViewJavascriptBridge._messageHandler) {
			WebViewJavascriptBridge.init("WebViewJavascriptBridge Initialization completed!");
		}
		callBack(WebViewJavascriptBridge);
	} else {
		document.addEventListener('WebViewJavascriptBridgeReady', function(e) {
			e.bridge.init("WebViewJavascriptBridge Initialization completed!");
			callBack(e.bridge);
		}, false);
	}
}

//安卓回调函数
function androidCallBack(functype, result) {
	if (functype == "payCallBack") {
		if (result === "true" || result === true) {
			showTips("支付成功！");
			window.location.href = "http://wx.360haoyao.com/wgroup/pay/payCallBack.action?orderId=" + payOrder.orderId + "&pinId=" + payOrder.pinId;
		} else {
			showTips("支付失败请稍后再试！");
			$("#toPay").removeClass('disabled').html(payOrder.btnText);
		}
	} else if (functype == "shareCallBack") {
		if (result === "true" || result === true) {

		} else {
			showTips("分享失败请稍后再试！");
		}
	} else if (functype == "wxtEncryptedCharacters") {
		if (result === "true" || result !== true) {
			payOrder.appKey = result;
		}
	}
}

$(function() {
	if (jumpUrl[window.location.pathname] == 1) {

	} else {
		historyUtils.add(window.location.href, window.location.origin + window.location.pathname);
	}
	//为APP调用
	var urlApp = {
		"http://search.m.360haoyao.com/": 1
	}
	var UA = navigator.userAgent.toLowerCase();

	// app除了店铺页面其他都去除头部
	if (/youyi/.test(UA)) {
		if (!(/\/shop\//.test(window.location.href) || /\/store\//.test(window.location.href))) {
			$("#gHeader").hide();
			$("#h5-header").hide();
			$(".g-header").hide();
		}
	}

	//修改返回首页功能
	var sourceUrl = $.cookie("sourceUrl"),
		is360Safe = $.cookie("is360Safe");
	if (sourceUrl != null && sourceUrl != "") {
		$(".g-home a").attr('href', sourceUrl);
		$(".top_nav_home a").attr('href', sourceUrl);
		// 如果是CMS页面在360安全卫士中打开则隐藏顶部
		if(window.location.hostname.indexOf("sale"+g_loction.domain)>-1){
			$("#h5-header").length > 0 ? $("#h5-header").hide() : "";
			$("header").length > 0 ? $("header").hide() : "";
		}
	}
});