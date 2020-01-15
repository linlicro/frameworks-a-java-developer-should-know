package me.icro.java.springboot.scaffold.exception.constant;

import lombok.Getter;

/**
 * 状态码封装
 *
 * Created by Lin on 2020/1/15.
 */
@Getter
public enum Status {
    /**
     * 成功
     */
    OK(200, "成功"),

    /**
     * 参数错误
     */
    ILLEGAL(400, "参数错误"),

    /**
     * 404
     */
    NOT_FOUND(404, "NOT FOUND"),

    /**
     * 异常 状态
     */
    ERROR(500, "服务异常");

    private Integer code;
    private String message;

    Status(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
