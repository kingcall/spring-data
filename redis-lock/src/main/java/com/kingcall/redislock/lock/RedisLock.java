package com.kingcall.redislock.lock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisLock {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public boolean lock(String key, String value) {
        return redisTemplate.opsForValue().setIfAbsent(key, value);
    }

    public void unLock(String key) {
        redisTemplate.delete(key);
    }
}
