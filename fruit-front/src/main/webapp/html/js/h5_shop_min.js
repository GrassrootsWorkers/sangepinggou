function cb() {
}
function callback() {
}
function isApp() {
    var ua = navigator.userAgent.toLowerCase();
    if (ua.indexOf("youyi") > 0) {
        return true
    }
    return false
}
function getappos() {
    var ua = navigator.userAgent.toLowerCase();
    if (ua.indexOf("android") > 0) {
        return "android"
    }
    if (ua.indexOf("iphone") > 0) {
        return "ios"
    }
    return "h5"
}
var nutflag = 0;
var hostor = window.location.host;
var plat = "shop";
var src = "h5";
var host = "." + hostor;
if (host.indexOf("360jk.com") > -1) {
    host = ".360jk.com"
}
if (host.indexOf("360haoyao.com") > -1) {
    host = ".360haoyao.com"
}
if (hostor.indexOf("wx.360haoyao.com") > -1 || hostor.indexOf("wxt.360haoyao.com") > -1) {
    plat = "shop";
    src = "wxt"
}
if (hostor.indexOf("tuan.360haoyao.com") > -1 || hostor.indexOf("pt.360haoyao.com") > -1) {
    plat = "shop";
    src = "pct"
}
var statstr = ["0", "0", "0", "0", "0", "0", "0", "0", "0"];
var curl = document.location.href;
var pt = getPageType(curl);
statstr[0] = pt;
var appos = null;
try {
    var uid = (jQuery.cookie("uid"));
    if (getUrlParam("uid") != null) {
        uid = getUrlParam("uid");
        jQuery.cookie("uid", uid, {path: "/", domain: host, expires: 1})
    }
    if (isApp()) {
        if (jQuery.cookie("uuid") != null) {
            uid = (jQuery.cookie("uuid"))
        }
        appos = getappos()
    }
    var tracker_u;
    var cookie_tr = (jQuery.cookie("tracker_u"));
    var url_tr = getUrlParam("tracker_u");
    var vid = (jQuery.cookie("vid"));
    var user_id = (jQuery.cookie("JUD"));
    var rf = document.referrer;
    var tcpos1 = getUrlParam("tcpos1");
    if (tcpos1 != null) {
        jQuery.cookie("tcpos1", tcpos1, {path: "/", domain: host, expires: 1 / 2})
    }
    tcpos1 = (jQuery.cookie("tcpos1"));
    var sendurl = "";
    var send_str = "";
    if (curl.indexOf("file:") > -1) {
        src = "none"
    }
    if (user_id == null) {
        user_id = getUrlParam("JUD");
        jQuery.cookie("JUD", user_id, {path: "/", domain: host, expires: 1})
    }
    if (url_tr != null && url_tr != "") {
        url_tr = url_tr.replace("/", "");
        cookie_tr = url_tr;
        jQuery.cookie("tracker_u", url_tr, {path: "/", domain: host, expires: 1})
    }
    if (cookie_tr == null && url_tr == null && rf != "") {
        if (rf.indexOf("baidu") > -1) {
            cookie_tr = "naturebaidu"
        } else {
            if (rf.indexOf("soso") > -1) {
                cookie_tr = "naturesoso"
            } else {
                if (rf.indexOf("www.haosou.com") > -1 || rf.indexOf("www.so.com") > -1) {
                    cookie_tr = "naturehaosou"
                } else {
                    if (rf.indexOf("sogou") > -1) {
                        cookie_tr = "naturesougou"
                    } else {
                        if (rf.indexOf("hao.360.cn") > -1) {
                            cookie_tr = "hao360"
                        } else {
                            if (hostor.indexOf("jiankang.360jk.com/news") > -1) {
                                cookie_tr = "qihoo"
                            } else {
                                if (rf.indexOf("cn.bing.com") > -1) {
                                    cookie_tr = "naturebing"
                                } else {
                                    if (rf.indexOf("360haoyao.com") <= -1 && cookie_tr == null) {
                                        cookie_tr = "natureother"
                                    } else {
                                        if (rf.indexOf("360haoyao.com") >= -1 && cookie_tr == null && url_tr == null) {
                                            cookie_tr = "direct"
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        jQuery.cookie("tracker_u", cookie_tr, {path: "/", domain: host, expires: 1})
    }
    if (cookie_tr == null && url_tr == null && rf == "") {
        if (hostor.indexOf("jiankang.360jk.com/news") > -1 || hostor.indexOf("qihoo.com") > -1 || rf.indexOf("qihoo.com") > -1 || rf.indexOf("jiankang.360jk.com/news") > -1) {
            jQuery.cookie("tracker_u", "qihoo", {path: "/", domain: host, expires: 1});
            cookie_tr = "qihoo"
        } else {
            if (hostor.indexOf("yiliaojiankang.html") > -1 || hostor.indexOf("jiankang.360jk.com/hao360") > -1 || rf.indexOf("jiankang.360jk.com/hao360") > -1 || rf.indexOf("yiliaojiankang.html") > -1) {
                jQuery.cookie("tracker_u", "pc_360_jkdh", {path: "/", domain: host, expires: 1});
                cookie_tr = "pc_360_jkdh"
            } else {
                cookie_tr = "direct";
                jQuery.cookie("tracker_u", "direct", {path: "/", domain: host, expires: 1})
            }
        }
    }
    if (uid == null) {
        uid = getUrlParam("uid");
        jQuery.cookie("uid", uid, {path: "/", domain: host, expires: 1})
    }
    if (vid == null) {
        vid = getUrlParam("vid");
        jQuery.cookie("vid", vid, {path: "/", domain: host, expires: 1})
    }
    if (vid == null) {
        vid = "U" + Math.uuid(4, 16) + new Date().getTime();
        jQuery.cookie("vid", vid, {path: "/", domain: host, expires: 1})
    }
    if (uid == null) {
        uid = "U" + Math.uuid(4, 16) + new Date().getTime();
        jQuery.cookie("uid", uid, {path: "/", domain: host, expires: 1})
    }
    tracker_u = cookie_tr;
    curl = curl.replace(/&/g, "---");
    rf = rf.replace(/&/g, "---");
    send_str = "uid=" + uid + "&src=" + src + "&plat=" + plat + "&user_id=" + user_id + "&refer=" + rf + "&vid=" + vid + "&page_info=" + curl + "&tracker_u=" + tracker_u + "&tcpos1=" + tcpos1 + "&appos=" + appos;
    if (navigator.userAgent.indexOf("MSIE") >= 0) {
        var uidtrackeru = jQuery.ajax({
            url: "http://stat.360haoyao.com/?jkip=mm&tracker_u=" + cookie_tr,
            async: true,
            dataType: "jsonp",
            jsonpCallback: "callback",
            jsonp: "callback",
            success: function (data) {
                if (navigator.userAgent.indexOf("7") < 0) {
                    uid = data.split("||")[0];
                    tracker_u = (data.split("||")[1])
                } else {
                }
                vid = uid;
                send_str = "uid=" + uid + "&src=" + src + "&plat=" + plat + "&user_id=" + user_id + "&refer=" + rf + "&vid=" + vid + "&page_info=" + curl + "&tracker_u=" + tracker_u + "&tcpos1=" + tcpos1 + "&appos=" + appos;
                sendStatFunc(send_str, "none")
            }
        })
    } else {
        sendStatFunc(send_str, "none")
    }
    $(document).ready(function () {
        $("body").on("click", function (e) {
            var e = e || window.event;
            var posx = e.clientX + document.body.scrollLeft + document.documentElement.scrollLeft;
            var posy = e.clientY + document.body.scrollTop + document.documentElement.scrollTop;
            var bottomw = document.body.clientWidth;
            var bottomh = document.body.clientHeight;
            var pwidth = screen.width;
            var pheight = screen.height;
            var xy = posx + "_" + posy + "_" + pwidth + "_" + pheight + "_" + bottomw + "_" + bottomh;
            send_str = "uid=" + uid + "&src=" + src + "&plat=" + plat + "&user_id=" + user_id + "&refer=" + rf + "&tracker_u=" + tracker_u + "&vid=" + vid + "&page_info=" + curl + "&xy=" + xy + "&appos=" + appos;
            if (navigator.userAgent.indexOf("MSIE") >= 0) {
                var uidtrackeru = jQuery.ajax({
                    url: "http://stat.360haoyao.com/?jkip=mm&tracker_u=" + cookie_tr,
                    async: true,
                    dataType: "jsonp",
                    jsonpCallback: "callback",
                    jsonp: "callback",
                    success: function (data) {
                        if (navigator.userAgent.indexOf("7") < 0) {
                            uid = data.split("||")[0];
                            tracker_u = (data.split("||")[1])
                        } else {
                        }
                        vid = uid;
                        send_str = "uid=" + uid + "&src=" + src + "&plat=" + plat + "&user_id=" + user_id + "&refer=" + rf + "&tracker_u=" + tracker_u + "&vid=" + vid + "&page_info=" + curl + "&xy=" + xy + "&appos=" + appos;
                        sendStatFunc(send_str, "point")
                    }
                })
            } else {
                sendStatFunc(send_str, "point")
            }
        });
        runSongshu();
        $("body").on("click", "a,area,input", function (e) {
            if (nutflag == 0) {
                runSongshu()
            }
            nutflag = 1;
            var e = e || window.event;
            var pt_pos_lid = $(this).attr("pt_pos_lid");
            var spm = $(this).attr("spm");
            var tcpos = jQuery.cookie("tcpos");
            if (tcpos == null || tcpos.indexOf("--") < 0) {
                tcpos = statstr.join("--")
            }
            statstr = tcpos.split("--");
            if (pt_pos_lid != null) {
                statstr[2] = pt_pos_lid
            }
            statstr[0] = pt;
            var parr = ["0", "0", "0", "h", "q", "s", "l", "p", "m"];
            if (spm != null) {
                $.each(parr, function (i, val) {
                    if (pt == val) {
                        if (pt == "s") {
                            var activityReg = /http:\/(\/)?sale.360haoyao.com+(\/pc\/|\/m\/)([0-9]+)\//g;
                            pattern = new RegExp(activityReg);
                            var res = pattern.exec(curl);
                            if (res != null) {
                                saleid = res[3]
                            }
                            statstr[i] = saleid + "-" + spm
                        } else {
                            statstr[i] = spm
                        }
                    }
                })
            }
            jQuery.cookie("tcpos", statstr.join("--"), {path: "/", domain: host, expires: 1});
            send_str = "uid=" + uid + "&src=" + src + "&plat=" + plat + "&user_id=" + user_id + "&refer=" + rf + "&tracker_u=" + tracker_u + "&page_info=" + curl + "&vid=" + vid + "&pt_pos_lid=" + pt_pos_lid + "&tcpos=" + statstr.join("--") + "&appos=" + appos;
            if (navigator.userAgent.indexOf("MSIE") >= 0) {
                var uidtrackeru = jQuery.ajax({
                    url: "http://stat.360haoyao.com/?jkip=mm&tracker_u=" + cookie_tr,
                    async: true,
                    dataType: "jsonp",
                    jsonpCallback: "callback",
                    jsonp: "callback",
                    success: function (data) {
                        try {
                            uid = data.split("||")[0];
                            tracker_u = (data.split("||")[1]);
                            vid = uid;
                            send_str = "uid=" + uid + "&src=" + src + "&plat=" + plat + "&user_id=" + user_id + "&refer=" + rf + "&tracker_u=" + tracker_u + "&page_info=" + curl + "&vid=" + vid + "&pt_pos_lid=" + pt_pos_lid + "&tcpos=" + statstr.join("--") + "&appos=" + appos;
                            sendStatFunc(send_str, "click")
                        } catch (ere) {
                        }
                    }
                })
            } else {
                sendStatFunc(send_str, "click")
            }
        })
    });
    var start = -1;
    var starttime = 0;
    var endtime = 0;
    var mflag = 0;
    var fisrvisit = 0;
    $(window).scroll(function () {
        endtime = new Date().getTime();
        if (mflag == 0) {
            starttime = new Date().getTime()
        }
        mflag = 1;
        var cur = $(window).scrollTop();
        var move = start - cur;
        if (endtime - starttime > 1000 || fisrvisit == 0) {
            send_str = "uid=" + uid + "&src=" + src + "&plat=" + plat + "&user_id=" + user_id + "&refer=" + rf + "&vid=" + vid + "&page_info=" + curl + "&tracker_u=" + tracker_u + "&tcpos1=" + tcpos1 + "&scroll=" + move + "&appos=" + appos;
            sendStatFunc(send_str, "scroll");
            mflag = 0
        }
        start = cur;
        fisrvisit = 1
    })
} catch (err) {
    console.log(err)
}
function sendStatFunc(send_str, eventname) {
    try {
        var encode = jQuery.base64.encode;
        send_str = send_str + "&event=" + eventname;
        send_str = encode(send_str);
        sendurl = "http://stat.360haoyao.com/?nut=" + send_str;
        jQuery.ajax({type: "get", url: sendurl, async: true, dataType: "jsonp", jsonpCallback: "cb"})
    } catch (e1) {
        console.log(e1)
    }
}
function im_destUserInfo(userInfo) {
    try {
        var tcpos = jQuery.cookie("tcpos");
        if (tcpos == null || tcpos.indexOf("--") < 0) {
            tcpos = statstr.join("--")
        }
        statstr = tcpos.split("--");
        statstr[0] = pt;
        statstr[1] = userInfo.id;
        send_str = send_str + "&nengid=" + userInfo.id + "&tcpos=" + statstr.join("--");
        jQuery.cookie("tcpos", statstr.join("--"), {path: "/", domain: host, expires: 1});
        sendStatFunc(send_str, "click")
    } catch (e2) {
        console.log(e2)
    }
}
function getPageType(pageurl) {
    var pt = "x";
    if (pageurl.indexOf("www.360haoyao.com/?") > -1 || pageurl == "http://www.360haoyao.com/" || pageurl == "http://www.360haoyao.com" || pageurl.indexOf("www.360haoyao.com?") > -1 || pageurl == "http://m.360haoyao.com/" || pageurl.indexOf("m.360haoyao.com?") > -1) {
        pt = "h"
    } else {
        if (pageurl.indexOf("360haoyao.com/list/") > -1) {
            pt = "l"
        } else {
            if (pageurl.indexOf("www.360haoyao.com/search") > -1 || pageurl.indexOf("search.m.360haoyao.com/") > -1) {
                pt = "q"
            } else {
                if (pageurl.indexOf("360haoyao.com/item/") > -1) {
                    pt = "p"
                } else {
                    if (pageurl.indexOf("tuan.360haoyao.com") > -1 || pageurl.indexOf("wxt.360haoyao.com") > -1 || pageurl.indexOf("pt.360haoyao.com") > -1 || pageurl.indexOf("wx.360haoyao.com") > -1) {
                        pt = "t"
                    } else {
                        if (pageurl.indexOf("360haoyao.com/store") > -1) {
                            pt = "r"
                        } else {
                            if (pageurl.indexOf("www.360jk.com") > -1 || pageurl.indexOf("m.360jk.com") > -1) {
                                pt = "j"
                            } else {
                                if (pageurl.indexOf("tj.360jk.com") > -1) {
                                    pt = "tj"
                                } else {
                                    if (pageurl.indexOf("360haoyao.com/cart") > -1) {
                                        pt = "c"
                                    } else {
                                        if (pageurl.indexOf("register.action") > -1) {
                                            pt = "reg"
                                        } else {
                                            if (pageurl.indexOf("sale.360haoyao.com") > -1) {
                                                pt = "s"
                                            } else {
                                                if (pageurl.indexOf("user.360haoyao.com") > -1 || pageurl.indexOf("user.m.360haoyao.com") > -1) {
                                                    pt = "u"
                                                } else {
                                                    if (pageurl.indexOf("login.360haoyao.com") > -1 || pageurl.indexOf("login.m.360haoyao.com") > -1) {
                                                        pt = "g"
                                                    } else {
                                                        if (pageurl.indexOf("buyImmediately") > -1 || pageurl.indexOf("orderSure") > -1 || pageurl.indexOf("fastBuyOrder") > -1 || pageurl.indexOf("initOrder") > -1 || pageurl.indexOf("initPrescriptOrder") > -1) {
                                                            pt = "o"
                                                        } else {
                                                            if (pageurl.indexOf("onlySuccess") > -1 || pageurl.indexOf("orderfinish") > -1 || pageurl.indexOf("orderSuccess.action") > -1 || pageurl.indexOf("prescriptOrderSuccess") > -1) {
                                                                pt = "e"
                                                            } else {
                                                                pt = "m"
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    return pt
};