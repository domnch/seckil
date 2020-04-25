package com.dayup.seckil.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author domn
 * @version 1.0
 * @date 2020/1/24 16:32
 */
public abstract class BaseRedis<T> {

    @Autowired
    protected RedisTemplate<String, Object> redisTemplate;

    @Resource
    protected HashOperations<String, String, T> hashOperations;

    protected abstract String getRedisKey();

    /**
     * 存入redis中的key
     * @return
     */
    public void put(String key, T domain, long expire) {
        hashOperations.put(getRedisKey(), key, domain);
        if(expire != -1) {
            redisTemplate.expire(getRedisKey(), expire, TimeUnit.SECONDS);
        }
    }

    /**
     * 查询
     * @param key 查询的key
     * @return
     */
    public T get(String key) {
        return hashOperations.get(getRedisKey(), key);
    }
}
