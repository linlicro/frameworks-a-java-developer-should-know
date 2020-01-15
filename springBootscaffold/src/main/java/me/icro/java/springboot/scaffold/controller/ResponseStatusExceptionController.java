package me.icro.java.springboot.scaffold.controller;

import me.icro.java.springboot.scaffold.exception.ResourceNotFoundException;
import me.icro.java.springboot.scaffold.exception.ResourceNotFoundException2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 * 描述:
 *
 * @author Lin
 * @since 2020-01-15 2:01 PM
 */
@RestController
@RequestMapping("/api")
public class ResponseStatusExceptionController {

    @GetMapping("/resourceNotFoundException2")
    public void throwResourceNotFoundException() {
        throw new ResourceNotFoundException2("the resource Not Found!");
    }

    @GetMapping("/resourceNotFoundException3")
    public void throwResourceNotFoundException1() {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "the resource Not Found!", new ResourceNotFoundException());
    }
}
