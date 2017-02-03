package com.sangepg.fruit.controller.user;

import com.farmer.fruit.interfaces.user.IUserService;
import com.farmer.fruit.models.user.User;
import com.farmer.fruit.models.user.UserQuery;
import com.farmer.fruit.sms.SmsUtil;
import com.farmer.fruit.utils.IpUtils;
import com.farmer.fruit.utils.MD5Util;
import com.farmer.fruit.utils.RandomStrUtil;
import com.farmer.fruit.utils.RedisUtils;
import com.sangepg.fruit.controller.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liuzhi on 2016/8/11.
 */
@Controller
@RequestMapping(value = "/user/")
public class UserController extends BaseAction {
    @Autowired
    JedisPool jedisPool;
    @Autowired
    IUserService<User,UserQuery> userService;
    @RequestMapping(value = {"sendSimpleLoginCode", "getCode"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> sendSimpleLoginCode(String mobile) {
        RedisUtils redisUtils = new RedisUtils(jedisPool);
        String checkCode = RandomStrUtil.getNumStr(6);
        boolean addFlag = redisUtils.setKey(mobile, checkCode, 1200);
        Map<String, Object> result = new HashMap<String, Object>();
            result.put("success", false);
        if (addFlag) {
            String content = null;
            try {
                content = URLEncoder.encode("您的验证码是：" + checkCode + "打死也不要告诉别人哦！", "UTF-8");
                SmsUtil.chuangLanSend(content, mobile);
                result.put("success", true);
                result.put("msg", "验证码已经发送到您的手机");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } else {
            result.put("msg", "系统繁忙，请稍后再试");
        }
        return result;

    }
    @RequestMapping(value = {"login","register"},method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> loginOrRegister(String mobile, String code, HttpServletRequest request, HttpServletResponse response){
        //获取用户openId关联手机号
        //如果有则关联
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", false);
        if(!validateMobile(mobile)){
            result.put("errCode","-1");
            return result;
        }
        RedisUtils redisUtils = new RedisUtils(jedisPool);
        String redisCode = redisUtils.getValueByKey(mobile);
        if(redisCode == null || !redisCode.equals(code)){
            result.put("msg","验证码不正确，请重新输入验证码");
            result.put("errCode","-2");
            return result;
        }else{
            result.put("success", true);
        }
        if(!redisUtils.mobileIfRegister(mobile)){
            //注册逻辑
            User user = new User();
            user.setMobile(mobile);
            user.setUserType(User.NORMAL_USER);
            user.setRegisterTime(new Date());
            user.setLastLoginTime(new Date());
            user.setIp(IpUtils.IpToLong(getRealIP(request)));
            user.setPassword(MD5Util.entryptPassword(mobile.substring(5,mobile.length())));
            user.setNewRecord(true);
            userService.save(user);
            redisUtils.addMobileToRedis(mobile);
            result.put("success", true);
        }
        //记录cookie 和redis
        addCookie("m",mobile,365*60*60,response);
        addCookie("mobile",mobile,365*60*60,response);
        //fron_login_user
        redisUtils.setKey("f_l_u"+mobile,mobile,365*60*60);
        return result;
    }

    @RequestMapping(value = "checkSimpleLoginCode")
    @ResponseBody
    public Map<String, Object> checkSimpleLoginCode(String mobile, String code) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", true);
        RedisUtils redisUtils = new RedisUtils(jedisPool);
        String redisCode = redisUtils.getValueByKey(mobile);
        if (redisCode == null || !redisCode.equals(code)) {
            result.put("success", false);
            return result;
        }
        return result;
    }
}
