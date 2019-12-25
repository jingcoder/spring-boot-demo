
关于kafka的操作API都是根据kafka命令去提供的，所以可以通过命令去了解清楚API的使用

### 1. topic的操作API

 kafka 的 topic 操作命令是 `./kafka-topics.sh `

**examle**

创建一个副本数量为1，分区数量为1的topic

`./kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic testing`

修改topic的分区数量、副本分配，topic的一些属性


