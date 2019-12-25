package com.example.redisdemo.demo;

import org.junit.Test;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author xu.xiaojing
 * @Date 2018/11/20 23:09
 * @Email xu.xiaojing@frontsurf.com
 * @Description  客户端分片连接池（因为直连都是线程不安全的，所以在多想做中就需要使用线程池了）
 */

public class ShardedJedisPoolDemo {

    String host = "fsnn1";
    int port = 6379;
    int connectTimeout = 1000;
    int soTimeout = 1000;
    boolean ssl = false;
    //Redis 数据库 序号
    int db = 5;


    @Test
    public void test1(){
        List<JedisShardInfo> list = new LinkedList<>();
        //指定redis的db（数据库）好像只能通过uri设置
        //还可以设置每个Redis的权重
        int weight = 1;
        JedisShardInfo jedisShardInfo = new JedisShardInfo(host,port,connectTimeout,soTimeout,weight,ssl);
        list.add(jedisShardInfo);

        // 创建一个分布式直连的线程池，这里简单地使用一个线程池配置，更加详细的配置参考 JedisPoolDemo
        ShardedJedisPool shardedJedisPool = new ShardedJedisPool(new JedisPoolConfig(),list);

        //从线程池中获取一个连接
        ShardedJedis shardedJedis = shardedJedisPool.getResource();

        System.out.println("------------"+shardedJedis.get("aa"));

        //必须使用close，而非disConnected
        shardedJedis.close();
    }
}
