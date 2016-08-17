package com.sangepg.fruit.controller;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/1/17 0017.
 */
public class BaseAction {
    protected HttpServletRequest request;
    protected HttpServletResponse response;

    protected  String getCookieValue(String key){
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
    protected boolean addCookie(String key, String value, int expireHour){
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
}
