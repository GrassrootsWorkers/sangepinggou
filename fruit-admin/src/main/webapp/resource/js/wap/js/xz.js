
//��ȡ������ں���Ϣ
var browser={
versions:function(){
var u = navigator.userAgent, app = navigator.appVersion;
return {
trident: u.indexOf('Trident') > -1, //IE�ں�
presto: u.indexOf('Presto') > -1, //opera�ں�
webKit: u.indexOf('AppleWebKit') > -1, //ƻ�����ȸ��ں�
gecko: u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1, //����ں�
mobile: !!u.match(/AppleWebKit.*Mobile.*/)||!!u.match(/AppleWebKit/), //�Ƿ�Ϊ�ƶ��ն�
ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios�ն�
android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1, //android�ն˻���uc�����
iPhone: u.indexOf('iPhone') > -1 || u.indexOf('Mac') > -1, //�Ƿ�ΪiPhone����QQHD�����
iPad: u.indexOf('iPad') > -1, //�Ƿ�iPad
webApp: u.indexOf('Safari') == -1 //�Ƿ�webӦ�ó���û��ͷ����ײ�
};
}()
} 

function do_app_download(){
if(browser.versions.ios == true || browser.versions.iPhone ==true || browser.versions.iPad == true){
window.location.href='https://itunes.apple.com/us/app/1hao-yao-dian/id727578007?ls=1&mt=8';
} else if(browser.versions.android == true){
window.location.href='http://m.111.com.cn/mobile/yaodian2.apk';
}else{
window.location.href='/m';
}
} 