package com.example.demo.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;

/**
 * @Author xu.xiaojing
 * @Date 2018/12/26 9:59
 * @Email xu.xiaojing@frontsurf.com
 * @Description
 */

public class KafkaConsumerDemo {



    public static void main(String[] args) {
//        KafkaConsumer kafkaConsumer = new KafkaConsumer();
       KafkaConsumer consumer = KafkaConsumerConfig.createDefaultKafkaConsumer();
       //订阅多个topic
//        consumer.subscribe(Arrays.asList("foo", "bar"));

        consumer.subscribe(Arrays.asList("test4"));

        while(true){
//            consumer.
            ConsumerRecords<Object,Object> records = consumer.poll(1000);
            for(ConsumerRecord<Object,Object> record : records){
                System.out.println("----------------------------------" + record.value());
            }
        }
    }

}
