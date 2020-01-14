package me.icro.java.springboot.scaffold;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 描述:
 *
 * @author Lin
 * @since 2020-01-09 2:54 PM
 */
@MapperScan(basePackages = {"me.icro.java.springboot.scaffold.mapper"})
@SpringBootApplication
public class ScaffoldApplication {
    public static void main(String[] args) {
        SpringApplication.run(ScaffoldApplication.class, args);
    }
}
