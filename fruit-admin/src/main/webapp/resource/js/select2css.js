

var selects = document.getElementsByTagName('select');

var isIE = (document.all && window.ActiveXObject && !window.opera) ? true : false;

function $(id) {
	return document.getElementById(id);
}

function stopBubbling (ev) {
	ev.stopPropagation();
}

function rSelects() {
	for (i=0;i<selects.length;i++){
		selects[i].style.display = 'none';
		select_tag = document.createElement('div');
		select_tag.id = 'select_' + selects[i].name;
		select_tag.className = 'select_box';
		selects[i].parentNode.insertBefore(select_tag,selects[i]);

		select_info = document.createElement('div');
		select_info.id = 'select_info_' + selects[i].name;
		select_info.className='tag_select';
		select_info.style.cursor='pointer';
		select_tag.appendChild(select_info);

		select_ul = document.createElement('ul');
		select_ul.id = 'options_' + selects[i].name;
		select_ul.className = 'tag_options';
		select_ul.style.position='absolute';
		select_ul.style.display='none';
		select_ul.style.zIndex='999';
		select_tag.appendChild(select_ul);

		rOptions(i,selects[i].name);

		mouseSelects(selects[i].name);

		if (isIE){
			selects[i].onclick = new Function("clickLabels3('"+selects[i].name+"');window.event.cancelBubble = true;");
		}
		else if(!isIE){
			//selects[i].onclick = new Function("clickLabels3('"+selects[i].name+"')");
			selects[i].addEventListener("click", stopBubbling, false);
		}
	}
}

/**
 * 支持联动的方法
 * 
 * @param i 第几个标签
 * @param name 标签的id或name值
 */
function rOptions_father_and_son(i, name, text) {
	var select_obj = document.getElementById(name);
	
	var options_ul = 'options_' + name;
	jQuery("#options_" + name).html("");
	jQuery('#select_info_' + name).html("--请选择"+text+"--");
	for (n=0;n<select_obj.options.length;n++){
		option_li = document.createElement('li');
		option_li.style.cursor='pointer';
		option_li.className='open';
		$(options_ul).appendChild(option_li);
		
		option_text = document.createTextNode(select_obj.options[n].text);
		option_li.appendChild(option_text);
		
		option_selected = select_obj.options[n].selected;

		if(option_selected){
			option_li.className='open_selected';
			option_li.id='selected_' + name;
			jQuery('#select_info_' + name).html("");
			$('select_info_' + name).appendChild(document.createTextNode(option_li.innerHTML));
			jQuery('#select_info_' + name).attr("lang", select_obj.options[n].value);
		}
		
		option_li.onmouseover = function(){	this.className='open_hover';}
		option_li.onmouseout = function(){
			if(this.id=='selected_' + name){
				this.className='open_selected';
			}
			else {
				this.className='open';
			}
		}
		
		//add by wqw 把select数据中的id放到新样式中
		option_li.value = select_obj.options[n].value;
		
		option_li.onclick = new Function("clickOptions("+i+","+n+",'"+select_obj.name+"','"+option_li.value+"')");
	}
}

function rOptions(i, name) {
	var options = selects[i].getElementsByTagName('option');
	
	var options_ul = 'options_' + name;
	for (n=0;n<selects[i].options.length;n++){
		option_li = document.createElement('li');
		option_li.style.cursor='pointer';
		option_li.className='open';
		$(options_ul).appendChild(option_li);
		
		option_text = document.createTextNode(selects[i].options[n].text);
		option_li.appendChild(option_text);
		
		option_selected = selects[i].options[n].selected;
		
		if(option_selected){
			option_li.className='open_selected';
			option_li.id='selected_' + name;
			$('select_info_' + name).appendChild(document.createTextNode(option_li.innerHTML));
		}
		
		option_li.onmouseover = function(){	this.className='open_hover';}
		option_li.onmouseout = function(){
			if(this.id=='selected_' + name){
				this.className='open_selected';
			}
			else {
				this.className='open';
			}
		}
		
		//add by wqw 把select数据中的id放到新样式中
		option_li.value = selects[i].options[n].value;
		
		option_li.onclick = new Function("clickOptions("+i+","+n+",'"+selects[i].name+"','"+option_li.value+"')");
	}
}

function mouseSelects(name){
	var sincn = 'select_info_' + name;

	$(sincn).onmouseover = function(){ if(this.className=='tag_select') this.className='tag_select_hover'; }
	$(sincn).onmouseout = function(){ if(this.className=='tag_select_hover') this.className='tag_select'; }

	if (isIE){
		$(sincn).onclick = new Function("clickSelects('"+name+"');window.event.cancelBubble = true;");
	}
	else if(!isIE){
		$(sincn).onclick = new Function("clickSelects('"+name+"');");
		$('select_info_' +name).addEventListener("click", stopBubbling, false);
	}

}

