package com.farmer.fruit.sgpg.controller.base;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.farmer.fruit.interfaces.farmer.IFarmerService;
import com.farmer.fruit.models.farmer.Farmer;
import com.farmer.fruit.models.farmer.FarmerQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Created by Administrator on 2016/1/17 0017.
 */
public class BaseAction {
  
	public static final String VALIDATE_CODE = "validateCode";
	public static final String CURRENT_USER = "ID";
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    @Autowired
    protected IFarmerService farmerService;

    protected ModelAndView returnLoginHtml(){
        RedirectView redirectView = new RedirectView("/jsp/user/user_login.html");
        ModelAndView view = new ModelAndView(redirectView);
        return view;
    }
    protected Farmer getLoginFarmer(HttpServletRequest request){
        this.request = request;
        String mobile = this.getCookieValue("qr_un");
        FarmerQuery farmerQuery = new FarmerQuery();
        farmerQuery.setMobile(mobile);
        Farmer farmer = farmerService.get(farmerQuery);
        return farmer;
    }
    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public Long getFarmerId(String mobile){
        Long farmerId = (Long)this.request.getSession().getAttribute(mobile);
        return farmerId;
    }
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
    
    protected String[] formatLabels(String labels){
        if(StringUtils.isEmpty(labels)){
            return null;
        }
        String[] labelArray = labels.split("\\{#\\}");
        String[] labelList = new String[labelArray.length];

        for(int i = 0 ;i<= labelArray.length-1;i++){
            String[] result = labelArray[i].split("=");
            try{
                labelList[i] = result[1];
            }catch (Exception e){}

        }
        return labelList;
    }
    public boolean validateCode(String key,String destCode){
        String code = (String)request.getSession().getAttribute(key);
        return destCode.equalsIgnoreCase(code);
    }

    private  int daysBetween(Date smdate, Date bdate) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        smdate=sdf.parse(sdf.format(smdate));
        bdate=sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days=(time2-time1)/(1000*3600*24);

        return Integer.parseInt(String.valueOf(between_days));
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
    public String formatMobile(String mobile){
        String s = mobile.substring(3,7);
        return mobile.replace(s,"****");
    }
    protected boolean recordLoginTrack( int userId,String userName,String type){
 		//cookie
		//记录userId bg_ui
		//记录userName
		//记录username
		int expireHour = ReloadableConfig.getIntProperty("session_expire_time");
		String userIdKey = ReloadableConfig.getStringProperty("cookie_user_id","qr_id");
		addCookie(userIdKey,userId+"_"+type,expireHour);
		
		String userNameKey =ReloadableConfig.getStringProperty("cookie_user_name","qr_nm");
		addCookie(userNameKey,userName,expireHour);
		//redis
		//记录 registered_1861810269
		//服务器端登录
		//保存最后一次登陆时间
		return true;
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

    
}
