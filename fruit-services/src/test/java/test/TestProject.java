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

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
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
    public void testLambda(){
        List features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
        features.forEach(
                n -> System.out.println(n)
        );
    }

}
