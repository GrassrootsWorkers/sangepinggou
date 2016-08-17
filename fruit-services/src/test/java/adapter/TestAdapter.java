package adapter;

import org.junit.Test;

/**
 * Created by liuzhi on 2016/8/10.
 */
public class TestAdapter {
    @Test
    public void testAdapter(){
        ClassAdapter adapter = new ClassAdapter();
        System.out.println(adapter.getName());
        System.out.println(adapter.getAddress());

    }
    @Test
    public void testObjectAdapter(){
        WrapperAdapter wrapperAdapter = new WrapperAdapter(new Source());
        System.out.println(wrapperAdapter.getName());
        System.out.println(wrapperAdapter.getAddress());
    }
}
