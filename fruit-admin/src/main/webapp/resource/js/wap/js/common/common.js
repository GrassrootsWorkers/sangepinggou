
/**
 * �ж��ַ����Ƿ�Ϊ��
 */
function isEmpty(str){
	if(null == str || str.replace(/[ ]/g, "").length == 0) {
		return true;
	}
	return false;
};

/**
 * �ֻ������ʽ��֤
 */
function checkMobile(no){
    var re = /13\d{9}|15\d{9}|18\d{9}|14\d{9}/;
    if (!re.test(no) || no.length != 11){
    	return false;
    }
    return true;
}

/**
 * ��ֹð���¼�
 */
function cancelBubble(e){
	if (e.stopPropagation){
		e.stopPropagation();
	}else{
		e.cancelBubble = true;
	} 
}

/**
 * ��ʽ�����(������λС��)
 */
function formatMoneyAmt(x){
	var f_x = parseFloat(x);
	if (isNaN(f_x)){
		return false;
	}
	f_x = Math.round(f_x*100) / 100;
	var s_x = f_x.toString();
	var pos_decimal = s_x.indexOf('.');
	if (pos_decimal < 0){
		pos_decimal = s_x.length;
		s_x += '.';
	}
	while (s_x.length <= pos_decimal + 2){
		s_x += '0';
	}
	return s_x;
}
