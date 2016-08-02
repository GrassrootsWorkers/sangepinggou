package com.farmer.fruit.publish.fruit;

import com.farmer.fruit.interfaces.task.Task;
import com.farmer.fruit.models.fruit.Fruit;
import com.farmer.fruit.publish.AbstractMonitor;
import com.farmer.fruit.publish.work.Worker;
import com.farmer.fruit.queue.RedisQueue;
import redis.clients.jedis.JedisPool;

/**
 * Created by liuzhi on 2016/7/28.
 */
public class FruitMonitor extends AbstractMonitor {
    Task task;
    JedisPool jedisPool;
    String redisKey;
    RedisQueue<Fruit> queue;
    public void init() {
        queue = new RedisQueue<>(jedisPool,redisKey,Fruit.class);
        Thread t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                startTask();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(1000 * interval);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private void startTask() {
        while (true) {
            Fruit fruit = queue.poll();
            if (fruit == null) {
                break;
            }
            Worker worker = new Worker(task, fruit);
            if (threadManager.contains(worker)) {
                continue;
            } else {
                threadManager.execute(worker);
            }
        }
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public JedisPool getJedisPool() {
        return jedisPool;
    }

    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    public String getRedisKey() {
        return redisKey;
    }

    public void setRedisKey(String redisKey) {
        this.redisKey = redisKey;
    }

}
