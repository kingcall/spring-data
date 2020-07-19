package com.kingcall.redistemplate.Controller;

import com.kingcall.redistemplate.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@Api(value = "RedisController", description = "Redis 接口文档")
@RestController
@RequestMapping("/redis")
public class RedisController {
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    @Qualifier("redisTemplate")
    RedisTemplate redisTemplate;

    @GetMapping("/string/set")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "key", value = "待操作的key", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "value", value = "待设置的value", required = true, dataType = "String")
    })
    @ApiOperation(value="设置key-value 字符串")
    public String stringSet(String key,String value){
        stringRedisTemplate.opsForValue().set(key,value);
        return stringRedisTemplate.opsForValue().get(key);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "key", value = "待操做list的key", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "value", value = "待设置的value", required = true, dataType = "String")
    })
    @ApiOperation(value="设置 list 元素")
    @GetMapping("/list/set")
    public String listSet(String key,String value){
        stringRedisTemplate.opsForList().leftPush(key, value);
        return stringRedisTemplate.opsForList().index(key, 0);
    }

    @ApiOperation(value="查看 list 的长度")
    @ApiImplicitParam(paramType="query", name = "key", value = "待查看list的key", required = true, dataType = "String")
    @GetMapping("/list/len")
    public Long listLen(String key){
        return stringRedisTemplate.opsForList().size(key);
    }

    @ApiOperation(value="查看 list 的元素")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "key", value = "待操做list的key", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "start", value = "起始", required = true, dataType = "long"),
            @ApiImplicitParam(paramType="query", name = "end", value = "截止", required = true, dataType = "long"),
    })

    @GetMapping("/list/range")
    public List<String> listRange(String key, long start, long end){
        return stringRedisTemplate.opsForList().range(key,start,end);
    }

    @ApiOperation(value = "设置hash",notes = "给hash添加元素")
    @GetMapping("/hash/set")
    public void hashSet(String hash, String filed, String value){
         stringRedisTemplate.opsForHash().put(hash,filed,value);
    }

    @ApiOperation(value = "发布",notes = "通过特定channel 发布信息")
    @GetMapping("/publish")
    public void publish(String channel, String message){
        stringRedisTemplate.convertAndSend(channel,message);
    }
    @ApiOperation(value = "设置Object对象",notes = "使用RedisTemplate 设置对象作为value ")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "key", value = "待操做的key", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "id", value = "用户ID", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "name", value = "用户姓名", required = true, dataType = "String"),
    })
    @GetMapping("/string/obj/set")
    public void objSet(String key, String id,String name){
        Integer userid = Integer.parseInt(id);
        redisTemplate.opsForValue().set(key,new User(userid,userid,name,new Date()));
    }

    /**
     * 不能正确获取到 redisTemplate 设置的信息
     * @param key
     * @return
     */
    @GetMapping("/string/obj/get")
    public String objGet(String key){
        return stringRedisTemplate.opsForValue().get(key);
    }
    @GetMapping("/string/obj/get2")
    public String objGet2(String key){
        return ((User) redisTemplate.opsForValue().get(key)).toString();
    }

    /**
     * 这种方式才能正确的获取到对象的信息
     * @param key
     * @return
     */
    @GetMapping("/string/obj/get3")
    public User objGet3(String key){
        return (User) redisTemplate.opsForValue().get(key);
    }

}
