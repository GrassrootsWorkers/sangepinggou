function deleteFavorite(pid){
	jConfirm("您确认要删除吗?","我的收藏", function(yes){
		if(yes){
			var curl = "http://my.m.111.com.cn/ucenter/deleteFavorite.action?ids="+pid;
			jQuery.ajax({
				type: "POST",
				url: curl,
				dataType: "html",
				cache: false,
				success: function(date){
					if(date == "1"){
						window.location.href="http://my.m.111.com.cn/ucenter/toEditFavorite.action";
					}else{
						jAlert("系统繁忙,请稍后再试");
					}
		       	}
		  	});
		}else{
			return false;
		}
	});
}