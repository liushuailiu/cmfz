package com.fly.util.cache;

import org.apache.ibatis.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author fly
 * 自定义redis缓存策略
 */
public class RedisCache implements Cache {

    /**
     * 缓存实例
     */
    private final String id;
    /**
     * 并发读写锁,适用于读多写少的情况
     */
    private final static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    @Autowired
    private static JedisConnectionFactory jedisConnectionFactory;

    public RedisCache(final String id) {
        if (null == id) {
            throw new IllegalArgumentException("缓存实例需要一个ID");
        }
        this.id = id;
    }

    /**
     *
     * @return  返回缓存实例ID
     */
    @Override
    public String getId() {
        return this.id;
    }

    /**
     * 序列化存入数据
     * @param key    键
     * @param value  值
     */
    @Override
    public void putObject(Object key, Object value) {
        RedisConnection redisConnection = jedisConnectionFactory.getConnection();
        RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer();
        redisConnection.set(serializer.serialize(key),serializer.serialize(value));
        redisConnection.close();
    }

    /**
     * 根据key得到redis缓存内容
     * @param key
     * @return key-->value
     */
    @Override
    public Object getObject(Object key) {
        RedisConnection redisConnection =
                jedisConnectionFactory.getConnection();
        RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer();
        Object result = serializer.deserialize(redisConnection.get(serializer.serialize(key)));
        redisConnection.close();
        return result;
    }

    /**
     * 删除内存当中的数据
     * @param key  被删除数据的名称
     * @return  object
     */
    @Override
    public Object removeObject(Object key) {
        Object result = null;
        RedisConnection redisConnection = jedisConnectionFactory.getConnection();
        RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer();
        result = redisConnection.expire(serializer.serialize(key),0);
        redisConnection.close();
        return result;
    }

    /**
     * 清空redis当中的缓存
     */
    @Override
    public void clear() {
        RedisConnection redisConnection = jedisConnectionFactory.getConnection();
        redisConnection.flushDb();
        redisConnection.flushAll();
        redisConnection.close();
    }

    /**
     * 获取缓存当中key的个数
     * @return  int
     */
    @Override
    public int getSize() {
        int result = 0;
        RedisConnection redisConnection = jedisConnectionFactory.getConnection();
        result = Integer.valueOf(redisConnection.dbSize().toString());
        redisConnection.close();
        return result;
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return readWriteLock;
    }

    public static void setJedisConnectionFactory(JedisConnectionFactory jedisConnectionFactory) {
        RedisCache.jedisConnectionFactory = jedisConnectionFactory;
    }

}
