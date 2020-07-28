package com.kingcall.redislock.lock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisLock2 {
    @Autowired
    private StringRedisTemplate redisTemplate;

    public boolean lockV2(String key, String value,Long timeOut) {
        return redisTemplate.opsForValue().setIfAbsent(key, value, timeOut, TimeUnit.MILLISECONDS);
    }

    public void unLockV2(String key) {
        redisTemplate.delete(key);
    }

}
