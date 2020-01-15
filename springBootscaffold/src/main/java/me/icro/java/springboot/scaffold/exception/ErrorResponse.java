package me.icro.java.springboot.scaffold.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 描述: 异常信息包装类
 *
 * @author Lin
 * @since 2020-01-15 10:46 AM
 */
@AllArgsConstructor

public class ErrorResponse {

    @Getter
    private String message;
    @Getter
    private String errorTypeName;

    public ErrorResponse(Exception e) {
        this(e.getMessage(), e.getClass().getName());
    }
}
