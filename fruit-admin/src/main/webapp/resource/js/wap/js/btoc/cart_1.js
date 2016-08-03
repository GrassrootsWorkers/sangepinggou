var cartItemTypes = {1:{'name':'普通商品', 'showName':''}, 3:{'name':'套餐', 'showName':''}, 6:{'name':'赠品', 'showName':'<span class="red">[赠品]</span>'}, 7:{'name':'换购', 'showName':'<span class="red">[换购]</span>'}};
	function addItemToCart(iy, ri, ii, limitBuyNum){
//		stopPropagation(this.enent);
		if(iy == 1){
			var ic = $('#product_amount').val()||1;
			addToCart(iy, ri, ic, ii, null, limitBuyNum);
		}else if(iy == 3){
			//ii = $('#taocan_head > li[class="current"]').attr('name');
			addToCart(iy, ri, 1, ii, null, limitBuyNum);
		}
	}
	function addToCart(iy, ri, ic, ii, show, limitBuyNum){
		if(iy != 1 && iy != 3)return;
		if(!/^\d*$/g.test(ri))return;
		if(!/^\d*$/g.test(ii))return;
		if(!/^\d*$/g.test(ic))return;
		if(!limitBuyNum || limitBuyNum == 1){limitBuyNum=0}
		if(ic < limitBuyNum){ic = limitBuyNum}
		var cart = readCart();
		var item = {'ss':parseInt(0),'iy':parseInt(iy),'ri':parseInt(ri),'ic':parseInt(ic),'ii':parseInt(ii),'limitBuyNum':limitBuyNum};
		if(cart){
			for(var i in cart){
				if(cart[i].ii == item.ii && cart[i].iy == item.iy){
					cart[i].ic = parseInt(cart[i].ic) + parseInt(item.ic);
					writeCart(cart);
					if(!show){showAddCartDiv(iy, ri, ic, ii);}
					return;
				}
			}
			cart[cart.length] = item;
			writeCart(cart);
		}else{
			writeCart([item]);
		}
		if(!show){showAddCartDiv(iy, ri, ic, ii);}
	}

	function showAddCartDiv(iy, ri, ic, ii){
		$('#myModal').modal(null);
	}

	function readCart(){
		var c = $.cookie('cartItem');
		try{
			if(c){
				c = c.replace('"','').replace('"','');
				var rel = eval(c.replace(/\$/gm, '"'));
				if(c.match(/.*\$iy\$:.*?,.*\$ri\$:.*?,/)){
					return rel;
				}
			}
		}catch(e){}
		cleanCart();
		return null;
	}
	function writeCart(cart){
		$.cookie('cartItem'
			, $.stringifyJSON(cart).replace(/\"/gm, '$')
			, {path: '/', domain: '.111.com.cn', expires: 30});
		 $("a[class='cart']").html(countCart());
	}

	function countCart(){
		var cart = readCart();
		if(!cart){return 0}
		var count = 0;
		for(var i = 0; i < cart.length; i++){
			count += cart[i].ic;
		}
		return count;
	}

	function accMul(arg1, arg2) {
	    var m = 0, s1 = arg1.toString(), s2 = arg2.toString();
	    try { m += s1.split(".")[1].length } catch (e) { }
	    try { m += s2.split(".")[1].length } catch (e) { }
	    return Number(s1.replace(".", "")) * Number(s2.replace(".", "")) / Math.pow(10, m)
	}
	function accAdd(arg1, arg2) {
	    var r1, r2, m;
	    try { r1 = arg1.toString().split(".")[1].length } catch (e) { r1 = 0 }
	    try { r2 = arg2.toString().split(".")[1].length } catch (e) { r2 = 0 }
	    m = Math.pow(10, Math.max(r1, r2))
	    return (arg1 * m + arg2 * m) / m
	}

	function showCart(show){
		if(!show){
			if($('#minicart_list').css('display')=='block')
				return;
		}
		
		var cart = readCart();
		
		if(!cart){
			cleanCart();
			$('#minicart_list').show();
			return;
		}else{
			$('.none_tips').hide();
			$('.list_detail > ul').show()
			$('#miniCart_p2').hide();
		}
		
		var ids = '';
		var itemprice = {};
		
		for(var c = 0; c < cart.length; c++){
			if(ids != ''){ids += ',';}
			ids += cart[c].ii;
		}
		
		$.getJSON("http://m.111.com.cn/interfaces/item/itemPrice.action?jsoncallback=?&itemids=" + ids, function(data){
			var price = 0.0;
			for(var ci = 0; ci < cart.length; ci++){
				var c = cart[ci];
				$.each(data, function(i,item){
					if(c.ii == item.id){
						itemprice[c.ii+"_"+c.iy] = {};
						if(c.iy == 7){ // 换购
							$.ajax({
							   type: "GET",
							   dataType: "text",
							   url: 'http://m.111.com.cn/front/promotion/product/redePrice.action?itemId='+c.ii,
							   async: false,
							   success: function(msg){
							   	 if(msg != ""){
								   	 var price_ = eval('('+msg+')').price;
								     price = accAdd(price, accMul(price_, c.ic));
								     itemprice[c.ii+"_"+c.iy]['price'] = price_;
							   	 }
							   }
							});
						}else if(c.iy == 6){ // 赠品
							itemprice[c.ii+"_"+c.iy]['price'] = 0;
						}else{
							price = accAdd(price, accMul(item.price, c.ic));
							itemprice[c.ii+"_"+c.iy]['price'] = item.price;
						}

						itemprice[c.ii+"_"+c.iy]['name'] = item.name;
						itemprice[c.ii+"_"+c.iy]['saletype'] = item.saletype;
					}
				})
			}
			$('#minicart_list > div > div > p > strong').html('￥' + parseInt(price*100)/100);
			
			// show
			var ul = $('#minicart_list > div > ul')[0];
			var count = 0;
			$(ul).html('');
		
			for(var i = cart.length-1; i >= 0; i--){
				var c = cart[i];
				var url = 'http://www.111.com.cn/product/' + c.ri + '.html';
				
				if(c.iy==3){
					url += '#'+c.ii+'" onclick="$(\'#' + c.ii + '\').click();';
				}
				var ipath;
				if(itemprice[c.ii+"_"+c.iy]['saletype'] == 1){
					ipath = 'img/group/' + parseInt(c.ri/1000) + '/' + c.ri + '/50_34.jpg';
				} else {
					ipath = 'img/' + parseInt(c.ri/1000) + '/' + c.ri + '/50_50.jpg';
				}
				
				var pic = 'http://p' + ((parseInt(Math.random(10)*10)%3)+1) + '.maiyaole.com/' + ipath;
				var name = itemprice[c.ii+"_"+c.iy]['name'];
				
				$(ul).append('<li><a traget="_blank" class="pro_img" href="' + url
						+ '"><img heigth="40" width="40" alt="' + name + '" src="' + pic + '"></a>'
						+ '<a traget="_blank" class="pro_name" href="' + url + '">' + cartItemTypes[c.iy].showName + name + '</a>'
						+ '<span class="pro_price">￥' + itemprice[c.ii+"_"+c.iy]['price'] + '</span>'
						+ '<div class="num_box"><b name="editName_' + c.ii + '" class="minusDisable" onclick="updateCart(-1,' + c.ii + ',' + c.iy + ')"></b><input type="text" class="minicart_num" value="'
						+ c.ic + '"><b name="editName_' + c.ii + '" class="plus" onclick="updateCart(1,' + c.ii + ',' + c.iy + ')"></b><a target="_self" href="javascript:removeCart(' + c.ii + ',' + c.iy + ')">删除</a></div></li>'
				);
				count += parseInt(c.ic);
			}
			
			$('#minicart_list > div > div > p > span > strong').html(count);
			$('#minicart_list').parent().prev().children().html('购物车(' + count + ')');
			$('#minicart_list').show();
		});
	}

	function updateCart(num,ii,iy){
		if(iy == 6 || iy == 7)return;
		var cart = readCart();
		for(var i = 0; i < cart.length; i++){
			var c = cart[i];
			if(c.ii == ii && c.iy == iy){
				c.ic = parseInt(c.ic) + parseInt(num);
				if(c.ic <= 0){
					removeCart(c.ii, c.iy);
					showCart(1);
					return;
				}
				if(c.ic < c.limitBuyNum){c.ic = c.limitBuyNum}
			}
		}
		writeCart(cart);
		showCart(1);
	}

	function removeCart(ii, iy){
		//$('b[name="editName_' + ii + '"]').closest('li').remove();
		var cart = readCart();
		
		var cartn = [];
		var index = 0;
		for(var i = 0; i < cart.length; i++){
			if(ii == cart[i].ii && iy == cart[i].iy){
				continue;
			}
			cartn[index++] = cart[i];
		}
		$.cookie('cartItem', null, {path:'/', domain: '.111.com.cn'});
		
		if(cartn.length > 0){
			writeCart(cartn)
		}
		showCart(1);
	}

	function cleanCart(){
		$.cookie('cartItem', null, {path:'/', domain: '.111.com.cn'});
		$('.list_detail > ul').hide();
		$('.none_tips').show();
	}

	function setAddressCity(pid , o ){
//		if($.cookie('provinceId')==pid) window.location.href="http://m.111.com.cn";
		$.cookie('provinceId', pid, {path: '/', domain: '.111.com.cn', expires: 15});
		$.cookie('provinceName', o, {path: '/', domain: '.111.com.cn', expires: 15});
		window.location.href="http://m.111.com.cn";
	}


	function checkProvinceId(id){
		if(id == null)return false;
		return true;
	}

	$(document).ready(function(){
		var ck = $.cookie('provinceId');
		if(!checkProvinceId(ck)){
			$.cookie('provinceId', '1', {path: '/', domain: '.111.com.cn', expires: 30});
			$.cookie('provinceName', "上海", {path: '/', domain: '.111.com.cn', expires: 30});
		}
		
		$('.shoppingCart').mouseover(function(){
			stopPropagation(this.enent);
			showCart()
		});
		$('.shoppingCart').bind("mouseleave", function() {
			stopPropagation(this.enent);
			$('#minicart_list').hide();
		})
		
		$('#minicart_list').parent().prev().children().html('购物车(' + countCart() + ')');
		
		$('#allProvinces > dl > dd > a').each(function(){
			if($(this).attr('id').replace('p_','') == $.cookie('provinceId')){
				$('#usersProvince').html($(this).html());
			}
		});
	});

	function minusitem(e){
		var inputObject = $(e).parent().find('input');
		var oldValue = inputObject.val();
		if(!/^[0-9]+.?[0-9]*$/.test(parseInt(oldValue)) ){
			return ;
		}
		if(  parseInt(oldValue)==1){
			$("#minusitemButton").addClass("btn minu  disabled");
		}else{
			inputObject.val(parseInt(oldValue) - 1);
		}
		
	}
	function additem(e){
		var inputObject = $(e).parent().find('input');
		var oldValue = inputObject.val();
		if(!/^[0-9]+.?[0-9]*$/.test(parseInt(oldValue))){
			return ;
		}
		inputObject.val(parseInt(oldValue) + 1);
		$("#minusitemButton").removeClass("disabled");
	}
	function favoriteNotice( o ){
		$('#favoriteModal').find("h4").html(o);
	}
	function addToMyFavorite(pid){
		var url =  "http://m.111.com.cn/ucenter/addFavorite.action?pid="+pid;
		$.ajax({
			type:"post",
			url: url,
		    async: false, 
			success : function(data) {
				if (data == 1) {
					favoriteNotice("添加至个人收藏成功！");
					$('#favoriteModal').modal(null);
				} else if (data == 0) {
					favoriteNotice("参数对象不能为空");
					$('#favoriteModal').modal(null);
				} else if (data == 2) {
					favoriteNotice("商品id不能为空");
					$('#favoriteModal').modal(null);
				}else if (data == 3) {
					favoriteNotice("您已经收藏该商品");
					$('#favoriteModal').modal(null);
				} else if (data == 4) {
					favoriteNotice("商品不存在");
					$('#favoriteModal').modal(null);
				}else if (data == 5) {
					favoriteNotice("加入收藏失败");
					$('#favoriteModal').modal(null);
				}else if (data == 6) {
					favoriteNotice("系统繁忙,请稍后再试");
					$('#favoriteModal').modal(null);
				}
			}
		});
	}
	
	//收藏
	function itemFavorite(itemId){
		if(loginCheck(window.location.href)){
			addToMyFavorite(itemId);
		}
	}
	function loginCheck(backurl,status,id){
		return userLoginCheck( backurl, status, id, "");
	}


	function userLoginCheck( backurl, status, id, curl){
		if(!createUser()){
			testMessageBox(backurl);
			return false;
		}else{
			return true;
		}
		
	}
	//测试弹出
	function testMessageBox(backurl){
		window.location.href='http://passport.m.111.com.cn/sso/login.action?ReturnUrl='+backurl;
	}


	//读取用户Cookies 创建loginUser对象
	function createUser(){
	    loginUser = new Object();
	    loginUser.UserId = ppkRead("UserInfo", "UserId");//用户ID 
		loginUser.UserName = decodeURIComponent(ppkRead("UserInfo", "UserName"));//用户名
		loginUser.NickName=decodeURIComponent(ppkRead("UserInfo", "NickName"));//用户名
		loginUser.Token = ppkRead("UserInfo", "Token");//令牌号
		loginUser.Security = ppkRead("UserInfo", "Security");//加密串
		if(loginUser.UserName == null || loginUser.UserName == '' || loginUser.UserName == 'null'){
	    	return false;
	    } else {
	    	return true;
	    }
	}
	//验证用户登录Cookies
	function ppkRead(name, second) {
//	if (!second) {
//	    var nameEQ = name + "=";
//	    var ca = document.cookie.split(';');
//	    for (var i = 0; i < ca.length; i++) {
//	        var c = ca[i];
//	        while (c.charAt(0) == ' ') c = c.substring(1, c.length);
//	        if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length, c.length);
//	    }
//	    return null;
//	} else {
	    var val =$.cookie(name);
//	    alert(val);
	    if (val) {
	        var arr = val.split('&');
	        for (var i = 0, len = arr.length; i < len; i++) {
	            if (arr[i].indexOf(second) > -1) {
//	            	alert(arr[i].substring(arr[i].indexOf("=") + 1));
	                return arr[i].substring(arr[i].indexOf("=") + 1);
                }
            }
            return null;
        }
        return null;
//	    }
	}