var provinceSite = {1: "2,30,28,27,26,17,16,11,29,3,4,32,8,10,9", 2: "1,5,6,13", 3:"18,14,25,24,23,12,7,22,15,20,19,21"};
function getSite(){
		var ck = jQuery.cookie('provinceId');
		for(var i in provinceSite){
			if(provinceSite[i].split(',').indexOf(ck) != -1){
				return i;
			}
		}
}

function strToJson(str){  
    var json = eval('(' + str + ')');  
    return json;  
}   
var myStock = false, sdata_ = {};
var sltVal = '';
var x;
function loadStockAndSpecialAttr(itemId,productno){
	jQuery.ajax({
	  type:"post",
	  async: false, 
	  url: 'http://m.111.com.cn/interfaces/querySpecialItemByItemid.action?id='+itemId+'&areaId='+getSite(),
	  success: function(data){
		    if(data!=""){
			  data=strToJson(data);  
			}
			var productnos = '', allproductnos = '';
			x = data;
			for(var i in x){
	            if(i == 'selected')continue;
	            for(var k = 0; k < x[i].length; k++){
	                var pno = x[i][k].match(/\^(.*)\^(.*)\^/)[2];
	                allproductnos += pno+',';
	                if(productnos.indexOf(pno) != -1)continue;
	                productnos += (productnos ? ',' : '') + pno;
	            }
			}
			var prvid = jQuery.cookie('provinceId');
			if(prvid=="")  prvid='1';
			loadStockData(productnos,productno,prvid,data,allproductnos);
	  }
	}); 
}
function loadStockData(productnos,productno,prvid,data,allproductnos){
	// 库存、特殊属性
	var defaultContent="";
	productnos = (productnos == '' ? productno : productnos);
	var url = "http://m.111.com.cn/gss/interfaces/stock/queryStockToJson.action?province=" + prvid + "&productNos=" + productnos+ "&callback=?";
		// 处理当前商品及关联商品库存、套餐库存
	jQuery.ajax({
		   type: "post",
		   async: false, 
		   dataType: "json",
		   url: url,
		   success: function(sdata){
            sdata_ = sdata;
            var index = index2 = 0;
        	var html = '<div class="size_list"><b class="center-block gray">';
		  	myStock = sdata[productno] > 0;
		  	if(myStock){
				$('#seriesCartButton').attr('class', 'btn btn-danger btn-block');
		  		$('#seriesCartButton').text('加入购物车');
		  	}
            // 特殊属性
		  	if(!productnos)return;
		  	for(var k in data){
		  		if(k=='selected')break;
		  		// 判断是否显示图片
		  		var isPic = data[k][0].split('^')[3] != 'null';
		  		var name = (k.length == 2?(k.split('')[0] + '　　' + k.split('')[1]):k);
	  			html+=getHtml(name,index,data.length-1);
	  			defaultContent += name ;
	  			if(data.length-1!=index){
	  				defaultContent+="/";
	  			}
		  		for(v = 0; v < x[k].length; v++){
		  			var value_att = x[k][v];
		  			var isSelect = false;
		  			var no_ = allproductnos.split(',')[index2];
		  			var hasStock = sdata[no_] > 0;
		  			// 已选择
		  			var slet_ = x['selected'][index].split('^')[1];
		  			if(value_att.split('^')[0] == slet_){
		  				isSelect = true;
		  			}
		  			html += getLi(value_att, isSelect, isPic, hasStock, v );
		  			index2++;
		  		}
		  		index++;
		  		html += '</ul></dd></dl>';
		  	}
		  	$('#propery_item').html(html);
		  	if(x){
		  		var sel = x['selected'];
			  	for(var i = 0; i < sel.length; i++){
			  		sltVal += sel[i].split('^')[1];
			  		if(i!=sel.length-1){
		  				sltVal+="/";
		  			}
			  	}
			  	setSelectedClass(sltVal,sltVal==""?defaultContent:"");
			}
		  	if(sltVal!=""){
		  		$("#specialDiv").show();	
		  	}else{
		  		$("#specialDiv").hide();
		  	}
		  	 
	  }
	});
}
function setSelectedClass(sltVal,defaultContent){
	var o = $('div[class="row mb10"]');	
	if(sltVal!=null && sltVal!=""){
		o.find("span[class='gray']").text("您选择了:");
		o.find("b").html(sltVal);
		$("#propery_item").removeClass("in");
		$("#propery_item").addClass("collapse");
		$("#propery_item").attr("style","height:20px");
		o.find("button").removeClass("hidden");
		o.find("button").addClass("collapsed");
	}else{
		o.find("span[class='gray']").text("请选择:");
		o.find("b").html(defaultContent);
	}
}
function getHtml(name,index ,count){
	var html="";
	if(index!=0 && index!=count){
		html += '</div><div class="size_list"><b class="center-block gray">' ;
	}
	if(index==count){
		html += "</div>";
	}
	html += name + "：</b>";
	return html;
}
function getLi(li, isSelect, isPic, hasStock, index){
	var rel = '';
	var vals = li.split('^');
	var pic = vals[3];
	var itemid = vals[1];
	var pname = vals[0];
	var slt = ' class="';
	var url = 'http://m.111.com.cn/product/'+itemid+'.html';
	slt += isSelect?'selected':'';
	slt += hasStock?'':' disabled';
	slt += '"';
	if(hasStock){
		rel += ' <a href="'+url+'"' ;
	}else{
		rel += ' <a href="javascript:void(0);"' ;
	}
	rel += ' <a href="'+url+'"' ;
	rel += slt;
	if(isPic){
		rel += '><img width="43" height="43" title="' + pname + '" id="color_' + pname + '" src="' + pic + '"/>';
	} else {
		rel += '>'+pname;
	}
	if(index%5 == 5){
		rel += "<i></i>";
	}
	rel += '</a>';
	return rel;
}