package com.farmer.fruit.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by liuzhi on 2016/8/14.
 */
public class RedisUtils {
    private JedisPool jedisPool;

    public RedisUtils(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    public boolean setKey(String key, String value,int expire){
        if (jedisPool == null) return false;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            if (jedis == null) return false;
            jedis.set(key, value);
            jedis.expire(key,expire);
            return true;
        } catch (Exception e) {
            return false;
        }finally {
            if(jedis != null){
                jedis.close();
            }
        }
    }
    public boolean setKey(String key, String value) {
        if (jedisPool == null) return false;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            if (jedis == null) return false;
            jedis.set(key, value);

            return true;
        } catch (Exception e) {
            return false;
        }finally {
            if(jedis != null){
                jedis.close();
            }
        }
    }

    public String getValueByKey(String key) {
        if (jedisPool == null) return null;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            if (jedis == null) return null;
            String value = jedis.get(key);
            return value;
        } catch (Exception e) {
            return null;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
    public boolean mobileIfRegister(String mobile){
        String key = mobile.substring(0,3);
        String field = mobile.substring(3,8);
        String value = mobile.substring(8,mobile.length());
        if (jedisPool == null) return false;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            if (jedis == null) return false;
            if(!jedis.exists(key)){
                return false;
            }
            if(!jedis.hexists(key,field)){
                return false;
            }
            String sourceValue = jedis.hget(key,field);
            if(sourceValue.contains(value)){
                return true;
            }else{
                return false;
            }
        } catch (Exception e) {
            return false;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
    public boolean addMobileToRedis(String mobile){
        String key = mobile.substring(0,3);
        String field = mobile.substring(3,8);
        if (jedisPool == null) return false;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String value = jedis.hget(key,field);
            jedis.hset(key,field,mobile.substring(8,mobile.length())+"_"+value);
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
}
