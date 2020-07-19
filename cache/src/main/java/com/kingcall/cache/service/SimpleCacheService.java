package com.kingcall.cache.service;

import com.kingcall.cache.entity.SimpleCache;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * 使用SB 自带的缓存
 */
@Service
public class SimpleCacheService {
    Random random = new Random();
    @Cacheable(cacheNames = "SimpleCache")
    public SimpleCache getCache(String name){
        System.out.println("构建新的对象");
        return new SimpleCache(name,21);
    }
    @Cacheable(value ="num#${select.cache.timeout:1}")
    public int getNum(){
        System.out.println("获取新的数字");
        return random.nextInt(5);
    }
}
