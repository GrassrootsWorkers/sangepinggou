function minusitem(e){
	var inputObject = $(e).parent().find('input');
	var oldValue = inputObject.val();
	inputObject.val(parseInt(oldValue) - 1);
	if(parseInt(inputObject.val())==0){
		jConfirm("ȷ�ϲ�����ѡ����Ʒ��?", "", function(r){
			if(r){
				prompt(inputObject);
			}else{
				inputObject.val(oldValue);
			}
		} , "ȷ��", "ȡ��");
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
	//������У�鴦����������ַ��������С��0�������ʾ����δ���
	var delFlag =true;
	var oldValue =  $(e).attr("origianNum");
	var limitCount = $(e).parent().find('.xg').attr('lc');//�޹�����
	var newValue = $(e).val();
	var catalimit = $(e).parent().attr('catal');
	var limitBuy=$(e).parent().attr('limitbuy');//������
	if (parseInt(newValue) < parseInt(0) || isNaN(parseInt(newValue)) || !/^[0-9]+.?[0-9]*$/.test(parseInt(newValue)) ) {
		jAlert("�����ʽ����");
		$(e).val(oldValue);
		return; 
	}
	if (parseInt(newValue) < parseInt(limitBuy) && parseInt(limitBuy)>0 ) {
		jAlert("�����������ܵ���"+limitBuy+"����");
		$(e).val(oldValue);
		$('button[class="btn minu"]').addClass("disabled");
		return;
	}
	if (parseInt(newValue) > parseInt(catalimit) && parseInt(catalimit)>0) {
		jAlert("�����������ܳ���"+catalimit+"�������ڹ�������ϵ�ͷ�400-007-0958");
		$(e).val(oldValue);
		return;
	}
	//�ӺŰ�ť��ʾ��֤����ͨ��Ʒ��
	if (parseInt(newValue) > parseInt(limitCount)) {
		jAlert("�����������ܳ���"+limitCount+"��!");
		$('button[class="btn add"]').addClass("disabled");
		$(e).val(limitCount);
		return; 
	}
	var itemid = $(e).attr('itemid');
	var itype = $(e).attr('itype');
	var refmainitemid = $(e).attr('refmainitemid');
	//ajax�ύ
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
	jConfirm("ȷ�ϲ�����ѡ����Ʒ��?", "", 
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
		
	} , "ȷ��", "ȡ��");
}


/**
* ȥ���㣬������ǰ�ļ�飬������¼
*/
function cartnullalert(event,cartDomain,appDomain,cartItemCount,type){
	if (cartItemCount > 0) {
		if(!checkCartNum()){
			return;
		}
		createUser();
		var backurl;
		backurl = 'http://buy.m.111.com.cn/interfaces/wap/orderSure.action?page=cart';
		//��תҳ��
		loginCheck(event, backurl, 0, null);
	} else {
		jAlert('���Ĺ��ﳵ��û����Ʒ����Ʒ��治�㣬ϵͳ���Զ�ת����ҳ�Է���������������Ʒ��',"",function (){
			window.location.href="http://m.111.com.cn";
		});
		
	}
}
function checkCartNum(){
	var re = /^[1-9]+[0-9]*]*$/;
	var numArray=$(".num");
	$(numArray).each(function(){
		if(!re.test($(this).val())) {
			jAlert("�������Ʒ����������Ʒ����ֻ��Ϊ��������");
			return false;
		}
	});
	return true;
}
/**
 * ������ղ� status==1
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
//���Ե���
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

//��ȡ�û�Cookies ����loginUser����
function createUser(){
    loginUser = new Object();
    loginUser.UserId = ppkRead("UserInfo", "UserId");//�û�ID 
	loginUser.UserName = decodeURIComponent(ppkRead("UserInfo", "UserName"));//�û���
	loginUser.NickName=decodeURIComponent(ppkRead("UserInfo", "NickName"));//�û���
	loginUser.Token = ppkRead("UserInfo", "Token");//���ƺ�
	loginUser.Security = ppkRead("UserInfo", "Security");//���ܴ�
	if(loginUser.UserName == null || loginUser.UserName == '' || loginUser.UserName == 'null'){
    	return null;
    } else {
    	if(loginUser.NickName == null || loginUser.NickName == '' || loginUser.NickName == 'null')
    		return loginUser.UserName;
    	return loginUser.NickName;
    }
}

//��֤�û���¼Cookies
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