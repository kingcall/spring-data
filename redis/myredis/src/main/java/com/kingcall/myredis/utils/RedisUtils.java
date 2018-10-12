package com.kingcall.myredis.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.Serializable;

/**
 * 数据库连接通用类
 * 但是不应该将这个东西注入，注入之后每次调用都会创建新的池子，或者redis client，这样就没有意义，所以正确的做法是将他的实例注入
 */
public class RedisUtils implements Serializable {
    private static final long serialVersionUID = 6703303999874064656L;
    
    private static JedisPool writePool = null;
    private static JedisPool readPool = null;
    private String writeHost;
    private String readHost;
    private int port;
    
    public RedisUtils(String writeHost, String readHost, int port) {
        this.writeHost = writeHost;
        this.readHost = readHost;
        this.port = port;
        writePool = getRedisPool(writeHost, port);
        readPool = getRedisPool(readHost, port);
    }
    
    /**
     * 获取jedis 连接池,开放了连接，但是没有开放池子
     *
     * @param host redis host
     * @param port redis port
     * @return JedisPool
     */
    private JedisPool getRedisPool(String host, int port) {
        JedisPoolConfig jedisConfig = initJedisConf();
        return new JedisPool();
    }
    
    /**
     * 获取read redis connection
     *
     * @return read Jedis
     */
    public Jedis getReadRedis() {
        if (readPool != null) {
            return readPool.getResource();
        }
        readPool = getRedisPool(readHost, port);
        return readPool.getResource();
    }
    
    
    /**
     * write redis connection
     *
     * @return write Jedis
     */
    public Jedis getWriteRedis() {
        if (writePool != null) {
            return writePool.getResource();
        }
        writePool = getRedisPool(writeHost, port);
        return writePool.getResource();
    }
    
    private JedisPoolConfig initJedisConf() {
        JedisPoolConfig conf = new JedisPoolConfig();
        return conf;
    }
}
