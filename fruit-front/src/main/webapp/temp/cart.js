function checkCartAll(e, cartType) {
    var checkStatus = $(e).find("input[type=checkbox]").prop("checked");
    var markFlag = "noBuy";//标记不为要
    var fruitCodes = new Array();
    if (!checkStatus) {
        markFlag = "buy";// 标记为要
        //获取以前标记未要的goods id
        var codes = $("input[name='nobuy_" + cartType + "']");
        $.each(codes, function (i, val) {
            fruitCodes.push($(val).val());
        });
    } else {
        //获取以前标记要的goods id,标记为 不要
        var codes = $("input[name='buy_" + cartType + "']");
        $.each(codes, function (i, val) {
            fruitCodes.push($(val).val());
        });
    }
    getCartPrice("sale_price", "lang");
    var url = "http://m.sangepg.com/front/cart/mark?fruitCodes=" + fruitCodes.join(";") + "&markFlag=" + markFlag
        + "&cartGroups=" + cartGroup.join(";") + "&salePrices=" + salePrices.join(";");
    $.get(url, {}, function (returnData) {
        var begin = returnData.indexOf('<div id="main"');
        //alert(begin);
        var end = returnData.lastIndexOf('<span></span>');
        var html = returnData.substring(begin, end);
        renderCart(html);
    });
}

function checkItemStatus(e) {
    var fruitCodes = "";
    var checkObject = $(e).find("input[type=checkbox]");
    var checkStatus = false;
    $.each(checkObject,function(index,obj){
        fruitCodes = $(obj).val();
        if($(obj).prop("checked")){
            checkStatus = true;
        }
    });
    var markFlag = "noBuy";
    if (!checkStatus) {
        markFlag = "buy";
    }
    getCartPrice("sale_price", "lang");
    var url = "http://m.sangepg.com/front/cart/mark?fruitCodes=" + fruitCodes + "&markFlag=" + markFlag
        + "&cartGroups=" + cartGroup.join(";") + "&salePrices=" + salePrices.join(";");
    $.get(url, {}, function (returnData) {
        var begin = returnData.indexOf('<div id="main"');
        //alert(begin);
        var end = returnData.lastIndexOf('<span></span>');
        var html = returnData.substring(begin, end);
        renderCart(html);
    });
}
var cartGroup = null;
var salePrices = null;
function getCartPrice(name, attr) {
    cartGroup = new Array();
    salePrices = new Array();
    var codes = $("input[name='" + name + "']");
    $.each(codes, function (i, val) {
        salePrices.push($(val).val());
        cartGroup.push($(val).attr(attr));
    });
}
function moneyCheck(obj) {
    var isNum = /^\d+(\.\d+)?$/;
    var money = $(obj).val();
    if (!isNum.test(money)) {
        jAlert("请输入正确的单价");
        return;
    } else if(money >0) {
        getCartPrice("sale_price", "lang");
        var url = "http://m.sangepg.com/front/cart/count/price?cartGroups=" + cartGroup.join(";") + "&salePrices=" + salePrices.join(";");
        $.get(url, {}, function (returnData) {
            var begin = returnData.indexOf('<div id="main"');
            var end = returnData.lastIndexOf('<span></span>');
            var html = returnData.substring(begin, end);
            renderCart(html);
        });
    }
}
//渲染购物车
function renderCart(cartData) {
    var buffDom = $("<div>"), scrollTop = $("body").scrollTop();
    buffDom.html(cartData);
    $("#main").replaceWith(buffDom.html());
    $("body").scrollTop(scrollTop);
}