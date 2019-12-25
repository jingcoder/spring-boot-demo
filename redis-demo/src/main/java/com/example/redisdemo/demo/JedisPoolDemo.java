package com.example.redisdemo.demo;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author xu.xiaojing
 * @Date 2018/11/18 22:53
 * @Email xu.xiaojing@frontsurf.com
 * @Description
 */

public class JedisPoolDemo {

    String host = "fsnn1";
    int port = 6379;
    int timeout = 1000;
    boolean ssl = false;
    //Redis 数据库 序号
    int db = 5;

    /**
     * 创建一个 Redis线程池
     * @return
     */
    JedisPool jedisPool;
    public JedisPool getJedisPool(){

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(20);
        jedisPoolConfig.setMaxIdle(10);
        jedisPoolConfig.setMinIdle(5);

        // 下面的配置都是链接池的配置，都是默认值

        //下面四个默认为false，大部分情况是不开启的，因为性能考虑
        //指明是否在从池中取出连接前进行测试,如果检验失败,则从池中去除连接并尝试取出另一个.
        jedisPoolConfig.setTestOnBorrow(false);
        // 指明是否在归还到池中前进行测试
        jedisPoolConfig.setTestOnReturn(false);
        //空闲回收器 每运行一次，测试校验的链接数量
        jedisPoolConfig.setNumTestsPerEvictionRun(3);
        //是否每一次空闲回收器运行时，进行测试（此处测试的空闲链接数为3个），如果测试链接失败，就回收掉此链接，而不会管此链接的空闲时间是否到来；此处建议开启；
        jedisPoolConfig.setTestWhileIdle(false);

        jedisPoolConfig.setTestOnCreate(false);
        // 每隔N秒运行一次空闲回收器 （这里好像不允许？？？）
        jedisPoolConfig.setTimeBetweenEvictionRunsMillis(-1L);
        // 空闲的链接最少存活时间30分钟(逐出连接的最小空闲时间)
        jedisPoolConfig.setMinEvictableIdleTimeMillis(1800000L);
        //驱逐线程关闭的超时时间,默认10秒
        jedisPoolConfig.setEvictorShutdownTimeoutMillis(10000L);
        //后进先出策略
        jedisPoolConfig.setLifo(true);

        //JMX监控设置
        jedisPoolConfig.setJmxEnabled(true);
        jedisPoolConfig.setJmxNamePrefix("pool");
        jedisPoolConfig.setJmxNameBase(null);

        //创建一个数据库链接池
        //还可以使用uri：JedisPool pool = new JedisPool(new URI("redis://:foobared@localhost:6380/2"));
        JedisPool jedisPool = new JedisPool(jedisPoolConfig ,host,port,timeout,null,db,ssl);

        //从池里获取一个链接
        Jedis jedis = jedisPool.getResource();

        // 可以查看源码，会发现 对线程池的链接来说，是退还链接操作，而不是真的关闭链接；
        //不能直接调用disconnect（）方法，因为此方法是真的关闭连接；
        jedis.close();

        return jedisPool;
    }

    /**
     * 从链接池里面获取一个链接
     * @return
     */
    public Jedis getJedis(){

        if(jedisPool == null){
            jedisPool = this.getJedisPool();
        }

        return jedisPool.getResource();
    }



    public static void main(String[] args) {

       JedisPoolDemo jedisPoolDemo = new JedisPoolDemo();

        /**
         * jedis线程池压测
         */
        List<Thread> list = new LinkedList<>();
        for (int i = 0; i < 1000; i++) {
            Thread thread = new Thread("thread-"+i){
                @Override
                public void run() {
                    while(true){
                        Jedis jedis = jedisPoolDemo.getJedis();
                        String result;
                        try {
                            result = jedis.get("aa");
                        }finally {
                            jedis.close();
                        }
                        System.out.println("------- "+Thread.currentThread().getName()+" ----："+result);
                        jedis.close();
                    }
                }
            };
            list.add(thread);
        }

        for(Thread thread : list){
            thread.start();
        }
    }
}
