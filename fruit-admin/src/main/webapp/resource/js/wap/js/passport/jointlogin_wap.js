function jointLogin(type){
   	var returnUrl = $.cookie("ReturnUrl");
   	if(returnUrl == null){
   		$.cookie("ReturnUrl","http://m.111.com.cn",{expires:60*10, path:'/',domain:'.111.com.cn' });
   	}else{ 
   		$.cookie("ReturnUrl", null, { path: '/' ,domain:'111.com.cn' }); 
   		$.cookie("ReturnUrl",returnUrl.replace("\"","").replace("\"","") ,{expires:60*10, path:'/',domain:'.111.com.cn' });
   	}
	returnUrl =  $.cookie("ReturnUrl");
	if(type == 'QQ')
		window.location.href = 'http://passport.m.111.com.cn/interfaces/authorize/tencent?flag=1&url='+returnUrl;
	else if(type == 'RR')
		window.location.href = 'http://graph.renren.com/oauth/grant?client_id=1479327d613943a6a59611faf12349d8&response_type=code&display=page&secure=true&origin=00000&redirect_uri=http://passport.m.111.com.cn/interfaces/userAuthSuccess/joint/RR?itemurl='+returnUrl;
	else if(type == 'BAIDU') 
		window.location.href = 'http://openapi.baidu.com/oauth/2.0/authorize?client_id=DewROSAhYao4ihEMisw7jmUK&response_type=code&redirect_uri=http://passport.m.111.com.cn/interfaces/userAuthSuccess/joint/Baidu?itemurl='+returnUrl;
	else if(type == 'SINA')
		window.location.href = 'https://api.weibo.com/oauth2/authorize?client_id=1398898410&response_type=code&redirect_uri=http://passport.m.111.com.cn/interfaces/userAuthSuccess/joint/Sina?itemurl='+returnUrl;
	else if(type == '163') 
		window.location.href = 'http://reg.163.com/open/oauth2/authorize.do?client_id=6593850819&response_type=code&redirect_uri=http://passport.111.com.cn/interfaces/authorize/N163?flag=1&itemurl='+returnUrl;
	else if(type == 'ZFB')
		window.location.href = 'http://passport.m.111.com.cn/interfaces/authorize/alipay?itemurl='+returnUrl;
	else if(type == '360')
		window.location.href = 'http://passport.m.111.com.cn/interfaces/authorize/360safe?itemurl='+returnUrl;
	else if(type="YHD")
		window.location.href = 'http://passport.111.com.cn/interfaces/authorize/yihaodian?flag=1&itemurl='+returnUrl;
	else if(type="WLT")
		window.location.href = 'http://passport.m.111.com.cn/interfaces/authorize/wanlitong?itemurl='+returnUrl;
	return false;
};