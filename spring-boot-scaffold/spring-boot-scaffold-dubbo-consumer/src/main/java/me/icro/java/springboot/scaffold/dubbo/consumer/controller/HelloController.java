package me.icro.java.springboot.scaffold.dubbo.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import lombok.extern.slf4j.Slf4j;
import me.icro.java.springboot.scaffold.dubbo.service.HelloService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述:
 *
 * @author Lin
 * @since 2020-01-19 2:36 PM
 */
@RestController
@Slf4j
public class HelloController {
    @Reference(check = false)
    private HelloService helloService;

    @GetMapping("/sayHello")
    public String sayHello(@RequestParam(defaultValue = "icro") String name) {
        log.info("calling someone......");
        return helloService.sayHello(name);
    }
}
