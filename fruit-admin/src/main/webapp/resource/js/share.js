/**
 * Created by Administrator on 2016/3/3 0003.
 */
 function ShareTip (){
}

ShareTip.prototype.shareToQQ=function(content,url,picUrl){
    var shareQQstring='http://v.t.QQ.com/share/share.php?title='+content+'&url='+url+'&pic='+picUrl;
    window.open(shareQQstring,'newwindow','height=100,width=100,top=100,left=100');
}
//分享到新浪微博
ShareTip.prototype.shareTosina=function(title,url,picUrl){
    var sharesinastring='http://v.t.sina.com.cn/share/share.php?title='+title+'&url='+url+'&content=utf-8&sourceUrl='+url+'&pic='+picUrl;
    window.open(sharesinastring,'newwindow','height=400,width=400,top=100,left=100');
}
//分享到QQ空间
ShareTip.prototype.shareToQQzone=function(title,url,picUrl){
    var shareQQzonestring='http://sns.qzone.QQ.com/cgi-bin/qzshare/cgi_qzshare_onekey?summary='+title+'&url='+url+'&pics='+picUrl;
    window.open(shareQQzonestring,'newwindow','height=400,width=400,top=100,left=100');
}

//分享到新浪
function shareProject(projectId,projectTitle,shareType) {
    var title = projectTitle;
    var url = "http://www.binggou.com/project/"+projectId+".html";
    var picUrl = "http://p1.binggou.com/"+projectId/1000+"/"+projectId+"/"+"p_center.jpg";
    var share = new ShareTip();
    if("QQ" == shareType){
        share.shareToQQ(title,url,picUrl)
    }
    if("sina" == shareType){
        share.shareTosina(title,url,picUrl);
    }
    if("QQzone" == shareType){
        share.shareToQQzone(title,url,picUrl)
    }
}

//分享到新浪
function shareDemand(demandId,demandTitle,shareType) {
    var title = demandTitle;
    var url = "http://www.binggou.com/demand/"+demandId+".html";
    var picUrl = "http://p1.binggou.com/"+demandId/1000+"/"+demandId+"/d_center.jpg";
    var share = new ShareTip();
    if("QQ" == shareType){
        share.shareToQQ(title,url,picUrl)
    }
    if("sina" == shareType){
        share.shareTosina(title,url,picUrl);
    }
    if("QQzone" == shareType){
        share.shareToQQzone(title,url,picUrl)
    }

}