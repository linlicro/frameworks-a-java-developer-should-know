# spring boot scaffold(脚手架)

spring boot scaffold(脚手架) 集成redis、pagehelper、mybatis、log4j2、druid、jwt、mail等，方便于日常业务开发。

-[x] redis
-[x] log4j2
-[ ] mybatis
-[ ] pagehelper
-[ ] druid
-[ ] jwt
-[ ] mail
-[ ] Spring Boot CLI: CLI自动生成

## 日志 log4j2

使用log4j2，做日志文件框架，移除了springBoot自带的logback。

```xml
<dependencies>
  <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter</artifactId>
      <exclusions>
          <!--移除springBoot自带的logback-->
          <exclusion>
              <groupId>org.springframework.boot</groupId>
              <artifactId>spring-boot-starter-logging</artifactId>
          </exclusion>
      </exclusions>
  </dependency>
  
  <!--使用log4j2，做日志文件框架-->
  <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-log4j2</artifactId>
  </dependency>
</dependencies>
```

小技巧，使用 `@Slf4j` 来替代初始化 `org.slf4j.Logger`，更方便哦。

## 缓存 redis

整合 redis，操作redis中的数据，并使用redis缓存数据。连接池使用 Lettuce。

spring boot框架在1.x.x的版本时默认使用的jedis客户端，现在是2.x.x版本默认使用的lettuce客户端，两种客户端的区别如下：

相同的的是，Jedis和Lettuce都是Redis Client。但，Jedis 是直连模式，在多个线程间共享一个 Jedis 实例时是线程不安全的，如果想要在多线程环境下使用 Jedis，需要使用连接池，每个线程都去拿自己的 Jedis 实例，当连接数量增多时，物理连接成本就较高了。

Lettuce的连接是基于Netty的，连接实例可以在多个线程间共享，所以，一个多线程的应用可以使用同一个连接实例，而不用担心并发线程的数量。当然这个也是可伸缩的设计，一个连接实例不够的情况也可以按需增加连接实例。基于 netty ，netty 是一个多线程、事件驱动的 I/O 框架，通过异步的方式可以让我们更好的利用系统资源，而不用浪费线程等待网络或磁盘I/O。

所以 Lettuce 可以帮助我们充分利用异步的优势。

### 参考

* [Caching Data with Spring](https://spring.io/guides/gs/caching/)
* [Spring Data Redis](https://docs.spring.io/spring-data/redis/docs/2.0.1.RELEASE/reference/html/)
* [redis 中文文档](http://www.redis.cn/commands.html)