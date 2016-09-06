package com.farmer.fruit.utils;



import redis.clients.jedis.JedisCluster;

import java.util.Map;

/**
 * Created by liuzhi on 2016/8/14.
 */
public class RedisClusterUtils {
    private JedisCluster jedisCluster;
    public RedisClusterUtils(JedisCluster jedisCluster) {
        this.jedisCluster= jedisCluster;
    }

    public boolean setKey(String key, String value, int expire) {
        try {
            jedisCluster.set(key, value);
            jedisCluster.expire(key, expire);
            return true;
        } catch (Exception e) {
            return false;
        } finally {
        }
    }

    public boolean setKey(String key, String value) {
        try {
            if (jedisCluster== null) return false;
            jedisCluster.set(key, value);
            return true;
        } catch (Exception e) {
            return false;
        } finally {
        }
    }

    public String getValueByKey(String key) {
        try {
            if (jedisCluster== null) return null;
            String value = jedisCluster.get(key);
            return value;
        } catch (Exception e) {
            return null;
        } finally {
        }
    }
    public boolean del(String key) {
        try {
           
            if (jedisCluster== null) return false;
            jedisCluster.del(key);
            return true;
        } catch (Exception e) {
            return false;
        } finally {
        }
    }

    public boolean mobileIfRegister(String mobile) {
        String key = mobile.substring(0, 3);
        String field = mobile.substring(3, 8);
        String value = mobile.substring(8, mobile.length());
        try {
            if (jedisCluster== null) return false;
            if (!jedisCluster.exists(key)) {
                return false;
            }
            if (!jedisCluster.hexists(key, field)) {
                return false;
            }
            String sourceValue = jedisCluster.hget(key, field);
            if (sourceValue.contains(value)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        } finally {

        }
    }

    public boolean addMobileToRedis(String mobile) {
        String key = mobile.substring(0, 3);
        String field = mobile.substring(3, 8);
        try {
            String value = jedisCluster.hget(key, field);
            jedisCluster.hset(key, field, mobile.substring(8, mobile.length()) + "_" + value);
            return true;
        } catch (Exception e) {
            return false;
        } finally {
        }
    }

    public boolean setHKey(String key, String field, String value) {
        try {
            jedisCluster.hset(key, field, value);
            return true;
        } catch (Exception e) {
            return false;
        } finally {
        }
    }
    public int getFieldSize(String key) {
        try {
           
            return jedisCluster.hlen(key).intValue();
        } catch (Exception e) {
            return 0;
        } finally {
        }
    }
    public Map<String,String> getUserCartStr(String mobile){
        try {
           
            return jedisCluster.hgetAll(mobile);
        } catch (Exception e) {
            return null;
        } finally {
        }
    }
    public boolean delHField(String key, String field) {
        try {
            jedisCluster.hdel(key, field);
            return true;
        } catch (Exception e) {
            return false;
        } finally {
        }
    }
    public boolean setTimes(String key) {
        try {
            jedisCluster.incr(key);
            return true;
        } catch (Exception e) {
            return false;
        } finally {
        }
    }
}
