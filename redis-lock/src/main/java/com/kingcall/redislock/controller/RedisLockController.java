package com.kingcall.redislock.controller;

import com.kingcall.redislock.lock.RedisLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redislock")
public class RedisLockController {

    private final long TIME_OUT = 50 * 1000;

    private final String REDIS_LOCK = "REDIS_LOCK";

    @Autowired
    private RedisLock redisLock;

    /**
     * 当 lock() 成功以后，在 do something 的过程中出现意外导致后面的 unLock() 没有被执行，
     * 那么就会导致其他请求无法再获得锁，从而造成了死锁。所以，它不是一把好锁。
     */
    @GetMapping("/lock")
    public void lock() {

        // 加锁
        long currentTime = System.currentTimeMillis();
        boolean isLock = redisLock.lock(REDIS_LOCK, String.valueOf(currentTime + TIME_OUT));
        if (!isLock) {
            throw new RuntimeException("资源已被抢占，换个姿势再试试吧！");
        }

        // do something

        // 解锁
        redisLock.unLock(REDIS_LOCK);

    }

    /**
     * 现在这个方案完美了吗？显然还没有。假如有两个线程 A 和 B，
     * 在 A 执行完 do something 之后，恰好 key 到了过期时间，
     * 又恰好这时 B 获得了锁，那么接下来会发生什么？接下来 A 执行 unLock() 会将 B 获得的锁删掉！防不胜防呀！
     */
}
