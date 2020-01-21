# spring boot scaffold(脚手架)

spring boot scaffold(脚手架) 集成redis、pagehelper、mybatis、log4j2、druid、jwt、mail等，方便于日常业务开发。

## 开发环境

* IDEA(注意：务必使用 IDEA 开发，同时保证安装 lombok 插件)
* Maven、JDK 1.8+
* Springboot(2.1.4.RELEASE)、Mybatis等
* Redis、Mysql 5.7 + (尽量保证使用 5.7 版本以上，因为 5.7 版本加了一些新特性，同时不向下兼容。本 demo 里会尽量避免这种不兼容的地方，但还是建议尽量保证 5.7 版本以上)

-[x] redis
-[x] log4j2
-[x] properties
-[ ] aopLog(通过AOP记录web请求日志)
-[x] mybatis & 通用Mapper & 多数据源 && pagehelper
-[ ] PageHelper(通用的Mybatis分页插件) & mybatis-plus(快速操作Mybatis)
-[ ] druid
-[x] Dubbo(采用官方的starter)
-[ ] jwt
-[ ] mail
-[ ] actuator(监控)
-[ ] admin(可视化的监控)
-[ ] Spring Boot CLI: CLI自动生成

## 一个小彩蛋：动态 Banner

来尝尝鲜。

很简单，只需要在src/main/resources路径下新建一个banner.txt文件，banner.txt中填写好需要打印的字符串内容即可。

借助第三方工具帮忙转化内容，如网站 <http://www.network-science.de/ascii/> 将文字转化成字符串，网站： <http://www.degraeve.com/img2txt.php> 可以将图片转化成字符串。

## 日志 log4j2

使用log4j2，做日志文件框架，移除了springBoot自带的logback。

Log4j2是Log4j的升级版本，Log4j2相对于Log4j1.x 有了很多显著的改善。

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

    <!-- 日志文件异步依赖 -->
    <dependency>
        <groupId>com.lmax</groupId>
        <artifactId>disruptor</artifactId>
        <version>3.4.1</version>
    </dependency>
</dependencies>
```

常见错误(Multiple bindings were found on the class path)的解决方案: 通过IDEA查找包之间的依赖，以及按需求排除依赖。 参考 <http://www.slf4j.org/codes.html#multiple_bindings>

小技巧，使用 `@Slf4j` 来替代初始化 `org.slf4j.Logger`，更方便哦。

## properties 多环境配置

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

### 问题: Idea inspects batis mapper bean wrong

一种解决方案是 在 `Mapper` 类上加上:

```java
@Mapper
@Component
```

### 通用Mapper 和 pagehelper

通用Mapper官方文档：<https://github.com/abel533/Mapper/wiki/1.integration>
pagehelper 官方文档：<https://github.com/pagehelper/Mybatis-PageHelper/blob/master/wikis/zh/HowToUse.md>

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

Apache Dubbo (incubating) |ˈdʌbəʊ| 是一款高性能、轻量级的开源Java RPC 框架，它提供了三大核心能力：面向接口的远程方法调用，智能容错和负载均衡，以及服务自动注册和发现。

简单来说 Dubbo 是一个分布式服务框架，致力于提供高性能和透明化的RPC远程服务调用方案，以及SOA服务治理方案。

dubbo 官网：<http://dubbo.apache.org/zh-cn/>

这里使用ZooKeeper作为服务注册中心，引入[dubbo-spring-boot-project](https://github.com/apache/dubbo-spring-boot-project)来实现服务编码。

pom:

```xml
<!-- dubbo依赖 -->
<dependency>
    <groupId>com.alibaba.spring.boot</groupId>
    <artifactId>dubbo-spring-boot-starter</artifactId>
    <version>${dubbo.starter.version}</version>
</dependency>
<dependency>
    <groupId>com.101tec</groupId>
    <artifactId>zkclient</artifactId>
    <version>${zkclient.version}</version>
</dependency>
```

application.yml:

```text
spring:
   # dubbo配置
  dubbo:
    application:
      name: spring-boot-scaffold-dubbo-provider
      registry: zookeeper://localhost:2181
```

具体实现见dubbo服务提供`HelloServiceImpl` 和 消费者`HelloController`。

### Filter

使用`Filter`对dubbo请求进行日志记录。详细见 `ExecutionArgumentAndTimeCollectFilter`。
