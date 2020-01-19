package me.icro.java.springboot.scaffold;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 描述:
 *
 * @author Lin
 * @since 2020-01-19 10:32 AM
 */
@SpringBootApplication
@EnableDubboConfiguration
public class ScaffoldApplication {
    public static void main(String[] args) {
        SpringApplication.run(ScaffoldApplication.class, args);
    }
}
