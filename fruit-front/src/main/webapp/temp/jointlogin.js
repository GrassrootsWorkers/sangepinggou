var base64EncodeChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
/**
 * base64编码
 * @param {Object} str
 */
function base64encode(str){
    var out, i, len;
    var c1, c2, c3;
    len = str.length;
    i = 0;
    out = "";
    while (i < len) {
        c1 = str.charCodeAt(i++) & 0xff;
        if (i == len) {
            out += base64EncodeChars.charAt(c1 >> 2);
            out += base64EncodeChars.charAt((c1 & 0x3) << 4);
            out += "==";
            break;
        }
        c2 = str.charCodeAt(i++);
        if (i == len) {
            out += base64EncodeChars.charAt(c1 >> 2);
            out += base64EncodeChars.charAt(((c1 & 0x3) << 4) | ((c2 & 0xF0) >> 4));
            out += base64EncodeChars.charAt((c2 & 0xF) << 2);
            out += "=";
            break;
        }
        c3 = str.charCodeAt(i++);
        out += base64EncodeChars.charAt(c1 >> 2);
        out += base64EncodeChars.charAt(((c1 & 0x3) << 4) | ((c2 & 0xF0) >> 4));
        out += base64EncodeChars.charAt(((c2 & 0xF) << 2) | ((c3 & 0xC0) >> 6));
        out += base64EncodeChars.charAt(c3 & 0x3F);
    }
    return out;
}

function jointLogin(type,obj){
	$(obj).closest("ul").find("a").removeClass("on");
	$(obj).addClass("on");
	
   	var returnUrl = $.cookie("ReturnUrl");
	$.cookie("app_union_login","2",{expires:60*10, path:'/',domain:g_loction.domain });
   	if(returnUrl == null){
   		$.cookie("ReturnUrl","http://m"+g_loction.domain,{expires:60*10, path:'/',domain:g_loction.domain });
   	}else{ 
   		$.cookie("ReturnUrl", null, { path: '/' ,domain:g_loction.domain }); 
   		$.cookie("ReturnUrl",returnUrl.replace("\"","").replace("\"","") ,{expires:60*10, path:'/',domain:g_loction.domain });
   	}

	returnUrl =  $.cookie("ReturnUrl");
    var SINAUrl = "http://user.m.360jk.com/member/center.action";
	if(type == 'QQ')
		window.location.href = 'http://login.m'+g_loction.domain+'/interfaces/authorize/tencent?flag=1&url='+returnUrl;
	else if(type == 'RR')
		window.location.href = 'http://graph.renren.com/oauth/grant?client_id=1479327d613943a6a59611faf12349d8&response_type=code&display=page&secure=true&origin=00000&redirect_uri=http://login.m'+g_loction.domain+'/interfaces/userAuthSuccess/joint/RR?itemurl='+returnUrl;
	else if(type == 'BAIDU') 
		window.location.href = 'http://openapi.baidu.com/oauth/2.0/authorize?client_id=DewROSAhYao4ihEMisw7jmUK&response_type=code&redirect_uri=http://login.m'+g_loction.domain+'/interfaces/userAuthSuccess/joint/Baidu?itemurl='+returnUrl;
	else if(type == 'SINA')
		window.location.href = 'https://api.weibo.com/oauth2/authorize?client_id=1380446419&response_type=code&redirect_uri=http://login.m.360jk.com/interfaces/userAuthSuccess/joint/Sina?itemurl='+base64encode(SINAUrl);
	else if(type == '163') 
		window.location.href = 'http://reg.163.com/open/oauth2/authorize.do?client_id=6593850819&response_type=code&redirect_uri=http://login'+g_loction.domain+'/interfaces/authorize/N163?flag=1&itemurl='+returnUrl;
	else if(type == 'ZFB')
		window.location.href = 'http://login.m'+g_loction.domain+'/interfaces/authorize/alipay?itemurl='+returnUrl;
	else if(type == '360')
		window.location.href = 'http://login.m'+g_loction.domain+'/interfaces/authorize/360safe?itemurl='+base64encode(returnUrl)+"&flag=wap";
	else if(type="YHD")
		window.location.href = 'http://login'+g_loction.domain+'/interfaces/authorize/yihaodian?flag=1&itemurl='+returnUrl;
	else if(type="WLT")
		window.location.href = 'http://login.m'+g_loction.domain+'/interfaces/authorize/wanlitong?itemurl='+returnUrl;
	
	return false;
};