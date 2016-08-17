package adapter;

/**
 * Created by liuzhi on 2016/8/10.
 */
public class Person implements Animal {
    @Override
    public void run() {
        System.out.println("我两条腿跑步");
    }

    @Override
    public void eat() {

    }

    @Override
    public void sleep() {

    }
}
