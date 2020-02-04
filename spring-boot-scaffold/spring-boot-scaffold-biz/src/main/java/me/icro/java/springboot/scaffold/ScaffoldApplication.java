package me.icro.java.springboot.scaffold;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * 描述:
 *
 * @author Lin
 * @since 2020-01-19 10:32 AM
 */
@SpringBootApplication
@EnableDubboConfiguration
@MapperScan(basePackages = {"me.icro.java.springboot.scaffold.dao.mybatis.mapper"})
public class ScaffoldApplication {

    public static void main(String[] args) {
        System.setProperty("Log4jContextSelector",
                "org.apache.logging.log4j.core.async.AsyncLoggerContextSelector");
        SpringApplication.run(ScaffoldApplication.class, args);
    }
}
