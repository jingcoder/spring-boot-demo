package com.example.redisdemo.demo;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author xu.xiaojing
 * @Date 2018/11/18 9:04
 * @Email xu.xiaojing@frontsurf.com
 * @Description  Jedis直连
 *
 * 注意： 在Jedis中，直连都是现场不安全的；
 */

public class JedisDemo {


    String host = "fsnn1";
    int port = 6379;
    int timeout = 1000;
    boolean ssl = false;
    //Redis 数据库 序号
    int db = 5;

    /**
     * Jedis的单链接
     */
    public void testOneCollection(){

        // jedis 的API 与 Redis的命令很相似,而且几乎支持所有的redis命令
        Jedis jedis = new Jedis(host,port,timeout,ssl);

        //清空redsi
        jedis.flushDB();

        //选择数据库
        jedis.select(db);
        //String操作 当值不存在时，新增
        jedis.setnx("jing","123");

        //hash的操作
        jedis.hset("map","qianxin","123");

        //list的操作
        jedis.rpush("mylist","aa","bb","cc");

        //set操作
        jedis.sadd("myset","aa","bb","cc");

        //zset操作
        jedis.zadd("myzset",1,"bb");


        //关闭连接
        jedis.close();
    }


}
