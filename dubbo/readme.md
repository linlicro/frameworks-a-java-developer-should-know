# Dubbo 总结

Dubbo 官网：<http://dubbo.apache.org/zh-cn/index.html>

Dubbo 中文文档： <http://dubbo.apache.org/zh-cn/index.html>

## 概念

### Dubbo是什么

Apache Dubbo (incubating) |ˈdʌbəʊ| 是一款高性能、轻量级的开源Java RPC 框架，它提供了三大核心能力：面向接口的远程方法调用，智能容错和负载均衡，以及服务自动注册和发现。

简单来说 Dubbo 是一个分布式服务框架，致力于提供高性能和透明化的RPC远程服务调用方案，以及SOA服务治理方案。

Github 地址：<https://github.com/apache/incubator-dubbo>。

Dubbo 实际上是 RPC 框架。

### 什么是RPC, RPC原理是什么

RPC（Remote Procedure Call）—远程过程调用，它是一种通过网络从远程计算机程序上请求服务，而不需要了解底层网络技术的协议。比如两个不同的服务A,B部署在两台不同的机器上，那么服务 A 如果想要调用服务 B 中的某个方法该怎么办呢？使用 HTTP请求 当然可以，但是可能会比较慢而且一些优化做的并不好。 RPC 的出现就是为了解决这个问题。

![rpc](img/rpc.jpeg)

* 服务消费方（client）调用以本地调用方式调用服务；
* client stub接收到调用后负责将方法、参数等组装成能够进行网络传输的消息体；
* client stub找到服务地址，并将消息发送到服务端；
* server stub收到消息后进行解码；
* server stub根据解码结果调用本地的服务；
* 本地服务执行并将结果返回给server stub；
* server stub将返回结果打包成消息并发送至消费方；
* client stub接收到消息，并进行解码；
* 服务消费方得到最终结果。

### Dubbo 架构

使用 zookeeper 作为注册中心，这也是 Dubbo 官方推荐的一种方式。

![dubbo architecture patterns](dubbo_architecture_patterns.jpeg)

节点说明:

* Provider 暴露服务的服务提供方
* Consumer 调用远程服务的服务消费方
* Registry 服务注册与发现的注册中心
* Monitor 统计服务的调用次数和调用时间的监控中心
* Container 服务运行容器

调用说明:

1. 服务容器负责启动，加载，运行服务提供者。
1. 服务提供者在启动时，向注册中心注册自己提供的服务。
1. 服务消费者在启动时，向注册中心订阅自己所需的服务。
1. 注册中心返回服务提供者地址列表给消费者，如果有变更，注册中心将基于长连接推送变更数据给消费者。
1. 服务消费者，从提供者地址列表中，基于软负载均衡算法，选一台提供者进行调用，如果调用失败，再选另一台调用。
1. 服务消费者和提供者，在内存中累计调用次数和调用时间，定时每分钟发送一次统计数据到监控中心。

### 为什么要用 Dubbo

从 Dubbo 提供的下面四点特性来说为什么要用 Dubbo:

1. 负载均衡——同一个服务部署在不同的机器时该调用那一台机器上的服务。
1. 服务调用链路生成——服务之间互相是如何调用的。
1. 服务访问压力以及时长统计——当前系统的压力主要在哪里，如何来扩容和优化。
1. 服务降级——某个服务挂掉之后调用备用服务。

## 实战#1 zk环境搭建

### zookeeper 环境安装搭建(本地 Mac)#1

采用Docker方式运行 ZooKeeper

```sh
# 下载镜像
docker pull wurstmeister/zookeeper

# 运行容器
docker run -d -p 2181:2181 -p 2888:2888 -p 2222:22 -p 3888:3888 --name zk wurstmeister/zookeeper

# 停止容器
docker stop zk

# 启动容器
docker start zk
```

### zookeeper 环境安装搭建(CentOS 7.4)

通过 <http://mirror.bit.edu.cn/apache/zookeeper/> 这个链接下载，然后上传到Linux上。（可以说那个 Xhell 附带的文件传输功能）。

或者直接在Linux中使用 wget <https://archive.apache.org/dist/zookeeper/zookeeper-3.4.12/zookeeper-3.4.12.tar.gz> 命令下载（版本号 3.4.12 是我写这篇文章的时候最新的稳定版本，各位可以根据实际情况修改）。

```sh
# 解压
tar -zxvf zookeeper-3.4.12-alpha.tar.gz

# 重命名
mv zookeeper-3.4.12 zookeeper

# 删除 zookeeper 安装包
rm -rf zookeeper-3.4.12.tar.gz

# 相关配置
cd /usr/local/zookeeper

# 创建data文件夹
mkdir data

# 复制zoo_sample.cfg，命名为zoo.cfg
cp zoo_sample.cfg zoo.cfg

# 修改配置
vim zoo.cfg # dataDir=/usr/local/zookeeper/data

# 启动(进入 zookeeper/bin 目录)
./zkServer.sh start

# 查看当前 zookeeper 状态
./zkServer.sh status

# 或者运行 netstat -lntup 命令查看网络状态,可以看到 zookeeper 的端口号 2181 已经被占用
```

### 实战#2 编码

参考: [dubbo-spring-boot-project](https://github.com/apache/dubbo-spring-boot-project)

spring-boot-demo-dubbo: <https://github.com/xkcoding/spring-boot-demo/tree/master/spring-boot-demo-dubbo>

使用 SpringBoot+Dubbo 搭建一个简单分布式服务: <https://github.com/Snailclimb/springboot-guide/blob/master/docs/advanced/springboot-dubbo.md>
