package me.icro.java.springboot.scaffold.dubbo.service;

import com.alibaba.dubbo.config.annotation.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 描述:
 *
 * Service - Dubbo 提供的而不是 Spring 提供的
 *
 * @author Lin
 * @since 2020-01-19 2:14 PM
 */
@Service
@Component
@Slf4j
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        log.info("calling sayHello...");
        return "say hello to " + name;
    }
}
