package me.icro.java.springboot.scaffold.controller;

import com.google.common.collect.ImmutableMap;
import me.icro.java.springboot.scaffold.exception.BaseException;
import me.icro.java.springboot.scaffold.exception.ResourceNotFoundException;
import me.icro.java.springboot.scaffold.exception.constant.Status;
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

    @GetMapping("/resourceNotFound")
    public void throwResourceNotFoundException() {
        throw new ResourceNotFoundException(ImmutableMap.of("person id:", 1L));
    }

    @GetMapping("/illegalArgument")
    public void throwIllegalArgumentException() {
        throw new BaseException(new IllegalArgumentException("入参异常"), Status.REQUEST_VALIDATION_FAILED, null);
    }

    @GetMapping("/unknownError")
    public void throwException() {
        throw new RuntimeException("未知异常");
    }
}
