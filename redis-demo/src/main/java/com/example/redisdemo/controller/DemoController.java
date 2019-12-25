package com.example.redisdemo.controller;

import com.example.redisdemo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.*;
import java.util.List;

/**
 * @Author xu.xiaojing
 * @Date 2018/11/17 22:34
 * @Email xu.xiaojing@frontsurf.com
 * @Description
 */

@RestController
public class DemoController {

    @Autowired
    RedisTemplate<Object,Object> redisTemplate;

    /**
     * StringRedisTemplate 的 KEY、VALUE均采用 StringRedisSerializer 序列化，就是个字符串，人工可读
     */
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    RedisConnectionFactory redisConnectionFactory;

    @GetMapping("/get")
    public String getValue(){

        // 写List列表，存储的值："123","456","789"
        redisTemplate.opsForList().rightPushAll("bb","123","456","789");
        //写KEY-VALUE
        redisTemplate.opsForValue().set("cc","123");
        //存储一个对象：{"id":1,"userName":"xiaoming","password":"123"}
        redisTemplate.opsForValue().set("user",new User());

        //存储一个 hash
        redisTemplate.opsForHash().put("map","jack",new User(2,"jack","123"));
        redisTemplate.opsForHash().put("map","TOM",new User(3,"TOM","123"));
        System.out.println("redisTemplat----" + redisTemplate.getKeySerializer());
        //

        //
        stringRedisTemplate.opsForValue().set("aa","123");
        return stringRedisTemplate.opsForValue().get("aa");
    }

    @GetMapping("/factory")
    public String opFactory(){

        //获取单个redis链接池的一个链接
        RedisConnection redisConnection = redisConnectionFactory.getConnection();
        //获取 Redis集群的链接
//        RedisClusterConnection redisClusterConnection = redisConnectionFactory.getClusterConnection();
        //获取哨兵模式的链接
//        RedisSentinelConnection redisSentinelConnection = redisConnectionFactory.getSentinelConnection();

        // 与使用redisTemplate是一样的效果
        redisConnection.set("ee".getBytes(),"123456".getBytes());

        return redisConnection.get("ee".getBytes()).toString();
    }

    /**
     * RedisTemplate的Pipeline的执行
     * @return
     */
    public String testPipeline(){

       List<Object> list = redisTemplate.executePipelined(new RedisCallback<Object>() {

           @Override
           public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
               // 注意 RedisConnection 是直接连上Redis的，也就是没有经过任何的序列化的，所以要进行序列化；
               // 如果想省掉这一步序列化，除非你所配置的序列化器 是 StringRedisSerializer
               RedisSerializer keySerializer = redisTemplate.getKeySerializer();
               RedisSerializer valueSerializer = redisTemplate.getValueSerializer();
               RedisSerializer hashkeySerializer = redisTemplate.getHashKeySerializer();
               RedisSerializer hashValueSerializer = redisTemplate.getHashValueSerializer();
               redisConnection.set(keySerializer.serialize("aa"),valueSerializer.serialize("123"));
               redisConnection.set(keySerializer.serialize("bb"),valueSerializer.serialize("123"));
               redisConnection.get(keySerializer.serialize("cc"));

               return null;
           }
       });

        for (Object obj : list){
            System.out.println("----------"+obj);
        }

       return "success";
    }
}
