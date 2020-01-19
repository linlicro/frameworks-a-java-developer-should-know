package me.icro.java.springboot.scaffold.dubbo.service;

/**
 * 描述: 服务接口
 *
 * @author Lin
 * @since 2020-01-19 11:23 AM
 */
public interface HelloService {
    /**
     * 问好
     *
     * @param name 姓名
     * @return 问好
     */
    String sayHello(String name);
}
