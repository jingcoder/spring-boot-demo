package com.example.demo.topic;

import kafka.admin.AdminUtils;
import kafka.admin.RackAwareMode;
import kafka.utils.ZkUtils;

import java.util.Properties;

/**
 * @Author xu.xiaojing
 * @Date 2018/12/25 21:43
 * @Email xu.xiaojing@frontsurf.com
 * @Description  topic 操作的工具類
 *
 */

public class KafkaTopicUtil {


    ZkUtils zkUtils;

    /**
     * 创建一个topic
     * @param topicName topic 名称
     * @param partition 分区数量
     * @param replication 副本数量，注意副本数量<= broker数量，否则会失败
     * @param properties topic的属性
     * @param rackAwareMode 机构模式
     * @return
     */
    public boolean createTopic(String topicName, int partition,int replication, Properties properties, RackAwareMode rackAwareMode){

        try{
            AdminUtils.createTopic(zkUtils,topicName,partition,replication,properties,rackAwareMode);
        }catch (Exception e){
            //创建topic可能会出错，然而不会返回false,只会抛异常；
            System.out.println("创建topic失败，Execption："+e);
            return false;
        }
        return true;
    }

    /**
     * 快速创建一个简单的topic，分区数为1，副本为1，开启不同机构模式（分区之间尽可能不会在同一台机构上）
     * @param topicName
     * @return
     */
    public boolean createDefaultTopic(String topicName){
        return this.createTopic(topicName,1,1,null,RackAwareMode.Enforced$.MODULE$);
    }

    /**
     * 刪除topic
     * 注意：必須要在 server.properties中要配置delete.topic.enable=true
     * 否則，是沒有真正刪除的，只是  marked for deletion
     * @param topicName
     * @return
     */
    public void deleteTopic(String topicName){

        AdminUtils.deleteTopic(zkUtils,topicName);
    }

    /**
     * 刪除分区
     * @param brokerId kafka节点
     * @param topicName
     */
    public void deletePartition(int brokerId,String topicName){

        zkUtils.deletePartition(brokerId,topicName);
    }


    public void changeTopicProperties(String topicName,Properties properties){
        AdminUtils.changeTopicConfig(zkUtils,topicName,properties);
    }




}
