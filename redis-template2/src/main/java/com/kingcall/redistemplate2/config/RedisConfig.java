package com.kingcall.redistemplate2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.net.UnknownHostException;

@Configuration
public class RedisConfig {
	/**
	 * Spring 将操作 Redis 的 API 封装成了 Template，我们使用最多的就是上面例子中的那个 StringRedisTemplate，
	 * 还有一个是 RedisTemplate。StringRedisTemplate 用于 Key 和 Value 都是字符串的情况，这也是我们平时使用最多的场景
	 *
	 * RedisTemplate 则是一个相对通用的 API，不仅可以处理字符串的情况，还可以处理自定义对象等复杂类型。RedisTemplate 默认是采用 JDK 的序列化方式来转换对象的，当然我们还可以根据需要自定义序列化的方式。
	 * @param redisConnectionFactory
	 * @return
	 */
	@Bean
	public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
		StringRedisTemplate stringRedisTemplate = new StringRedisTemplate(redisConnectionFactory);
		return stringRedisTemplate;
	}

}
