package com.example.demo;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.Future;

import static org.junit.Assert.*;

/**
 * @Author xu.xiaojing
 * @Date 2018/12/18 0:01
 * @Email xu.xiaojing@frontsurf.com
 * @Description
 */

public class RegisterCenterInvokerTest {

    @Test
    public void testGet(){
        ZkClient zkClient = new ZkClient("fsmanager:2181");
        List<String> children = zkClient.getChildren("/brokers");
        for (String item : children){
            System.out.println(item);
        }
    }

    @Test
    public void testCreateNodes(){
        ZkClient zkClient = new ZkClient("fsmanager:2181");
        //创建节点,父节点必须是持久节点
        zkClient.create("/root","serverr data",CreateMode.PERSISTENT);
        //创建子节点，子节点可以是 临时节点
        zkClient.create("/root/server1","server child node data",CreateMode.EPHEMERAL);
        //创建子节点
        zkClient.create("/root/server2","server child node data",CreateMode.EPHEMERAL);
        //获取子节点
        List<String> children = zkClient.getChildren("/root");
        for(String node : children){
            System.out.println(node);
        }

        //写入数据
        zkClient.writeData("/root/server1", "我是server节点1");

        //读取节点的数据
        Object object = zkClient.readData("/root/server1");
        System.out.println(object);
    }




    /**
     * ZkClient 将 Zookeeper的watcher机制转化为一种更容易理解的订阅形式，并且订阅是保持的，而非一次性的
     */
    @Test
    public void testSubscribe(){
        ZkClient zkClient = new ZkClient("fsmanager:2181");
        zkClient.create("/root/server1","server child node data",CreateMode.EPHEMERAL);
        zkClient.subscribeDataChanges("/root/server1", new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {
                System.out.println("数据发生变化："+o);
                Thread.sleep(2000);
            }

            @Override
            public void handleDataDeleted(String s) throws Exception {
            }
        });

        //写入数据
        zkClient.writeData("/root/server1", "我是server节点1,哈哈哈0000");
        zkClient.writeData("/root/server1", "我是server节点1,哈哈哈1111");
        zkClient.writeData("/root/server1", "我是server节点1,哈哈哈2222");
        zkClient.writeData("/root/server1", "我是server节点1,哈哈哈3333");
        zkClient.writeData("/root/server1", "我是server节点1,哈哈哈4444");

        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



}