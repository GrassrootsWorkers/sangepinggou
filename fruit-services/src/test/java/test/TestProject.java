package test;

import com.farmer.fruit.business.impl.common.CommonServiceImpl;
import com.farmer.fruit.models.address.Address;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.druid.pool.DruidDataSource;
import com.farmer.fruit.business.impl.user.UserServiceImpl;
import com.farmer.fruit.models.user.User;
import redis.clients.jedis.*;

import java.util.HashSet;
import java.util.Set;


public class TestProject {
    static DruidDataSource dataSource;
    static UserServiceImpl userServiceImpl;
    static CommonServiceImpl commonServiceImpl;
    static JedisPool jedisPool;

    @BeforeClass
    public static void init() {
       // ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-service.xml");
        //dataSource = (DruidDataSource)context.getBean("dataSource");
        //userServiceImpl = (UserServiceImpl)context.getBean("userServiceImpl");
        //commonServiceImpl = (CommonServiceImpl)context.getBean("commonServiceImpl");
       // jedisPool = (JedisPool) context.getBean("jedisPool");
    }

    @Test
    public void testProject() {
        System.out.println(userServiceImpl);
        User user = userServiceImpl.getById(1L);
        System.out.println(user.getId());
    }

    @Test
    public void testCommons() {
        Address address = new Address();
        //commonServiceImpl.getAddress(address);
        Jedis jedis = jedisPool.getResource();
        jedis.close();
    }

    @Test
    public void testRedisCluster() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(20);
        config.setMaxIdle(2);

        HostAndPort hp0 = new HostAndPort("192.168.118.128", 7001);
        HostAndPort hp1 = new HostAndPort("192.168.118.128", 7002);
        HostAndPort hp2 = new HostAndPort("192.168.118.128", 7003);
        HostAndPort hp3 = new HostAndPort("192.168.118.128", 7004);
        HostAndPort hp4 = new HostAndPort("192.168.118.128", 7005);
        HostAndPort hp5 = new HostAndPort("192.168.118.128", 7006);

        Set<HostAndPort> hps = new HashSet<HostAndPort>();
        hps.add(hp0);
        hps.add(hp1);
        hps.add(hp2);
        hps.add(hp3);
        hps.add(hp4);
        hps.add(hp5);
        // 超时，最大的转发数，最大链接数，最小链接数都会影响到集群
        JedisCluster jedisCluster = new JedisCluster(hps, 50000, 10, config);

       /* long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            jedisCluster.set("sn" + i, "n" + i);
        }
        long end = System.currentTimeMillis();

        System.out.println("Simple  @ Sharding Set : " + (end - start) / 10000);*/

        for (int i = 0; i < 100; i++) {
            System.out.println(jedisCluster.get("sn" + i));
        }

        jedisCluster.close();

    }

}
