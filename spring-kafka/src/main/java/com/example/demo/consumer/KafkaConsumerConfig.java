package com.example.demo.consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @Author xu.xiaojing
 * @Date 2018/12/26 9:59
 * @Email xu.xiaojing@frontsurf.com
 * @Description kafka消费者的配置、创建demo,其中的配置有值的都是默认值
 */

public class KafkaConsumerConfig {

    //只能写hostname，kafka间的通信好像都是hostname
    static String servers = "fsdn1:6667";
    static int autoCommitInterval = 1000;
    static String groupId = "consumer-group-test";
    static int fetchMaxWait = 500;


    public static Map<String, Object> defalutConsumerConfigs() {
        Map<String, Object> propsMap = new HashMap<>();
        //kafka节点
        propsMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, servers);
        //是否自动提交offset,设置后，将会定期提交offset
        propsMap.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        //自动提交offset的时间间隔，注意这里会有一个坑，如果消息的处理（特别是批量拉取），超过offset的时间间隔，就会出现异常，因为offset一到时间，就会尝试提交offset，而此时消息还没处理完成，offset提交失败。
        propsMap.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, autoCommitInterval);


        //键、值序列化方式
        propsMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        propsMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        // consumer group
        propsMap.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);

        //offset没有（不存在）时，重置策略：
        // earliest（将偏移量自动重置为最初的值）、
        // latest（自动将偏移量置为最新的值）、
        // none（如果在消费者组中没有发现前一个偏移量，就向消费者抛出一个异常）、
        // anything else（向消费者抛出异常）
        propsMap.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");


        //kafka集群每个分区一次返回的最大数据量。
        // 一次请求的最大内存使用量应该等于#partitions * max.partition.fetch.bytes。
        //这个值必须与kafka集群允许的最大消息数据量差不多大小，否则可能生产者发送了一个消息，大于消费者配置的值。
        propsMap.put(ConsumerConfig.MAX_PARTITION_FETCH_BYTES_CONFIG, 1048576);

        //下面是批量拉取的限制条件，满足下面的任意一个条件，poll操作都会结束

        //一次poll调用的最大消息条数
        propsMap.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG,2147483647);
        // 一次poll的最大等待时间
        propsMap.put(ConsumerConfig.FETCH_MAX_WAIT_MS_CONFIG,fetchMaxWait);
        //-次poll的最少数据量，达不到，就一直等待上面两个条件达到，就退出
        propsMap.put(ConsumerConfig.FETCH_MIN_BYTES_CONFIG,1);

        //高级设置,大部分情况下，不用管

        //会话超时,如果在指定时间内，没有接受到心跳，broker把消费者设置为无效
        propsMap.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 30000);
        //会话心跳间隔
        propsMap.put(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG,3000);
        //空闲的连接的关闭时间
        propsMap.put(ConsumerConfig.CONNECTIONS_MAX_IDLE_MS_CONFIG,540000);

        //是否允许订阅内部主题。（如__consumer.offsets用于保存消费者群组的便宜量）
        propsMap.put(ConsumerConfig.EXCLUDE_INTERNAL_TOPICS_CONFIG,true);

        //使用组管理时，客户端使用的分区策略的类名，根据这个策略来进行消费分区。
        propsMap.put(ConsumerConfig.PARTITION_ASSIGNMENT_STRATEGY_CONFIG,"org.apache.kafka.clients.consumer.RangeAssignor");
        //一次请求等待响应超时时间。如果在超时时间内未得到响应，kafka要么重发这条消息，要么超过重试次数的情况下直接置为失败。
        propsMap.put(ConsumerConfig.REQUEST_TIMEOUT_MS_CONFIG,40000);
        //SO_RCVBUF读取数据使用的内存大小。
        propsMap.put(ConsumerConfig.RECEIVE_BUFFER_CONFIG,65536);

        return propsMap;
    }


    /**
     * 创建一个kafka消费者
     * @return
     */
    public static KafkaConsumer createDefaultKafkaConsumer(){
        KafkaConsumer consumer = new KafkaConsumer(KafkaConsumerConfig.defalutConsumerConfigs());

        return consumer;
    }
}
