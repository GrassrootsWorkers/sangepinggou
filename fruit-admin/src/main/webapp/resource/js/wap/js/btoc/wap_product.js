$(document).ready(function(){
     //设置购物车数量
     $("a[class='cart']").html(countCart());
//     ajaxLoadComments(1);
});

function search(){
    var keyword=$("#keyword").val();
    if(keyword!=null && keyword!=""){
          $("#wapSearchForm").attr("action","http://m.111.com.cn/search/search.action?keyWord="+encodeURI(encodeURI(keyword)));
    }
	return true;
} 
function searchAjax(url , itemId,pn) {
	window.location.href=url+"?itemId="+ itemId +"&currProductName=" + pn;
}

function changeViews( v ) {
	if(v==1){
		$("#goodsDetailDiv").removeClass("hide");
		$("#productDescDiv").addClass("hide");
		$("#productComment").addClass("hide");
		$("#productQuestion").addClass("hide");
	}else if (v==2){
		$("#productDescDiv").removeClass("hide");
		$("#goodsDetailDiv").addClass("hide");
		$("#productComment").addClass("hide");
		$("#productQuestion").addClass("hide");
	}else if (v==3){
		$("#productComment").removeClass("hide");
		$("#productDescDiv").addClass("hide");
		$("#productQuestion").addClass("hide");
		$("#goodsDetailDiv").addClass("hide");
	}else{
		$("#productQuestion").removeClass("hide");
		$("#productDescDiv").addClass("hide");
		$("#goodsDetailDiv").addClass("hide");
		$("#productComment").addClass("hide");
	}
}
