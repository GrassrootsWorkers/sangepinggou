function deleteFavorite(pid){
	jConfirm("��ȷ��Ҫɾ����?","�ҵ��ղ�", function(yes){
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
						jAlert("ϵͳ��æ,���Ժ�����");
					}
		       	}
		  	});
		}else{
			return false;
		}
	});
}