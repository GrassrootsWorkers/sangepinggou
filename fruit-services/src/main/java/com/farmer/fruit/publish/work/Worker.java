package com.farmer.fruit.publish.work;

import com.farmer.fruit.interfaces.task.Task;
import com.farmer.fruit.models.BaseEntity;

/**
 * Created by liuzhi on 2016/8/1.
 */
public class Worker<T extends BaseEntity> implements Runnable {
    private Task task;
    private T entity;
    public Worker(Task task, T entity){
        this.task = task;
        this.entity = entity;
    }
    @Override
    public void run() {
        task.execute(entity);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Worker<T> worker = (Worker<T>) obj;
        return this.entity.equals(worker.entity);
    }
    @Override
    public int hashCode() {
        int result = entity != null ? entity.hashCode() : 0;
        result = 31 * result + (entity != null ? entity.hashCode() : 0);
        return result;
    }
    @Override
    public String toString() {
      return entity.toString();
    }
}
