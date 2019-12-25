package com.example.redisdemo.demo;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.List;

/**
 * @Author xu.xiaojing
 * @Date 2018/11/18 22:55
 * @Email xu.xiaojing@frontsurf.com
 * @Description  Jedis的事务
 */

public class JedisTranactions {
    @Test
    public void demo(){
        Jedis jedis = new Jedis("fsnn1",6379,1000,false);
        jedis.select(5);

        Transaction transaction = jedis.multi();

        for (int i = 0; i < 10; i++) {
             transaction.set("ts"+i,"ts"+i);
        }

        List<Object> list = transaction.exec();

        //单连接可以直接调用 disconnect（）
        //当然，也可以调用close()方法，close（）方法底层也是调用disconnect（）；
        jedis.disconnect();

        System.out.println("----------"+list.size()
        );
    }
}
