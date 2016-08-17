package adapter;

import org.junit.Test;

/**
 * Created by liuzhi on 2016/8/10.
 */
public class PersonAdapter extends Person implements Dog {
    @Override
    public void call() {
        System.out.println("我学会了狗的语言");
    }
    @Test
    public void test(){
        PersonAdapter adapter = new PersonAdapter();
        adapter.call();
        adapter.run();
    }
}
