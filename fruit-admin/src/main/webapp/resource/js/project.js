jQuery(function($){
	var origSelOjbs = $('#myMulSelect').selectlist();
	/*项目 主要信息   其他信息  结束*/
	$('.info_pro_tab ul.info_pro_menu li').click(function(){
		//获得当前被点击的元素索引值
		var Index = $(this).index();
		//给菜单添加选择样式
		$(this).addClass('active').siblings().removeClass('active');
		//显示对应的div
		$('.info_pro_tab').children('div').eq(Index).show().siblings('div').hide();

	});
	/*项目 主要信息   其他信息  结束*/


	/*项目 主要信息   其他信息 图片轮播展示开始*/
	$('#slides').slides({
		preload: true,
		play: 5000,
		pause: 2500,
		hoverPause: true,

		animationComplete: function(current){
			$('.caption').animate({
				bottom:0
			},200);
			if (window.console && console.log) {
				// example return of current slide number
				console.log(current);
			};
		}
	});

	/*项目 主要信息   其他信息  图片轮播展示结束*/



//置顶js

});
