function minusitem(e){
	var inputObject = $(e).parent().find('input');
	var oldValue = inputObject.val();
	inputObject.val(parseInt(oldValue) - 1);
	if(parseInt(inputObject.val())==0){
		jConfirm("确认不购买选择商品吗?", "", function(r){
			if(r){
				prompt(inputObject);
			}else{
				inputObject.val(oldValue);
			}
		} , "确定", "取消");
	}else{
		prompt(inputObject);
	}
}
function additem(e){
	var inputObject = $(e).parent().find('input');
	var oldValue = inputObject.val();
	inputObject.val(parseInt(oldValue) + 1);
	prompt(inputObject);
}
function prompt(e){
	//做输入校验处理，如果输入字符串，如果小于0，如何提示，如何处理
	var delFlag =true;
	var oldValue =  $(e).attr("origianNum");
	var limitCount = $(e).parent().find('.xg').attr('lc');//限购数量
	var newValue = $(e).val();
	var catalimit = $(e).parent().attr('catal');
	var limitBuy=$(e).parent().attr('limitbuy');//起购数量
	if (parseInt(newValue) < parseInt(0) || isNaN(parseInt(newValue)) || !/^[0-9]+.?[0-9]*$/.test(parseInt(newValue)) ) {
		jAlert("输入格式有误");
		$(e).val(oldValue);
		return; 
	}
	if (parseInt(newValue) < parseInt(limitBuy) && parseInt(limitBuy)>0 ) {
		jAlert("购买数量不能低于"+limitBuy+"件。");
		$(e).val(oldValue);
		$('button[class="btn minu"]').addClass("disabled");
		return;
	}
	if (parseInt(newValue) > parseInt(catalimit) && parseInt(catalimit)>0) {
		jAlert("购买数量不能超过"+catalimit+"件，大宗购物请联系客服400-007-0958");
		$(e).val(oldValue);
		return;
	}
	//加号按钮显示验证（普通商品）
	if (parseInt(newValue) > parseInt(limitCount)) {
		jAlert("购买数量不能超过"+limitCount+"件!");
		$('button[class="btn add"]').addClass("disabled");
		$(e).val(limitCount);
		return; 
	}
	var itemid = $(e).attr('itemid');
	var itype = $(e).attr('itype');
	var refmainitemid = $(e).attr('refmainitemid');
	//ajax提交
	var urlPre = "/cart/shoppingcart/updateshoppingcartitem.action";
	var url = urlPre + "?ope=change&num=" + newValue + "&cartitemid=" + itemid + "&itype=" + itype + "&refmainitemid=" + refmainitemid + "&A=" + Math.random();
	$.get(url, {}, function(returnData) {
		if (returnData) {
			var reg=/^error_/;
			if (returnData.match(reg) == "error_") {
				$(e).val(oldValue);
				jAlert(returnData.substring(6,returnData.length));
			} else {
				$('div[class="goodsListContainer"]').html(returnData);
				$("a[class='cart']").html(countCart());
			}
		}
	});
}
function deleteitem(itemid,itype,refmainitemid){
	jConfirm("确认不购买选择商品吗?", "", 
		function(r){
		if(r ){
			var url = "/cart/shoppingcart/deleteshoppingcartitem.action?cartitemid=" + itemid + "&itype=" + itype + "&refmainitemid=" + refmainitemid + "&A=" + Math.random();
			$.get(url, {}, function(returnData) {
				if (returnData) {
					$('div[class="goodsListContainer"]').html(returnData);
				    $("a[class='cart']").html(countCart());
				}
			});
		}else{
			return ;
		}
		
	} , "确定", "取消");
}


/**
* 去结算，做结算前的检查，包括登录
*/
function cartnullalert(event,cartDomain,appDomain,cartItemCount,type){
	if (cartItemCount > 0) {
		if(!checkCartNum()){
			return;
		}
		createUser();
		var backurl;
		backurl = 'http://buy.m.111.com.cn/interfaces/wap/orderSure.action?page=cart';
		//跳转页面
		loginCheck(event, backurl, 0, null);
	} else {
		jAlert('您的购物车中没有商品或商品库存不足，系统将自动转向首页以方便您继续购买商品！',"",function (){
			window.location.href="http://m.111.com.cn";
		});
		
	}
}
function checkCartNum(){
	var re = /^[1-9]+[0-9]*]*$/;
	var numArray=$(".num");
	$(numArray).each(function(){
		if(!re.test($(this).val())) {
			jAlert("输入的商品数量错误！商品数量只能为正整数。");
			return false;
		}
	});
	return true;
}
/**
 * 如果是收藏 status==1
 */
function loginCheck(e,backurl,status,id){
		userLoginCheck(e, backurl, status, id, "");
}


function userLoginCheck(e, backurl, status, id, curl){
	var e =  window.event;
	if(!createUser()){
		if(status == '1'){
			backurl += "&aa="+id;
		}
		testMessageBox(e,backurl);
		return;
	}
	
    if(status != '1'){
    	backurl = 'http://buy.m.111.com.cn/interfaces/wap/orderSure.action?page=cart';
    	window.location.href = backurl;
      	return ;
   	}
}
//测试弹出
function testMessageBox(ev,backurl){
	window.location.href='http://passport.m.111.com.cn/sso/login.action?ReturnUrl='+backurl;
}


function checkAll(){
	var url = "/cart/shoppingcart/updateshoppingstatus.action?cartitemid=1&A=" + Math.random();
	$.get(url, {}, function(returnData) {
		if (returnData) {
			var reg=/^error_/;
			if (returnData.match(reg) == "error_") {
				jAlert(returnData.substring(6,returnData.length));
			} else {
				$('div[class="goodsListContainer"]').html(returnData);
			}
		}
	});
}

//读取用户Cookies 创建loginUser对象
function createUser(){
    loginUser = new Object();
    loginUser.UserId = ppkRead("UserInfo", "UserId");//用户ID 
	loginUser.UserName = decodeURIComponent(ppkRead("UserInfo", "UserName"));//用户名
	loginUser.NickName=decodeURIComponent(ppkRead("UserInfo", "NickName"));//用户名
	loginUser.Token = ppkRead("UserInfo", "Token");//令牌号
	loginUser.Security = ppkRead("UserInfo", "Security");//加密串
	if(loginUser.UserName == null || loginUser.UserName == '' || loginUser.UserName == 'null'){
    	return null;
    } else {
    	if(loginUser.NickName == null || loginUser.NickName == '' || loginUser.NickName == 'null')
    		return loginUser.UserName;
    	return loginUser.NickName;
    }
}

//验证用户登录Cookies
function ppkRead(name, second) {
if (!second) {
    var nameEQ = name + "=";
    var ca = document.cookie.split(';');
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') c = c.substring(1, c.length);
        if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length, c.length);
    }
    return null;
} else {
    var val = ppkRead(name);
    if (val) {
        var arr = val.split('&');
        for (var i = 0, len = arr.length; i < len; i++) {
            if (arr[i].indexOf(second) > -1) {
                return arr[i].substring(arr[i].indexOf("=") + 1);
                }
            }
            return null;
        }
        return null;
    }
}