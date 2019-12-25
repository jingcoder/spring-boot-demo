package com.example.demo.topic;

import kafka.admin.AdminUtils;
import kafka.admin.BrokerMetadata;
import kafka.admin.RackAwareMode;
import kafka.utils.ZkUtils;
import org.apache.kafka.common.security.JaasUtils;
import scala.Option;
import scala.collection.mutable.LinkedList;

import java.util.Properties;

/**
 * @Author xu.xiaojing
 * @Date 2018/12/25 16:58
 * @Email xu.xiaojing@frontsurf.com
 * @Description
 */


public class TopicOperate {



    public static void main(String[] args) {
//        ZkClient
        System.out.println("-----------"+JaasUtils.isZkSecurityEnabled());
       ZkUtils zkUtils = ZkUtils.apply("192.168.100.51:2181",3000,3000,JaasUtils.isZkSecurityEnabled());



        Properties properties = new Properties();
//      properties.setProperty("max.message.bytes","2000000");
        properties.setProperty("delete.retention.ms","120000");
//      properties.setProperty("retention.ms","120000");
//      properties.setProperty("cleanup.policy","delete");
//        AdminUtils.createTopic(zkUtils, "test6", 1, 1, properties, RackAwareMode.Safe$.MODULE$);


        AdminUtils.changeTopicConfig(zkUtils,"test4",properties);

        BrokerMetadata broker1 = new BrokerMetadata(1001,Option.apply("rack1"));
        BrokerMetadata broker2 = new BrokerMetadata(1002,Option.apply("rack2"));
        LinkedList<Object> seq = new LinkedList<>();
        seq.elem_$eq(broker1);
//        Seq<BrokerMetadata> brokerMetadataSeq = AdminUtils.getBrokerMetadatas(zkUtils,RackAwareMode.Enforced$.MODULE$,Option.apply(seq));
//       Map<Object,Seq<Object>> map =  AdminUtils.assignReplicasToBrokers(brokerMetadataSeq,2,2,1,1);
//        AdminUtils.deleteTopic(zkUtils,"t3");
        //
//        zkUtils.deletePartition(1001,"t4");

//        AdminUtils.createTopic$default$5();

        zkUtils.close();
    }

    public void createTopic(String topicName){

        Properties properties = new Properties();
//        properties.
    }
}
