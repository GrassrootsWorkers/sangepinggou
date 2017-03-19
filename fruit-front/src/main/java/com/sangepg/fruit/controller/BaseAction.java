package com.sangepg.fruit.controller;


import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/1/17 0017.
 */
public class BaseAction {

    protected ModelAndView loginModel(){
        RedirectView redirectView = new RedirectView("http://m.sangepg.com/user/user_login.html");
        ModelAndView view = new ModelAndView(redirectView);
        return view;
    }
    protected  Map<String,BigDecimal> getPriceMapFromStrs(String keys, String doubleStr){
        String[] cartGroup = keys.split(";");
        String[] salePrice = doubleStr.split(";");
        Map<String,BigDecimal> groupMapPrice = new HashMap<>();
        for(int i=0;i<cartGroup.length;i++){
            groupMapPrice.put(cartGroup[i],new BigDecimal(salePrice[i]));
        }
        return groupMapPrice;
    }
    protected  String getCookieValue(String key,HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if(cookies == null) return null;
        String value = null;
        for (Cookie c : cookies) {
            if (c.getName().equals(key)) {
                value =  c.getValue();
            }
        }
        return value;
    }

    /**
     * 获得用户的真实IP
     *
     * @return 用户的真实IP
     */
    protected String getRealIP(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip=request.getHeader("x-real-ip");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
    protected boolean addCookie(String key, String value, int expireHour,HttpServletResponse response){
		try {
			value = URLEncoder.encode(value,"UTF-8");
			Cookie userNameCookie = new Cookie(key, value);
			userNameCookie.setPath("/");
			if(expireHour >0){
				userNameCookie.setMaxAge(expireHour * 60 * 60);
			}
			response.addCookie(userNameCookie);
			return true;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return false;
	}


    public boolean validateMobile(String mobile){
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(17[0-9])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(mobile);
        return m.matches();
    }
    public Map<String,Object> successMsg(){
        Map<String,Object> msg = new HashMap<>();
        msg.put("msg", "success");
        msg.put("code",200);
        return msg;
    }
    public Map<String,Object> errorMsg(){
        Map<String,Object> msg = new HashMap<>();
        msg.put("msg", "error");
        msg.put("code",-1);
        return msg;
    }
}
