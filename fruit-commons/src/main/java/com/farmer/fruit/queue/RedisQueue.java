package com.farmer.fruit.queue;


import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;

import com.farmer.fruit.json.GsonUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
/**
 * Created by liuzhi on 2016/7/15.
 */

public class RedisQueue<E> implements Queue<E> {

    private JedisPool jedisPool;

    private String redisKey;

    private Class<E> classT;

    public RedisQueue(JedisPool jedisPool, String redisKey, Class<E> type) {
        if (jedisPool == null || redisKey == null || "".equals(redisKey.trim())) {
            throw new IllegalArgumentException("poolConfig or redisKey not be null");
        }

        this.jedisPool = jedisPool;
        this.redisKey = redisKey;

        classT = type;
    }

    @Override
    public int size() {
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();

            Long len = jedis.llen(redisKey);
            return  len == null ? 0 : len.intValue();

        } finally{
          jedis.close();
        }
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException("not implement");
    }

    @Override
    public Iterator<E> iterator() {
        throw new UnsupportedOperationException("not implement");
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException("not implement");
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException("not implement");
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("not implement");
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException("not implement");
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {

        for (E e : c) {
            add(e);
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("not implement");
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("not implement");
    }

    @Override
    public void clear() {
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            jedis.del(redisKey);
        } finally{
           jedis.close();
        }
    }

    @Override
    public boolean add(E e) {
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();

            jedis.rpush(redisKey, GsonUtil.getJSON(e));

        } finally{
           jedis.close();
        }

        return true;
    }

    @Override
    public boolean offer(E e) {
        return add(e);
    }

    @Override
    public E remove() {
        return poll();
    }

    @Override
    public E poll() {
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();

            return GsonUtil.<E>getObject(jedis.lpop(redisKey), classT);

        } finally{
           jedis.close();
        }
    }

    @Override
    public E element() {
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();

            List<String> l = jedis.lrange(redisKey, 0, 0);

            if (l.isEmpty()) {
                return null;
            }

            return GsonUtil.<E>getObject(l.get(0), classT);

        } finally{
           jedis.close();
        }
    }

    @Override
    public E peek() {
        return element();
    }

}