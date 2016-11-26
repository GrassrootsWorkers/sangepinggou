package com.farmer.fruit.job.weixin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.farmer.fruit.models.farmer.Reserved;
import com.farmer.fruit.models.farmer.ReservedQuery;
import com.farmer.fruit.persistence.farmer.IReservedDao;
import com.farmer.fruit.utils.QRUtil;
import com.farmer.fruit.utils.RedisUtils;
import com.farmer.fruit.utils.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.util.List;

/**
 * Created by liuzhi on 2016/10/3.
 */
public class WeixinFreshAccessTokenService {
    private static Logger log = LoggerFactory.getLogger(WeixinFreshAccessTokenService.class);

    private JedisPool jedisPool;
    private  RedisUtils redisUtils = new RedisUtils(jedisPool);

    public void execute() {

        String weiXinAccount = redisUtils.getValueByKey("wx_accounts");
        String[] accounts = weiXinAccount.split(",");
        for (String account : accounts) {

            try {
                boolean flag = cacheAccessToken(account);
                if (flag) {
                    cacheTicket(account);
                }
            } catch (IOException e) {
                log.error("WeiXinFreshAccessTokenService={}",e.getMessage());
            }
        }

    }

    public boolean cacheAccessToken(String account) {
        String url = null;
        try {
            url = redisUtils.getHashValueByKey("wx_urls", account);
            String responseJson = WebUtils.doGet(url, null, WebUtils.DEFAULT_CHARSET);
            JSONObject jsonObject = JSON.parseObject(responseJson);
            String accessToken = (String) jsonObject.get("access_token");
            redisUtils.setHKey("wx_access_token", account, accessToken);
        } catch (Exception e) {
            log.error("account={} get access token error url ={}", account, url);
            return false;
        }
        return true;

    }

    public void cacheTicket(String account) throws IOException {
        try {
            String accessToken = redisUtils.getHashValueByKey("wx_access_token", account);
            String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + accessToken + "&type=jsapi";
            String responseJson = WebUtils.doGet(url, null, WebUtils.DEFAULT_CHARSET);
            JSONObject jsonObject = JSON.parseObject(responseJson);
            String ticket = (String) jsonObject.get("ticket");
            redisUtils.setHKey("wx_ticket", account, ticket);
        } catch (Exception e) {
            log.error("account={} get ticket error url ={}", account);
        }

    }

    public JedisPool getJedisPool() {
        return jedisPool;
    }

    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }
}

