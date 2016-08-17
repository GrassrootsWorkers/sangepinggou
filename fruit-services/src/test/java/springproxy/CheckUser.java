package springproxy;

import org.junit.Test;
import org.springframework.aop.BeforeAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.framework.ProxyFactoryBean;

import java.lang.reflect.Method;

/**
 * Created by liuzhi on 2016/8/10.
 */
public class CheckUser implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        String userName = (String)objects[0];
        System.out.println(userName+"login before");
    }
    @Test
    public void testSpringAop(){
        UserLoginImpl target = new UserLoginImpl();
        BeforeAdvice beforeAdvice = new CheckUser();
        ProxyFactory factoryBean = new ProxyFactory();
        factoryBean.setTarget(target);
        factoryBean.addAdvice(beforeAdvice);
        UserLogin userLogin = (UserLogin)factoryBean.getProxy();
        userLogin.login("刘志");
    }
}
