package com.farmer.fruit.task;

import com.farmer.fruit.models.fruit.Fruit;
import com.farmer.fruit.queue.RedisQueue;

import java.util.List;

/**
 * Created by liuzhi on 2016/7/14.
 */
public class AddToRedisTask implements Runnable{
    private RedisQueue redisQueue;
    private List<Fruit> fruitList;
    public AddToRedisTask(RedisQueue<Fruit> queue,List<Fruit> fruitList){
        this.redisQueue = queue;
        this.fruitList = fruitList;
    }
    @Override
    public void run() {
        for(Fruit fruit : fruitList){
            redisQueue.add(fruit);
        }
    }

}
