package me.icro.java.springboot.scaffold.exception.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;

/**
 * 状态码封装
 *
 * @author Lin
 * @date 2020/1/15
 */
@RequiredArgsConstructor
@Getter
@ToString
public enum Status {
    /**
     * {@code 200 OK}.
     */
    OK(1000, HttpStatus.OK, "成功"),

    /**
     * {@code 404 Not Found}.
     */
    RESOURCE_NOT_FOUND(1001, HttpStatus.NOT_FOUND, "未找到资源"),

    /**
     * {@code 400 Bad Request}.
     */
    REQUEST_VALIDATION_FAILED(1002, HttpStatus.BAD_REQUEST, "请求数据格式验证失败"),

    /**
     * {@code 500 Internal Server Error}.
     */
    REQUEST_INTERNAL_SERVER_ERROR(1003, HttpStatus.INTERNAL_SERVER_ERROR, "服务异常");

    private final Integer code;
    private final HttpStatus httpStatus;
    private final String message;
}
