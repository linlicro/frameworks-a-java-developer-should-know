# spring-boot-scaffold-elasticsearch

本项目为spring-boot-scaffold系列之集成 spring-boot-starter-data-elasticsearch 完成对 ElasticSearch 的高级使用技巧，包括创建索引、配置映射、删除索引、增删改查基本操作、复杂查询、高级查询、聚合查询。

> Elasticsearch 是一个分布式、可扩展、实时的搜索与数据分析引擎。 它能从项目一开始就赋予你的数据以搜索、分析和探索的能力，可用于实现全文搜索和实时数据统计。

## 开发环境

* IDE - IntelliJ IDEA
* JDK 1.8
* Maven

## ElasticSearch

### 版本问题

版本历史: 1.x -> 2.x -> 5.x

### 安装

本地环境使用docker运行:

```sh
# 下载镜像
docker pull elasticsearch:6.5.3

# 运行容器
docker run -d -p 9200:9200 -p 9300:9300 --name elasticsearch-6.5.3 elasticsearch:6.5.3

# 进入容器
docker exec -it elasticsearch-6.5.3 /bin/bash

# 安装ik分词器
./bin/elasticsearch-plugin install https://github.com/medcl/elasticsearch-analysis-ik/releases/download/v6.5.3/elasticsearch-analysis-ik-6.5.3.zip

# 修改 es 配置文件
vi ./config/elasticsearch.yml
# 配置内容：
# cluster.name: "docker-cluster"
# network.host: 0.0.0.0

# # minimum_master_nodes need to be explicitly set when bound on a public IP
# # set to 1 to allow single node clusters
# # Details: https://github.com/elastic/elasticsearch/pull/17288
# discovery.zen.minimum_master_nodes: 1

# # just for elasticsearch-head plugin
# http.cors.enabled: true
# http.cors.allow-origin: "*"

# 退出容器
exit

# 停止容器
docker stop elasticsearch-6.5.3

# 启动容器
docker start elasticsearch-6.5.3
```

验证安装是否成功:

```sh
curl http://localhost:9200/
{
  "name" : "F0R_3mD",
  "cluster_name" : "docker-cluster",
  "cluster_uuid" : "Jubb_nerSV6VVOl_c2bP7w",
  "version" : {
    "number" : "6.5.3",
    "build_flavor" : "default",
    "build_type" : "tar",
    "build_hash" : "159a78a",
    "build_date" : "2018-12-06T20:11:28.826501Z",
    "build_snapshot" : false,
    "lucene_version" : "7.5.0",
    "minimum_wire_compatibility_version" : "5.6.0",
    "minimum_index_compatibility_version" : "5.0.0"
  },
  "tagline" : "You Know, for Search"
}
```

或者 前往<https://www.elastic.co/cn/downloads/elasticsearch> 下载安装。

### 插件 Header 安装

ref: <https://github.com/mobz/elasticsearch-head>

## Elasticsearch 基础概念

* 集群和节点
* 索引: 含有相同属性的文档集合
* 类型: 索引可以定义一个或多个类型，文档必须属于一个类型
* 文档: 文档是可以被索引的基本数据单位
* 分片: 每个索引都有多个分片，每个分片是一个Lucene索引
* 备份: 拷贝一份分片就完成了分片的备份

## 基本用法

API 基本格式: `http://<ip>:<port>/<索引>/<类型>/<文档id>`

常用HTTP动词: GET/PUT/POST/DELETE

创建索引:

* 非结构化创建
* 结构化创建

## Spring Data Elasticsearch

> Spring Data Elasticsearch是Spring提供的一种以Spring Data风格来操作数据存储的方式，它可以避免编写大量的样板代码。

### 常用注解

@Document:

```java
//标示映射到Elasticsearch文档上的领域对象
public @interface Document {
  //索引库名次，mysql中数据库的概念
  String indexName();
  //文档类型，mysql中表的概念
  String type() default "";
  //默认分片数
  short shards() default 5;
  //默认副本数量
  short replicas() default 1;
}
```

@Id:

```java
//表示是文档的id，文档可以认为是mysql中表行的概念
public @interface Id {
}
```

@Field:

```java
public @interface Field {
  //文档中字段的类型
  FieldType type() default FieldType.Auto;
  //是否建立倒排索引
  boolean index() default true;
  //是否进行存储
  boolean store() default false;
  //分词器名次
  String analyzer() default "";
}

//为文档自动指定元数据类型
public enum FieldType {
 Text,//会进行分词并建了索引的字符类型
 Integer,
 Long,
 Date,
 Float,
 Double,
 Boolean,
 Object,
 Auto,//自动判断字段类型
 Nested,//嵌套对象类型
 Ip,
 Attachment,
 Keyword//不会进行分词建立索引的类型
}
```

## 参考

1. ElasticSearch 官方文档：<https://www.elastic.co/guide/en/elasticsearch/reference/6.x/getting-started.html>
2. spring-data-elasticsearch 官方文档：<https://docs.spring.io/spring-data/elasticsearch/docs/3.1.2.RELEASE/reference/html/>
