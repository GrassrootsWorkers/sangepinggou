package com.sangepg.fruit.controller.weixin;

import com.farmer.fruit.utils.MD5Util;
import com.farmer.fruit.utils.RandomStrUtil;
import com.farmer.fruit.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.JedisPool;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liuzhi on 2016/11/20.
 */
@Controller()
@RequestMapping("/wx/commons")
public class WeiXinCommonController {
    @Autowired
    JedisPool jedisPool;
    @RequestMapping(value = "ticket", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getWeiXinTicket(String account, String url){
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("success",false);
        RedisUtils redisUtils = new RedisUtils(jedisPool);
        String ticket = redisUtils.getHashValueByKey("wx_ticket",account);
        if(ticket == null){
            resultMap.put("code","501");
            resultMap.put("msg","error");
            return resultMap;
        }
        String nonceStr = RandomStrUtil.getCommonStr(16);
        long timestamp = (new Date().getTime())/1000;
        String queryString = "jsapi_ticket="+ticket+"&noncestr="+nonceStr+"&timestamp="+timestamp+"&url="+url;
        String sign = MD5Util.sign(queryString);
        if(sign == null){
            resultMap.put("code","501");
            resultMap.put("msg","error");
            return resultMap;
        }
        resultMap.put("code","200");
        resultMap.put("success",true);
        //resultMap.put("ticket",ticket);
        resultMap.put("nonceStr",nonceStr);
        resultMap.put("timestamp",timestamp);
        resultMap.put("sign",sign);
        return resultMap;
    }

}

