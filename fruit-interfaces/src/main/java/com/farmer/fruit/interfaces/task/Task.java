package com.farmer.fruit.interfaces.task;

/**
 * Created by liuzhi on 2016/7/27.
 */
public interface Task<T>{
    /**
     * 获取任务名称
     * @return
     */
    String getTaskName();

    /**
     * 执行任务
     */
    void execute(T t);
}
