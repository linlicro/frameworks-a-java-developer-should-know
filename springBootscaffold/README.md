# spring boot scaffold(脚手架)

spring boot scaffold(脚手架) 集成redis、pagehelper、mybatis、log4j2、druid、jwt、mail等，方便于日常业务开发。

## 开发环境

* 开发工具: IDEA
* 基础工具: Maven、JDK8
* 开发技术: Springboot(2.1.4.RELEASE)、Mybatis等
* 其他技术: Redis、MySQL

-[x] redis
-[x] log4j2
-[ ] properties
-[ ] aopLog(通过AOP记录web请求日志)
-[ ] mybatis & 通用Mapper & PageHelper(通用的Mybatis分页插件) & mybatis-plus(快速操作Mybatis)
-[ ] druid
-[ ] Dubbo(采用官方的starter)
-[ ] jwt
-[ ] mail
-[ ] actuator(监控)
-[ ] admin(可视化的监控)
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

## properties

获取配置文件的自定义配置，以及多环境下的配置文件信息的获取。

添加依赖:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-configuration-processor</artifactId>
    <optional>true</optional>
</dependency>
```

在 META-INF/additional-spring-configuration-metadata.json 中自定义配置，可以去除 application.yml 中自定义配置的红线警告，并且为自定义配置添加 hint 提醒。

注意: 需要打包编译后生效 `mvn clean package`。

## 整合 mybatis

最简易方式是 mybatis 官方提供的脚手架 mybatis-spring-boot-starter。

### mybatis-spring-boot-starter 参考

* [Mybatis官方脚手架文档](http://www.mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/)
* [Mybatis整合Spring Boot官方demo](https://github.com/mybatis/spring-boot-starter/tree/master/mybatis-spring-boot-samples)

补充几点注意:

* 连接到mysql-connector-java 6+以上的需要指定时区
* 使用`com.mysql.cj.jdbc.Driver`，因 `com.mysql.jdbc.Driver`被弃用了。

### 集成 Mybatis 的多数据源

往往随着业务量发展，我们通常会进行数据库拆分或是引入其他数据库，从而我们需要配置多个数据源。

还是基于 SpringBoot+Mybatis。

## 缓存 redis

整合 redis，操作redis中的数据，并使用redis缓存数据。连接池使用 Lettuce。

spring boot框架在1.x.x的版本时默认使用的jedis客户端，现在是2.x.x版本默认使用的lettuce客户端，两种客户端的区别如下：

相同的的是，Jedis和Lettuce都是Redis Client。但，Jedis 是直连模式，在多个线程间共享一个 Jedis 实例时是线程不安全的，如果想要在多线程环境下使用 Jedis，需要使用连接池，每个线程都去拿自己的 Jedis 实例，当连接数量增多时，物理连接成本就较高了。

Lettuce的连接是基于Netty的，连接实例可以在多个线程间共享，所以，一个多线程的应用可以使用同一个连接实例，而不用担心并发线程的数量。当然这个也是可伸缩的设计，一个连接实例不够的情况也可以按需增加连接实例。基于 netty ，netty 是一个多线程、事件驱动的 I/O 框架，通过异步的方式可以让我们更好的利用系统资源，而不用浪费线程等待网络或磁盘I/O。

所以 Lettuce 可以帮助我们充分利用异步的优势。

### Redis 参考

* [Caching Data with Spring](https://spring.io/guides/gs/caching/)
* [Spring Data Redis](https://docs.spring.io/spring-data/redis/docs/2.0.1.RELEASE/reference/html/)
* [redis 中文文档](http://www.redis.cn/commands.html)

## 多环境配置

## 全局异常

有3种方式来实现全局异常处理:

* 使用 `@ControllerAdvice` 和 `@ExceptionHandler` 处理全局异常，使用@ControllerAdvice注解 可以通过 assignableTypes 指定特定的类，让异常处理类只处理特定类抛出的异常。
* 使用`@ExceptionHandler` 处理 `Controller` 级别的异常。
* 使用 `ResponseStatusException` 更加方便, 可以避免我们额外的异常类。

但，实际项目中的异常处理解决方案，还需稍加优雅些。

返回的信息应包含异常的下面5部分内容:

* 唯一标示异常的 code
* HTTP状态码
* 错误路径
* 发生错误的时间戳
* 错误的具体信息

以便于前端根据异常做出对应的表现，详细见`GlobalExceptionHandler`。

## Dubbo

todo

* [actuator文档](https://docs.spring.io/spring-boot/docs/2.0.5.RELEASE/reference/htmlsingle/#production-ready)
