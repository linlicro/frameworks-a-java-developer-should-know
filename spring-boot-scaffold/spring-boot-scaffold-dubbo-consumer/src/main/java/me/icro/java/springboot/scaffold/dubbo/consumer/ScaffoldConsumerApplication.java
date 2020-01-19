package me.icro.java.springboot.scaffold.dubbo.consumer;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 描述:
 *
 * @author Lin
 * @since 2020-01-19 2:39 PM
 */
@SpringBootApplication
@EnableDubboConfiguration
public class ScaffoldConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScaffoldConsumerApplication.class, args);
    }
}
