package com.sangepg.fruit.controller.weixin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.farmer.fruit.interfaces.orders.IOrderService;
import com.farmer.fruit.models.orders.Order;
import com.farmer.fruit.models.orders.OrderQuery;
import com.farmer.fruit.utils.RedisUtils;
import com.farmer.fruit.utils.WebUtils;
import com.sangepg.fruit.controller.BaseAction;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liuzhi on 2016/10/11.
 */
@Controller()
@RequestMapping(value = "/weixin")
public class WeiXinController extends BaseAction {

   @Autowired
    JedisPool jedisPool;
    @Autowired
    IOrderService orderService;
    @RequestMapping(value = "/to/order/page/{source}", method = RequestMethod.GET)
    public ModelAndView toOrderPage(@PathVariable("source")String source, String code, HttpServletResponse response){
        RedisUtils redisUtils = new RedisUtils(jedisPool);
        String appId = redisUtils.getHashValueByKey("wx_appids",source);
        String secret = redisUtils.getHashValueByKey("wx_secrets",source);
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+appId+"&secret="+secret+"&code="+code+"&grant_type=authorization_code";
        String openId = "";
        try {
            String responseJson = WebUtils.doGet(url,null,WebUtils.DEFAULT_CHARSET);
            JSONObject jsonObject = JSON.parseObject(responseJson);
            openId = jsonObject.getString("openid");
            addCookie("openid",openId,0,response);
        } catch (IOException e) {
            addCookie("openid","openid",0,response);
            e.printStackTrace();
        }
        //查询用户的订单
        OrderQuery query = new OrderQuery();
        query.setOpenId(openId);
        List<Order> historyOrders = orderService.findList(query,0,5);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("history" ,historyOrders);
        ModelAndView modelAndView = new ModelAndView("order/order_confirm",resultMap);
        return null ;
    }
}
