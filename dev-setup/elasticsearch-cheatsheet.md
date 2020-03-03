# elasticsearch-cheatsheet

Elasticsearch 是一个分布式、可扩展、实时的搜索与数据分析引擎。 它能从项目一开始就赋予你的数据以搜索、分析和探索的能力，可用于实现全文搜索和实时数据统计。

## 安装

docker方式:

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

安装包方式(单实例):

前往 <https://www.elastic.co/cn/downloads/elasticsearch> 下载安装。

实用插件: <https://github.com/mobz/elasticsearch-head>

分布式设置:

```sh
# master配置信息
vim elasticsearch_path/config/elasticsearch.yml
# # 插入配置信息
# http.cors.enable: true
# http.cors.allow-origin: '*'
#
# cluster.name: your_cluster_name
# node.name: master
# node.master: true
#
# network.hosts: 127.0.0.1

# 重启
ps -ef | grep 'elasticsearch_path'
kill yout_pid
elasticsearch_path/bin/elasticsearch -d

# slave配置信息
vim es_slave_path/config/elasticsearch.yml
# # 插入配置信息
# cluster.name: your_cluster_name
# node.name: slave
#
# network.hosts: 127.0.0.1
# http.port: 8200
#
# discovery.zen.ping.unicast.hosts: ["master_ip"]
```

## 基础概念

* 集群和节点
* 索引: 含有相同属性的文档集合
* 类型: 索引可以定义一个或多个类型，文档必须属于一个类型
* 文档: 文档是可以被索引的基本数据单位
* 分片: 每个索引都有多个分片，每个分片是一个Lucene索引
* 备份: 拷贝一份分片就完成了分片的备份

## 基本用法

API 基本格式: `http://<ip>:<port>/<索引>/<类型>/<文档id>`

常用HTTP动词: GET/PUT/POST/DELETE

### 索引创建

创建索引:

* 非结构化创建: HEAD插件直接创建。
* 结构化创建

`POST http://127.0.0.1:9200/book/novel/_mappings`
BODY:

```json
{
  "novel": {
    "properties": {
      "title": {
        "type": "text"
      }
    }
  }
}
```

创建索引:

PUT `http://127.0.0.1:9200/people`

Request body:

```json
{
    "setting": {
        "number_of_shards": 1,
        "number_of_replicas": 0
    },
    "mappings": {
        "man": {
            "properties": {
                "name": {
                    "type": "text"
                },
                "country": {
                    "type": "keyword"
                },
                "age": {
                    "type": "integer"
                },
                "birthday": {
                    "type": "date",
                    "format": "yyyy-MM-dd HH:mm:ss||yyyy-MM-dd||epoch_millis"
                }
            }
        }
    }
}
```

Response body:

```json
{
  "acknowledged": true,
  "shards_acknowledged": true,
  "index": "people"
}
```

### 插入

* 指定文档id插入

PUT `127.0.0.1:9200/people/man/1`

Request body:

```json
{
 "name": "icro",
 "country": "China",
 "age": 30,
 "birthday": "1988-05-26"
}
```

Response body:

```json
{
  "_index": "people",
  "_type": "man",
  "_id": "1",
  "_version": 1,
  "result": "created",
  "_shards": {
    "total": 2,
    "successful": 1,
    "failed": 0
  },
  "_seq_no": 0,
  "_primary_term": 1
}
```

* 自动产生文档id插入

POST `127.0.0.1:9200/people/man/`

Request body:

```json
{
 "name": "fat-icro",
 "country": "China",
 "age": 40,
 "birthday": "1978-05-26"
}
```

Response body:

```json
{
  "_index": "people",
  "_type": "man",
  "_id": "d92Ud3ABg9LlR86HMTBo",
  "_version": 1,
  "result": "created",
  "_shards": {
    "total": 2,
    "successful": 1,
    "failed": 0
  },
  "_seq_no": 0,
  "_primary_term": 1
}
```

### 修改

* 直接修改文档

POST `127.0.0.1:9200/people/man/1/_update`

Requery Body:

```json
{
 "doc": {
  "name": "who_is_icro"
 }
}
```

* 通过脚本修改文档

...

### 删除

* 删除文档

DELETE `127.0.0.1:9200/people/man/1`

* 删除索引

DELETE `127.0.0.1:9200/people`

### 查询

* 简单查询

GET `127.0.0.1:9200/person/person/1`

* 复杂查询

查询全部数据:

POST `127.0.0.1:9200/person/_search`

```json
{
 "query": {
  "match_all": {

  }
 },
 "from": 1,
 "size": 1
}
```

关键字查询(模糊查询):

POST `127.0.0.1:9200/person/_search`

```json
{
 "query": {
  "match": {
   "country": "蜀国"
  }
 },
 "sort": [ {"birthday": {"order": "desc"}} ]
}
```

聚合查询:

POST `127.0.0.1:9200/person/_search`

```json
{
 "aggs": {
  "group_by_country": {
   "terms": {
    "field": "country"
   }
  }
 }
}
```

其他统计:

POST `127.0.0.1:9200/person/_search`

```json
{
 "aggs": {
  "grades_age": {
   "stats": {
    "field": "age"
   }
  }
 }
}
```

## 高级查询

* 子条件查询: 特定字段查询所指定值，包含 Query context，以及 Filter context；
* 复合条件查询: 以一定的逻辑组合子条件查询

### Query context

在查询过程中，除了判断文档是否满足查询条件外，ES还会计算一个`_score`来标识匹配的程度，旨在判断目标文档和查询条件的匹配`有多好`。

常用查询:

* 全文本查询: 针对文本类型数据
* 字段级别查询: 针对结构化数据，如数字、日期等

内容全包含的查询: `query` + `match_phrase`
多个字段模糊查询: `query` + `multi_match` + `fields`
语法查询: `query_string` *支持表达式 AND, OR*
字段(结构化字段)查询: `query` + `term` / "range"(范围)

### Filter context

Filter context: 在查询过程中，只判断该文档是否满足条件，只有Yes或者No。

查询关键字: `query` + `bool` + `filter`

### 复合条件查询

常用查询:

* 固定分数查询:
* 布尔查询: `query` + `bool` + `should`/`must`/`must_not`
