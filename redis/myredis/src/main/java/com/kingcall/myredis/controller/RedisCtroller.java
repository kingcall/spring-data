package com.kingcall.myredis.controller;

import com.kingcall.myredis.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import redis.clients.jedis.Jedis;

@Controller
@RequestMapping(value = "/redis")
public class RedisCtroller {
    @Autowired
    RedisUtils redisUtils;

    @RequestMapping(value = "/getstr",method = RequestMethod.GET)
    public String getStr(String key){
        Jedis jedis=redisUtils.getReadRedis();
        String value= jedis.get(key);
        jedis.close();
        System.out.println("要获取的key:"+key+" 获取到的值"+value);
        return value;
    }
    @RequestMapping(value = "/delete",method = RequestMethod.GET )
    public void delete(String key){
        System.out.println("要删除的key:"+key);
        Jedis jedis=redisUtils.getReadRedis();
        Long value= jedis.del(key);
        jedis.close();
        System.out.println("删除情况  1 删除成功  0  删除失败  "+value);
    }
}
