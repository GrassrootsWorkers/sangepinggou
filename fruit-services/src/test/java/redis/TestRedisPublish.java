package redis;

import org.junit.Test;
import redis.clients.jedis.Client;
import redis.clients.jedis.Jedis;

/**
 * Created by liuzhi on 2016/7/12.
 */
public class TestRedisPublish {

    @Test
    public void testRedis(){
        Jedis jedis = new Jedis("data.redis.univpl.com",6379,0);
        jedis.publish("liuZhi","test publish");
        jedis.publish("liuZhi001","test publish 001");
        jedis.disconnect();
    }
    @Test
    public void testSubscribe(){
        Jedis jedis = new Jedis("data.redis.univpl.com",6379,0);
        Client client = jedis.getClient();
        TestRedisPubSub testRedisPub = new TestRedisPubSub();
        testRedisPub.proceed(client,"liuZhi","liuZhi001");
        jedis.disconnect();
    }
}
