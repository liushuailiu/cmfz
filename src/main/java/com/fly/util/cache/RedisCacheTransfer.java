package com.fly.util.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

/**
 * @author fly
 * 为redisCache注入JedisConnectionFactory
 */
public class RedisCacheTransfer {

    @Autowired
    private JedisConnectionFactory jedisConnectionFactory;

    public void setJedisConnectionFactory(JedisConnectionFactory jedisConnectionFactory) {
        RedisCache.setJedisConnectionFactory(jedisConnectionFactory);
    }

    public JedisConnectionFactory getJedisConnectionFactory() {
        return jedisConnectionFactory;
    }

}
