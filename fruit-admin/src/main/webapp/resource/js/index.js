$(document).ready(function(){
	$('.flicker-example').flicker();
	nav_tag();

});





//标签滑动样式
function nav_tag(){
$(window).scroll(function() {
		if ($(window).scrollTop() > 750) {
			$('.nav-tag').addClass("nav-tag-fixed");
		} else {
			$(".nav-tag").removeClass("nav-tag-fixed"); 
		}
	});

}

