package com.example.redisdemo.demo;

import org.junit.Assert;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPipeline;

import javax.validation.constraints.AssertTrue;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author xu.xiaojing
 * @Date 2018/11/19 9:20
 * @Email xu.xiaojing@frontsurf.com
 * @Description ShardedJedis的DEMO
 *
 * ShardedJedis 简介
 *  1. ShardedJedis实现的是客户端分片，因为Redis3.0版本之前不支持集群模式，想要在程序中使用分布式的多个redis，就得客户端进行分片（达到横向扩展的目目的）；
 *   因为本质上，就是在客户端对KEY进行Hash，从而确定是哪个redis，然后直接连上这个Redis执行命令，所以也有人叫分布式直连；
 *
 *   2. 注意： 在Jedis中，直连都是现场不安全的；
 */

public class ShardedJedisDemo {


    String host = "fsnn1";
    int port = 6379;
    int connectTimeout = 1000;
    int soTimeout = 1000;
    boolean ssl = false;
    //Redis 数据库 序号
    int db = 5;

    @Test
    public void testShardedJedis(){
        List<JedisShardInfo> list = new LinkedList<>();
        //指定redis的db（数据库）好像只能通过uri设置
        //还可以设置每个Redis的权重
        int weight = 1;
        JedisShardInfo jedisShardInfo = new JedisShardInfo(host,port,connectTimeout,soTimeout,weight,ssl);
        list.add(jedisShardInfo);

        /**
         * ShardedJedis可以配置两种策略：
         *
         * 1. 哈希算法：支持MURMUR_HASH 和MD5两种算法
         * 2. 指定key的分布策略：传入keyTagPattern，所有能够匹配keyTagPattern的key（通过正则匹配）将放在同一个redis里，默认的是直接使用key来进行判定。
         *    Redis自带了一个Sharded.keyTagPattern。
         */
        ShardedJedis shardedJedis = new ShardedJedis(list);



        shardedJedis.set("aa","123");

        //可以直接获取 KEY （存储位置）对应的Redis
        Jedis jedis = shardedJedis.getShard("aa");

        System.out.println("----------------------"+jedis.get("aa"));
        System.out.println("---------------------"+shardedJedis.get("aa"));

        //单连接关闭
        shardedJedis.disconnect();

        //逐个检查 Jedis连接是否关闭
        for (Jedis je : shardedJedis.getAllShards()) {
            Assert.assertTrue(!je.isConnected());
        }
    }

    /**
     *  客户端分片中的管道
     */
    @Test
    public void test2(){
        List<JedisShardInfo> list = new LinkedList<>();
        //还可以设置每个Redis的权重
        int weight = 1;
        JedisShardInfo jedisShardInfo = new JedisShardInfo(host,port,connectTimeout,soTimeout,weight,ssl);
        list.add(jedisShardInfo);

        ShardedJedis shardedJedis = new ShardedJedis(list);

        //获取ShardedJedis的 Pipeline
        ShardedJedisPipeline pipeline =  shardedJedis.pipelined();
        pipeline.set("bbb","123");
        pipeline.set("ccc","123");
        // pipeline执行
        List<Object> result = pipeline.syncAndReturnAll();
        for(Object obj : result){
            System.out.println("-------------------"+obj);
        }
        shardedJedis.close();
    }
}
