package me.icro.java.springboot.scaffold.exception;

import me.icro.java.springboot.scaffold.exception.constant.Status;

/**
 * 描述: Json异常类
 *
 * @author Lin
 * @since 2020-01-15 3:52 PM
 */
public class JsonException extends BaseException {
    private static final long serialVersionUID = -2992732928077977168L;

    public JsonException(Status status) {
        super(status);
    }

    public JsonException(String message, Integer code) {
        super(message, code);
    }
}
