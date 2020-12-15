# Spring Security in Action

Spring Security是一款基于Spring的安全框架，主要包含认证和授权两大安全模块，它拥有更为强大的功能。Spring Security也可以轻松的自定义扩展以满足各种需求，并且对常见的Web安全攻击提供了防护支持。如果你的Web框架选择的是Spring，那么在安全方面Spring Security会是一个不错的选择。

这里的实战使用Spring Boot来集成Spring Security，Spring Boot版本为2.2.5.RELEASE，Spring Security版本为5.2.5RELEASE。

## Spring Security Basic

实例代码: <https://github.com/linlicro/spring-security-demos/tree/master/security-basic>

直接启动项目，访问 `http://localhost:8080/hello`，页面会自动跳转至登录页。

因为，项目中引入了Spring Security依赖的时候，项目会默认开启如下配置：

```yml
security:
  basic:
    enabled: true
```

开启了一个HTTP basic类型的认证，所有服务的访问都必须先过这个认证，默认的用户名为user，密码由Sping Security自动生成，回到IDE的控制台，可以找到密码信息。

## 基本原理
