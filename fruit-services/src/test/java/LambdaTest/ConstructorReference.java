package LambdaTest;

import org.junit.Test;

/**
 * Created by liuzhi on 2016/7/29.
 */
public class ConstructorReference {
    MyInterface myInterface = MyClass::new;
    @Test
    public  void test(){
       // System.out.print(myInterface.getMyClassObject());
    }
}
interface  MyInterface{
    MyClass getMyClassObject();
}
class MyClass{
    public MyClass(){

    }
}
