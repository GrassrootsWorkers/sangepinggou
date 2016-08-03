function goHome(){
	var sourceUrl=$.cookie("sourceUrl");
	if(sourceUrl==null  || sourceUrl==""){
		window.location.href="http://m.111.com.cn";
	}else{
		$.cookie('sourceUrl', null, {path:'/', domain: '.111.com.cn'});
		window.location.href=sourceUrl;
	}
}