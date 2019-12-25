package com.example.redisdemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisDemoApplicationTests {


    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void contextLoads() {
    }

    /**
     * RedisTemplate的Pipeline的执行
     * @return
     */
    @Test
    public void testPipeline(){

        List<Object> list = redisTemplate.executePipelined(new RedisCallback<Object>() {

            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {

                redisConnection.set("aa".getBytes(),"123".getBytes());
                redisConnection.set("bb".getBytes(),"123".getBytes());
                redisConnection.get("cc".getBytes());
                redisConnection.get("aa".getBytes());

                /**
                 * 注意这里如果成功，或者没有取消 pipeline，必须返回null，否则为失败，下面是源码
                 *     if (result != null) {
                 *                     throw new InvalidDataAccessApiUsageException("Callback cannot return a non-null value as it gets overwritten by the pipeline");
                 *     }
                 */
                return null;
            }
        });

        for (Object obj : list){
            System.out.println("----------"+obj);
        }

    }

}
