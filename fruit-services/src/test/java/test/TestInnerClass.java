package test;

import org.junit.Test;

import java.util.Comparator;

/**
 * Created by liuzhi on 2016/7/29.
 */
public class TestInnerClass {
        private String outMsg ="out msg";

    private void testLambda(){
        String  outMsg ="inner msg";
        new Thread(()->{
            System.out.println(outMsg);
            System.out.println(this.outMsg);
        }).start();

    }
    class InnerClass{
        private String  outMsg ="inner msg";
        public void innerMethod(){
            String outMsg ="method msg";
            System.out.println(outMsg);
            System.out.println(this.outMsg);
        }
    }
    public static void main(String[]args){
        TestInnerClass innerClass = new TestInnerClass();
        //InnerClass inner = innerClass.new InnerClass();
        //inner.innerMethod();
        innerClass.testLambda();

    }
    @Test
    public void test(){

    }
}
