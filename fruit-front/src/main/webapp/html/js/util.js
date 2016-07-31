(function () {
    var CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".split("");
    Math.uuid = function (len, radix) {
        var chars = CHARS, uuid = [], i;
        radix = radix || chars.length;
        if (len) {
            for (i = 0; i < len; i++) {
                uuid[i] = chars[0 | Math.random() * radix]
            }
        } else {
            var r;
            uuid[8] = uuid[13] = uuid[18] = uuid[23] = "-";
            uuid[14] = "4";
            for (i = 0; i < 36; i++) {
                if (!uuid[i]) {
                    r = 0 | Math.random() * 16;
                    uuid[i] = chars[(i == 19) ? (r & 3) | 8 : r]
                }
            }
        }
        return uuid.join("")
    }
})();
function Base64() {
    _keyStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";
    this.encode = function (input) {
        var output = "";
        var chr1, chr2, chr3, enc1, enc2, enc3, enc4;
        var i = 0;
        input = _utf8_encode(input);
        while (i < input.length) {
            chr1 = input.charCodeAt(i++);
            chr2 = input.charCodeAt(i++);
            chr3 = input.charCodeAt(i++);
            enc1 = chr1 >> 2;
            enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
            enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
            enc4 = chr3 & 63;
            if (isNaN(chr2)) {
                enc3 = enc4 = 64
            } else {
                if (isNaN(chr3)) {
                    enc4 = 64
                }
            }
            output = output + _keyStr.charAt(enc1) + _keyStr.charAt(enc2) + _keyStr.charAt(enc3) + _keyStr.charAt(enc4)
        }
        return output
    };
    this.decode = function (input) {
        var output = "";
        var chr1, chr2, chr3;
        var enc1, enc2, enc3, enc4;
        var i = 0;
        input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");
        while (i < input.length) {
            enc1 = _keyStr.indexOf(input.charAt(i++));
            enc2 = _keyStr.indexOf(input.charAt(i++));
            enc3 = _keyStr.indexOf(input.charAt(i++));
            enc4 = _keyStr.indexOf(input.charAt(i++));
            chr1 = (enc1 << 2) | (enc2 >> 4);
            chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
            chr3 = ((enc3 & 3) << 6) | enc4;
            output = output + String.fromCharCode(chr1);
            if (enc3 != 64) {
                output = output + String.fromCharCode(chr2)
            }
            if (enc4 != 64) {
                output = output + String.fromCharCode(chr3)
            }
        }
        output = _utf8_decode(output);
        return output
    };
    _utf8_encode = function (string) {
        string = string.replace(/\r\n/g, "\n");
        var utftext = "";
        for (var n = 0; n < string.length; n++) {
            var c = string.charCodeAt(n);
            if (c < 128) {
                utftext += String.fromCharCode(c)
            } else {
                if ((c > 127) && (c < 2048)) {
                    utftext += String.fromCharCode((c >> 6) | 192);
                    utftext += String.fromCharCode((c & 63) | 128)
                } else {
                    utftext += String.fromCharCode((c >> 12) | 224);
                    utftext += String.fromCharCode(((c >> 6) & 63) | 128);
                    utftext += String.fromCharCode((c & 63) | 128)
                }
            }
        }
        return utftext
    };
    _utf8_decode = function (utftext) {
        var string = "";
        var i = 0;
        var c = c1 = c2 = 0;
        while (i < utftext.length) {
            c = utftext.charCodeAt(i);
            if (c < 128) {
                string += String.fromCharCode(c);
                i++
            } else {
                if ((c > 191) && (c < 224)) {
                    c2 = utftext.charCodeAt(i + 1);
                    string += String.fromCharCode(((c & 31) << 6) | (c2 & 63));
                    i += 2
                } else {
                    c2 = utftext.charCodeAt(i + 1);
                    c3 = utftext.charCodeAt(i + 2);
                    string += String.fromCharCode(((c & 15) << 12) | ((c2 & 63) << 6) | (c3 & 63));
                    i += 3
                }
            }
        }
        return string
    }
}
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) {
        return unescape(r[2])
    }
    return null
}
(function () {
    var ie = !!(window.attachEvent && !window.opera);
    var wk = /webkit\/(\d+)/i.test(navigator.userAgent) && (RegExp.$1 < 525);
    var fn = [];
    var run = function () {
        for (var i = 0; i < fn.length; i++) {
            fn[i]()
        }
    };
    var d = document;
    d.switchLinkType = function (s) {
        var activityReg = /^sale.360haoyao.com+(\/pc\/|\/m\/)/ig, homeReg = /^(www|m)+.360haoyao.com$/ig, tuanReg = /^(tuan|wxt)+.360haoyao.com$/ig, listReg = /^(www|m)+.360haoyao.com\/list\//ig, searchReg = /^(www|m)+.360haoyao.com\/+(search|search\/)/ig, itemReg = /^(www|m)+.360haoyao.com\/item\//ig, regReg = /^(login|login.m)+.360haoyao.com\/passport\/register/ig, itemTuanReg = /^(tuan|wxt)+.360haoyao.com\/item\//ig, httpReg = [/(http:\/\/)|(https:\/\/)/, /index.html.*$/, /.html.*$/, /\?q\=/, /\?.*$/, /\&.*$/, /\#.*$/, /\/$/], linkStr = s;
        for (var i = 0; i < httpReg.length; i++) {
            linkStr = linkStr.replace(httpReg[i], "")
        }
        var returnJson = {type: false, value: s.replace(/(http:\/\/)|(https:\/\/)/, "").replace(/\?.*$/, "")};
        if (activityReg.test(linkStr)) {
            returnJson.type = "c";
            returnJson.value = linkStr.replace(activityReg, "")
        } else {
            if (listReg.test(linkStr)) {
                returnJson.type = "l";
                returnJson.value = linkStr.replace(listReg, "")
            } else {
                if (searchReg.test(linkStr)) {
                    returnJson.type = "q";
                    returnJson.value = linkStr.replace(searchReg, "").replace(/%/g, "__")
                } else {
                    if (itemReg.test(linkStr)) {
                        returnJson.type = "d";
                        returnJson.value = linkStr.replace(itemReg, "")
                    } else {
                        if (itemTuanReg.test(linkStr)) {
                            returnJson.type = "td";
                            returnJson.value = linkStr.replace(itemTuanReg, "")
                        } else {
                            if (regReg.test(linkStr)) {
                                returnJson.type = "reg";
                                returnJson.value = 1
                            } else {
                                if (homeReg.test(linkStr)) {
                                    returnJson.type = "h";
                                    returnJson.value = 1
                                } else {
                                    if (tuanReg.test(linkStr)) {
                                        returnJson.type = "t";
                                        returnJson.value = 1
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return returnJson
    }
})();
function runSongshu() {
    var allDom = document.getElementsByTagName("*");
    for (var i = 0; i < allDom.length;) {
        if (allDom[i].getAttribute("isTrackDom")) {
            var trackChildren = allDom[i].getElementsByTagName("*"), trackLinks = allDom[i].getElementsByTagName("a"), trackAreas = allDom[i].getElementsByTagName("area"), parentId = allDom[i].getAttribute("id") ? allDom[i].getAttribute("id") : allDom[i].className;
            if (trackChildren.length <= 0) {
                i++
            } else {
                i = i + trackChildren.length
            }
            for (var j = 0, index = 1; j < trackLinks.length; j++) {
                var link = trackLinks[j], url = link.getAttribute("href");
                if (/^(http:|https:)/.test(url)) {
                    var linkType = document.switchLinkType(url);
                    spm = false;
                    if (linkType.type) {
                        spm = parentId + "-" + index + "-" + linkType.type + "-" + linkType.value
                    } else {
                        spm = parentId + "-" + index + "-other-" + linkType.value
                    }
                    link.setAttribute("spm", spm.replace(/ /g, ""));
                    index++
                }
            }
            for (var j = 0; j < trackAreas.length; j++) {
                var link = trackAreas[j], url = link.getAttribute("href");
                if (/^(http:|https:)/.test(url)) {
                    var linkType = document.switchLinkType(url);
                    spm = false;
                    if (linkType.type) {
                        spm = parentId + "-" + index + "-" + linkType.type + "-" + linkType.value
                    } else {
                        spm = parentId + "-" + index + "-other-" + linkType.value
                    }
                    link.setAttribute("spm", spm.replace(/ /g, ""));
                    index++
                }
            }
        } else {
            i++
        }
    }
};