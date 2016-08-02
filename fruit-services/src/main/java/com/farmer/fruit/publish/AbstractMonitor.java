package com.farmer.fruit.publish;

public abstract class AbstractMonitor implements Runnable {
    protected String block_key;
    protected String fruit_key;
    protected ThreadManager threadManager;//线程管理器
    protected int interval;

    public String getBlock_key() {
        return block_key;
    }

    public void setBlock_key(String block_key) {
        this.block_key = block_key;
    }

    public String getFruit_key() {
        return fruit_key;
    }

    public void setFruit_key(String fruit_key) {
        this.fruit_key = fruit_key;
    }

    public ThreadManager getThreadManager() {
        return threadManager;
    }

    public void setThreadManager(ThreadManager threadManager) {
        this.threadManager = threadManager;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }
}
