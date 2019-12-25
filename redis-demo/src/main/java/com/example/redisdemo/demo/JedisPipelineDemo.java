package com.example.redisdemo.demo;

import javafx.beans.binding.StringBinding;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

import javax.validation.constraints.Null;
import java.util.List;

/**
 * @Author xu.xiaojing
 * @Date 2018/11/18 23:24
 * @Email xu.xiaojing@frontsurf.com
 * @Description  Jedis 的pipeline的DEMO
  */

public class JedisPipelineDemo {

    /**
     * 批量获取结果
     */
    @Test
    public void testPipeline(){
        Jedis jedis = new Jedis("fsnn1",6379,1000,false);

        jedis.select(5);
        //获取一个Pipeline
        Pipeline pipeline = jedis.pipelined();

        //批量写
        for (int i = 0; i < 10; i++) {
            pipeline.set("p"+i,"p"+i);
        }

        //同步执行pipeline批量命令，并返回所有的执行结果
        List<Object> list = pipeline.syncAndReturnAll();
        jedis.close();

        for(Object item : list){
            System.out.println(item);
        }

        System.out.println("-------"+list.size());
    }

    /**
     * 单独获取结果
     */
    @Test
    public void testPipeline2(){
        Jedis jedis = new Jedis("fsnn1",6379,1000,false);

        //获取一个Pipeline
        Pipeline pipeline = jedis.pipelined();

        //下面这些方法都是异步的，相当于Future
        Response<String> result = pipeline.set("aa","123");
        Response<String> aa = pipeline.get("aa");
        Response<byte[]> bb = pipeline.get("bb".getBytes());
        Response<Boolean> cc = pipeline.exists("cc");
        //提交批量命令
        pipeline.sync();
        //关闭连接
        jedis.close();
        //获取结果
        System.out.println(result.get());
        System.out.println(aa.get());
        System.out.println(bb.get());
        System.out.println(cc.get());
    }

    /**
     * pipeline中使用事务 DEMO
     */
    @Test
    public void testpipeline3(){
        Jedis jedis = new Jedis("fsnn1",6379,1000,false);

        //获取一个Pipeline
        Pipeline pipeline = jedis.pipelined();

        //事务开始
        pipeline.multi();
        for (int i = 0; i < 10; i++) {
            pipeline.set("p"+i,"p"+i);
        }
        //事务结束
        Response<List<Object>> response = pipeline.exec();
        //同步执行pipeline批量命令，并返回所有的执行结果
        List<Object> list = pipeline.syncAndReturnAll();
        jedis.close();

        for(Object item : response.get()){
            System.out.println("-------"+item);
        }

        for(Object item : list){
            System.out.println(item);
        }

        System.out.println("-------"+list.size());
    }
}
