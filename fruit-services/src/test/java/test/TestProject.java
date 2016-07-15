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
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


public class TestProject {
	static DruidDataSource dataSource ;
	static UserServiceImpl userServiceImpl;
	static CommonServiceImpl commonServiceImpl;
	static JedisPool jedisPool;
	 @BeforeClass
	    public static void  init() {
	        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-service.xml");
	        //dataSource = (DruidDataSource)context.getBean("dataSource");
	        //userServiceImpl = (UserServiceImpl)context.getBean("userServiceImpl");
		 //commonServiceImpl = (CommonServiceImpl)context.getBean("commonServiceImpl");
		 jedisPool = (JedisPool)context.getBean("jedisPool");
	 }
	 @Test
	 public void testProject(){
		 System.out.println(userServiceImpl);
		 User  user = userServiceImpl.getById(1L);
		 System.out.println(user.getId());
	 }
	@Test
	public void testCommons(){
		Address address = new Address();
		//commonServiceImpl.getAddress(address);
		Jedis jedis = jedisPool.getResource();
		jedis.close();
	}
	        
}
