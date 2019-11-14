


## 概念专业术语

- Producer
消息生产者, 负责产生消息, 一般由业务系统负责产生消息。

- Consumer
消息消费者, 负责消费消息, 一般是后台系统负责异步消费。

- Push Consumer
Consumer 的一种, 应用通常向 Consumer 对象注册一个 Listener 接口, 一旦收到消息, Consumer 对象立刻回调 Listener 接口方法。

- Pull Consumer
Consumer 的一种, 应用通常主动调用 Consumer 的拉消息方法从 Broker 拉消息, 主动权由应用控制。

- Producer Group
一类 Producer 的集合名称, 这类 Producer 通常发送一类消息, 且发送逻辑一致。

- Consumer Group
一类 Consumer 的集合名称, 这类 Consumer 通常消费一类消息, 且消费逻辑一致。

- Broker
消息中转角色, 负责存储消息, 转发消息, 一般也称为 Server。在 JMS 规范中称为 Provider。


## Producer（生产者）
生产者支持分布式部署。分布式生产者通过多种负载均衡模式向 Broker 集群发送消息。发送过程支持快速失败并具有低延迟。


## NameServer
提供轻量级服务发现和路由, 每个 Name Server 记录完整的路由信息, 提供相应的读写服务, 支持快速存储扩展。
主要包括两个功能:
    - 代理管理,  NameServer 接受来自 Broker 集群的注册, 并提供检测代理是否存在的心跳机制
    - 路由管理, 每个 NameServer 将保存有关代理群集的全部路由信息以及客户端查询的队列信息
    
RocketMQ客户端（生产者/消费者）将从NameServer查询队列路由信息, 但客户端如何找到NameServer地址？
将NameServer地址列表提供给客户端有四种方法: 
    1. 编程方式, 就像`producer.setNamesrvAddr("ip:port")`
    2. Java选项, 使用`rocketmq.namesrv.addr`
    3. 环境变量, 使用`NAMESRV_ADDR`
    4. HTTP 端点
    

## Broker
Broker 通过提供轻量级的 Topic 和 Queue 机制来支持消息存储。
支持 Push 和 Pull 模式, 包含容错机制（2个拷贝或者3个拷贝）, 并且提供了强大的峰值填充和以原始时间顺序累计数千亿条消息的能力。
此外, broker 还提供灾难恢复, 丰富的指标统计数据和警报机制, 而传统的消息传递系统都缺乏这些机制。

Broker 服务器重要的子模块: 
    - 远程处理模块是 broker 的入口，处理来自客户的请求。
    - Client manager，管理客户（生产者/消费者）并维护消费者的主题订阅。
    - Store Service，提供简单的 API 来存储或查询物理磁盘中的消息。
    - HA 服务，提供主代理和从代理之间的数据同步功能。
    - 索引服务，通过指定键为消息建立索引，并提供快速的消息查询。


## Consumer（消费者）
消费者也支持 Push 和 Pull 模型中的分布式部署。
还支持群集消费和消息广播。提供了实时的消息订阅机制，可以满足大多数消费者的需求。
