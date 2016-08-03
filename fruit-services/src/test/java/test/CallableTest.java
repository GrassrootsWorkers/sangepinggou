package test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by liuzhi on 2016/7/29.
 */
public class CallableTest implements Callable{
    private int index;
    public CallableTest(int index){
        this.index = index;
    }
    @Override
    public String call() throws Exception {
        return "result of TaskWithResult " + index;
    }
    public static void main(String[]args){
      /*  ExecutorService executorService = Executors.newCachedThreadPool();
        Future future = executorService.submit(new CallableTest(8));
        try {
            System.out.println(future.get().toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }*/
        List list = Arrays.asList((Callable)()->"first callable",(Callable) ()->"second callable",(Callable)()->"third callable");
        ExecutorService executor = Executors.newFixedThreadPool(2);
        try{
            List<Future> futures = executor.invokeAll(list);
            new CallableTest(8).dumpList(futures);
        }catch (InterruptedException | ExecutionException e){
            e.printStackTrace();
        }
    }
    public void dumpList(List<Future> futureList) throws InterruptedException ,ExecutionException {
        for(Future future : futureList){
            System.out.println(future.get());
        }
    }
    public String str(){
        return "::";
    }

}