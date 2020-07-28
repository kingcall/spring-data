package com.kingcall.redistemplate2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RedisController {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/hello")
    public String hello() {
        // 向 Redis 中添加一个 key 为 hello，Value 为 world 的记录
        stringRedisTemplate.opsForValue().set("hello","world");

        // 获取 Redis 中 key 为 hello 的值
        return stringRedisTemplate.opsForValue().get("hello");
    }

    public String hello2(){
        stringRedisTemplate.opsForValue().set("hello","world");

        return stringRedisTemplate.opsForValue().get("hello");
    }
}
