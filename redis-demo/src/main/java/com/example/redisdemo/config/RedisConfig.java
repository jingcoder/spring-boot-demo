package com.example.redisdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @Author xu.xiaojing
 * @Date 2018/11/17 22:56
 * @Email xu.xiaojing@frontsurf.com
 * @Description
 */

@Configuration
public class RedisConfig {

        @Bean
        public RedisTemplate<Object,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) throws Exception {
            RedisTemplate redisTemplate = new RedisTemplate();
            redisTemplate.setConnectionFactory(redisConnectionFactory);
            // KEY 值一般都是String类型，所以采用 StringRedisSerializer 类
            redisTemplate.setKeySerializer(new StringRedisSerializer());
            //设置Hash 的KEY的序列化
            redisTemplate.setHashKeySerializer(new StringRedisSerializer());

            Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
            //设置VALUE 的序列化
            redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
            //设置Hash 的VALUE的序列话
            redisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer(Object.class));


            redisTemplate.afterPropertiesSet();
            return redisTemplate;
        }
}
