package com.example.demo.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @Author xu.xiaojing
 * @Date 2018/12/26 17:24
 * @Email xu.xiaojing@frontsurf.com
 * @Description
 */

public class KafkaProducerDemo {

    public static void main(String[] args) {
        KafkaProducer kafkaProducer = KafkaProducerConfig.createDefaultKafkaProducer();
        /*for (int i = 0; i < 50; i++) {
            ProducerRecord records = new ProducerRecord("test1","哈哈哈，这是消息：00"+i);
           Future future = kafkaProducer.send(records);
        }*/

        for (int i = 10; i < 20; i++) {
            ProducerRecord records = new ProducerRecord("test6","哈哈哈，这是消息：00"+i);
            Future future = kafkaProducer.send(records);
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
