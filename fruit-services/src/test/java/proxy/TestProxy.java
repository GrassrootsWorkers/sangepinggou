package proxy;

import org.junit.Test;

import java.lang.reflect.Proxy;


/**
 * Created by liuzhi on 2016/8/10.
 */
public class TestProxy {

    @Test
    public void testDynamicProxy() {
        TeacherSubject teacherSubject = new TeacherSubject();
        DynamicProxy dynamicProxy = new DynamicProxy(teacherSubject);
        Subject subject = (Subject) Proxy.newProxyInstance(teacherSubject.getClass().getClassLoader(), teacherSubject.getClass().getInterfaces(), dynamicProxy);
        System.out.println(subject.getClass().getName());
        subject.setSubjectName("傻逼中国共产党");
        //subject.getSubjectName();
        System.out.print(subject.getSubjectName());
    }
    @Test
    public void testStaticProxy(){
       Subject normalProxy = new NormalProxy("二逼 北京");
        normalProxy.setSubjectName("傻逼共产党");
        System.out.println(normalProxy.getSubjectName());
    }
}
