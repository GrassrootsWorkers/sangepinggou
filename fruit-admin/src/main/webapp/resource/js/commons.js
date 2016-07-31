jQuery(function($){
//置顶js
    $(window).scroll(function() {

        if ($(window).scrollTop() > 300) {
            $('#jump li').fadeIn(800);
        } else {
            $('#jump li').fadeOut(800);
        }
    });
    $("#top").click(function() {
        $('body,html').animate({
                scrollTop: 0
            },
            1000);
        return false;
    });

    //右侧登录悬浮效果


//标签滑动样式
    $(window).scroll(function() {
        if ($(window).scrollTop() > 580) {
            $('.nav-tag').addClass("nav-tag-fixed");
        } else {
            $(".nav-tag").removeClass("nav-tag-fixed");
        }
    });


//



    $("#leftsead a").hover(function(){
        if($(this).prop("className")=="youhui"){
            $(this).children("img.hides").show();
        }else{
            $(this).children("img.hides").show();
            $(this).children("img.shows").fadeOut("800");
            $(this).children("img.hides").animate({marginRight:'0px'},'slow');
        }
    },function(){
        if($(this).prop("className")=="youhui"){
            $(this).children("img.hides").fadeOut("slow");
        }else{
            $("img.shows").fadeIn("1000");
            $(this).children("img.hides").animate({marginRight:'-143px'},'slow');
        }
    });

    $("#top_btn").click(function(){if(scroll=="off") return;$("html,body").animate({scrollTop: 0}, 600);});
//底部二维码

    $('.bt-login-app').mouseenter(function () {
        $('.bt_app_icon').fadeIn("slow");

    });
    $('.bt-login-app').mouseleave(function () {
        $('.bt_app_icon').fadeOut("slow");
    });

//用户头像弹框

 $('.login-r-img').mouseenter(function () {
        $('.login_user_info').show();
    });
    $('.login-r-img').mouseleave(function () {
        $('.login_user_info').fadeOut("");
    });

});
function validateIfNumber(val){
    var reg = new RegExp("^[0-9]*$");
    if(!reg.test(val)){
      return false;
    }
    return true;
}
function validateNDouble(val){//验证小数，保留一位小数点

    var patten = /^-?\d+\.?\d{2}$/;

    return patten.test(val);

}
