package com.kingcall.redislock.lock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Component
public class RedisLock3 {
    @Autowired
    private StringRedisTemplate redisTemplate;

    public boolean lockV3(String key, String value,Long timeOut) {
        return redisTemplate.opsForValue().setIfAbsent(key, value, timeOut, TimeUnit.MILLISECONDS);
    }

    /**
     * OK，这次我们在删除之前对锁的持有者进行了判断，只有确定自己是锁的持有者才去释放锁。
     * 这次看起来没什么毛病了，但是很遗憾，判断持有者的逻辑和删除 key 的逻辑仍然不是一个原子的操作。
     * 虽然这两个操作之间的间隔非常短，但仍有可能在这两个操作之间被其他线程干扰。
     * 判断结束后，被其他线程持有了
     * @param key
     * @param value
     */
    public void unLockV3(String key, String value) {
        String oldValue = redisTemplate.opsForValue().get(key);
        if (Objects.nonNull(oldValue) && oldValue.equals(value)) {
            redisTemplate.delete(key);
        }
    }
}
