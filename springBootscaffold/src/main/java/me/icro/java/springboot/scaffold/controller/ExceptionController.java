package me.icro.java.springboot.scaffold.controller;

import me.icro.java.springboot.scaffold.exception.ResourceNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述: 模拟抛出异常
 *
 * @author Lin
 * @since 2020-01-15 11:31 AM
 */
@RestController
@RequestMapping("/api")
public class ExceptionController {

    @GetMapping("/illegalArgumentException")
    public void throwIllegalArgumentException() {
        throw new IllegalArgumentException();
    }

    @GetMapping("/resourceNotFoundException")
    public void throwResourceNotFoundException() {
        throw new ResourceNotFoundException();
    }
}