function clickSelects(name){
	var sincn = 'select_info_' + name;
	var sinul = 'options_' + name;

	for (i=0;i<selects.length;i++){
		if(selects[i].name == name){
			if( $(sincn).className =='tag_select_hover'){
				$(sincn).className ='tag_select_open';
				$(sinul).style.display = '';
			}
			else if( $(sincn).className =='tag_select_open'){
				$(sincn).className = 'tag_select_hover';
				$(sinul).style.display = 'none';
			}
		}
		else{
			$('select_info_' + selects[i].name).className = 'tag_select';
			$('options_' + selects[i].name).style.display = 'none';
		}
	}

}

function clickOptions(i, n, name, value){
	var li = $('options_' + name).getElementsByTagName('li');

	$('selected_' + name).className='open';
	$('selected_' + name).id='';
	li[n].id='selected_' + name;
	li[n].className='open_hover';
	$('select_' + name).removeChild($('select_info_' + name));

	select_info = document.createElement('div');
	select_info.id = 'select_info_' + name;
	select_info.className='tag_select';
	select_info.style.cursor='pointer';
	$('options_' + name).parentNode.insertBefore(select_info,$('options_' + name));
	
	select_info.lang = value;//在div属性中加入lang，存放数据id;

	mouseSelects(name);

	$('select_info_' + name).appendChild(document.createTextNode(li[n].innerHTML));
	$( 'options_' + name ).style.display = 'none' ;
	$( 'select_info_' + name ).className = 'tag_select';
	
//	selects[i].options[n].selected = 'selected'; 暂时不知道这块有什么用处，行业和地区有冲突，暂时注释掉

	//add by wqw 联动
	if (name == "province") { //省市联动
		jQuery('[ms-duplex-string="city"]').find("option").remove();
		jQuery('.city_data option').each(function() {
			if (jQuery(this).attr("top_city_id").trim() == value.trim()) {
				jQuery('[ms-duplex-string="city"]').append(jQuery(this).clone());
			} else {
			}
		});
		rOptions_father_and_son(i+1, "city", "市");
	} else if (name == "fathercat") { //行业联动
//		jQuery("#soncat").load("/category/loadSonCat.do?catFatherId="+value.trim(), function() {});
		
		//重新将二级行业的多选框复现出来，让selectlist取值
		jQuery("#secondcat").remove();
		var soncat_html = "<select ms-duplex-string='cat' id='secondcat' name='secondcat'></select>";
		jQuery("#soncat").html(soncat_html);
		
		//jQuery('[ms-duplex-string="cat"]').find("option").remove();
		jQuery('.cat_data option').each(function() {
			if (jQuery(this).attr("top_cat_id").trim() == value.trim()) {
				jQuery('[ms-duplex-string="cat"]').append(jQuery(this).clone());
			} else {
			}
		});
		jQuery("#select_secondcat").remove();
		origSelOjbs = jQuery('#secondcat').selectlist();
		if(value != "-1") {
			hide_error_span("category", "father_category_error", "select");
		}
		//rOptions_father_and_son(i+1, "myMulSelect", "第二行业");
	} else if (name.trim() == "tradtypeshow") {//交易类型联动
		if (value.trim() == 2) {
			jQuery("#show_iscontrolpostion_box").removeClass("stock_none");
		} else {
			jQuery("#is_iscontrolpostion_check").attr("class", "");
			jQuery("#show_iscontrolpostion_box").addClass("stock_none");
			jQuery("#iscontrolpostion").val("");
		}
		//提示的错误，选择正确，取消红色提示
		if(value != "-1") {
			hide_error_span("tradtype", "tradtype_error", "select");
		}
	} else if (name.trim() == "receive_apply_time") {//接收到洽谈的时间
		query_receive();
	} else if (name.trim() == "send_apply_time") {//申请的洽谈的时间
		query_send();
	} else if (name.trim() == "submit_time") {//我投递过的需求列表中的时间条件
		query();
	} else if (name.trim() == "select_group") {//分组下拉框
		jQuery.ajax({
	        type: "post",
	        url: "/friends/friendChangeGroup",
	        data: {
	        	groupid : value,
	        	id : jQuery("#select_group_id").val(),
	        	groupname : li[n].innerHTML
	        },
	        success: function (result) {
	        	jQuery("#no_group_friends_list").load("/friends/no_group_friends_list");
	        	jQuery("#left_friends_group").load("/friends/leftGroupList");
	        }
	    });
	}
}

window.onload = function(e) {
	bodyclick = document.getElementsByTagName('body').item(0);
	rSelects(); 
	bodyclick.onclick = function(){
		for (i=0;i<selects.length;i++){
			$('select_info_' + selects[i].name).className = 'tag_select';
			$('options_' + selects[i].name).style.display = 'none';
		}
	}
}