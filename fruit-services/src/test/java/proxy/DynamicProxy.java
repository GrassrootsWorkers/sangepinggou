package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by liuzhi on 2016/8/10.
 */
public class DynamicProxy implements InvocationHandler {

    private Object subject;

    public DynamicProxy(Object object){
        this.subject = object;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("在调用方法之前");
        System.out.println("调用方法 method+"+method);
        Object object = method.invoke(subject,args);
        System.out.println("方法调用后");
        return object;
    }
}
