package me.icro.java.springboot.scaffold.controller;

import me.icro.java.springboot.scaffold.exception.JsonException;
import me.icro.java.springboot.scaffold.exception.constant.Status;
import me.icro.java.springboot.scaffold.model.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述:
 *
 * @author Lin
 * @since 2020-01-15 4:14 PM
 */
@RestController
@RequestMapping("/api")
public class JsonExceptionController {

    @GetMapping("/jsonException")
    public ApiResponse jsonException() {
        throw new JsonException(Status.ERROR);
    }
}
