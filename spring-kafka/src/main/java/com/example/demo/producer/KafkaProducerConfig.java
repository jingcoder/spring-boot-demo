package com.example.demo.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author xu.xiaojing
 * @Date 2018/12/26 15:46
 * @Email xu.xiaojing@frontsurf.com
 * @Description
 */

public class KafkaProducerConfig {

    static String servers = "fsdn1:6667";

    public static Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, servers);
        //失败重试次数。
        //如果不把max.in.flight.requests.per.connection设为1，重试可能会改变消息的顺序。
        props.put(ProducerConfig.RETRIES_CONFIG, 0);
        //设置消息的顺序性，但只能针对同一个partition，
        props.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION,5);


        //TCP的接收缓存 SO_RCVBUF 空间大小，用于读取数据
        props.put(ProducerConfig.RECEIVE_BUFFER_CONFIG, 32768);
        //TCP的发送缓存 SO_SNDBUF 空间大小，用于发送数据
        props.put(ProducerConfig.SEND_BUFFER_CONFIG,131072);

        //Producers用来缓存数据的内存大小，如果数据产生的速度比kafka发送速度快，可能会发生内存不足的情况，此时producer会阻塞 max.block.ms，超时则抛出异常（仍然没有空间）
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG,33554432);
        //当buffer空间不够或者metadata丢失时，生产将进行阻塞的时间
        props.put(ProducerConfig.MAX_BLOCK_MS_CONFIG,60000);

        //每个请求的最大字节数，即对消息的大小进行限制。kafka默认是1M，也可以单独对topic进行设置
        props.put(ProducerConfig.MAX_REQUEST_SIZE_CONFIG, 1048576);
        //请求超时设置
        props.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG,30000);
        //请求压缩类型：none，gzip，snappy，lz4
        props.put(ProducerConfig.COMPRESSION_TYPE_CONFIG,"none");

        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        //producer需要等待server完成请求的确认数量；
        // acks=0时，producer不会等待；
        // acks=1时，等待leader写到local log就行了；
        // acks=all 或 acks = -1 时，等待isr中所有的副本确认
        props.put(ProducerConfig.ACKS_CONFIG, "1");
        //指定server等待来自followers的确认的最大时间（即 根据acks类型，服务器的等待机器的确认数量的超时，即是在服务器上测量，不包含网络延时，超时返回给producer错误信息），
        props.put(ProducerConfig.TIMEOUT_CONFIG,30000);

        //批量发送的触发条件，满足下面两个条件之一，就会将多个请求批量发送，但注意，批量会有点时间损耗（linger）
        //一次批量的最大请求数，为0，则不会进行批量处理
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
        //批量请求的最大聚合时间（延迟发送），0则不会延迟。
        props.put(ProducerConfig.LINGER_MS_CONFIG, 0);
        return props;
    }

    public static KafkaProducer createDefaultKafkaProducer(){

        KafkaProducer producer = new KafkaProducer(KafkaProducerConfig.producerConfigs());
        return producer;
    }
}
