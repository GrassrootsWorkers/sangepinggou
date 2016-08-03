function toPage(pageIndex,dateStatus,orderStatus){
	window.location.href = "http://my.m.111.com.cn/ucenter/orderList.action?pageIndex="+pageIndex+"&dateStatus="+dateStatus+"&orderStatus="+orderStatus;
}
function toPay(orderId){
	window.open('http://buy.m.111.com.cn/payment/confirmWap.action?id='+orderId+'&isWapOrder=1');
}

function fastAddtoCart(itemId){
	window.location.href="http://buy.m.111.com.cn/cart/shoppingcart/additem2shoppingcart.action?itype=11&itemnum=1&itemids="+itemId;
}

function toLogDetail(orderId){
	window.location.href="http://my.m.111.com.cn/ucenter/logDetail.action?orderId="+orderId;
}

function submitCclReason(orderId) {
	jConfirm("您确认要取消此订单吗?","我的订单", function(yes){
		if(yes){
			 $.ajax({
				   type: "get",
				   url: "http://my.m.111.com.cn/ucenter/cancelOrder.action?orderId="+orderId+"&reason=3",
				   success: function(res){
				         if (res == "1") {
				        	 location.href = "http://my.m.111.com.cn/ucenter/orderList.action";
				         }else if(res == "4"){
				        	 jAlert('服务器繁忙,请稍后再试！');
				         }else{
				        	 jAlert('取消失败,请稍后再试');
				         }
					 }
				  });
		}else{
			return false;
		}
	});
}