package com.farmer.fruit.publish;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class ThreadManager {
    @SuppressWarnings("unused")
    private String threadPoolName;
    private ThreadPoolExecutor executorservice;

    public ThreadManager(String threadPoolName, int minPoolSize, int maxPoolSize, int queueBlockSize, int idleSeconds) {
        this.threadPoolName = threadPoolName;
        executorservice = new ThreadPoolExecutor(
                minPoolSize,  /*线程池维护线程的最少数量*/
                maxPoolSize,  /*线程池维护线程的最大数量*/
                idleSeconds,  /*线程池维护线程所允许的空闲时间*/
                TimeUnit.SECONDS, /*时间单位,秒*/
                new ArrayBlockingQueue<Runnable>(queueBlockSize), /*线程池所使用的缓冲队列*/
                new ThreadPoolExecutor.CallerRunsPolicy()); /*重试添加当前加入失败的任务*/
    }

    /**
     * 提交任务到线程池中
     * 如果提交的任务总数大于最大线程数和线程池缓冲队列，且设置为CallerRunsPolicy策略时，
     * 此方法阻塞
     *
     * @param task
     */
    public void execute(Runnable task) {
        this.executorservice.execute(task);
    }

    /**
     * 检查任务是否已在队列中
     *
     * @param task
     */
    public boolean contains(Runnable task) {
        return executorservice.getQueue().contains(task);
    }

    @Override
    public String toString() {
        return "ThreadManager: " + executorservice;
    }
}
