package com.kingcall.cache.Controller;

import com.kingcall.cache.entity.SimpleCache;
import com.kingcall.cache.service.SimpleCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/simple")
public class SimpleCacheController {
    @Autowired
    SimpleCacheService service;
    @RequestMapping("/get")
    public SimpleCache getCache(String name){
        return service.getCache(name);
    }
    @RequestMapping("/num")
    public int getCache(){
        return service.getNum();
    }

}
