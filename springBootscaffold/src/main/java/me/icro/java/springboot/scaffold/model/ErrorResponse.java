package me.icro.java.springboot.scaffold.model;

import lombok.Data;
import me.icro.java.springboot.scaffold.exception.BaseException;

import java.time.Instant;

/**
 * 描述: 异常响应
 *
 * @author Lin
 * @since 2020-01-15 11:23 PM
 */
@Data
public class ErrorResponse extends ApiResponse {

    private String path;
    private Instant timestamp;

    public ErrorResponse() {
        super();
    }

    public ErrorResponse(Integer code, String message, Object data, String path) {
        super(code, message, data);
        this.path = path;
        this.timestamp = Instant.now();
    }

    public static ErrorResponse of(Integer code, String message, Object data, String path) {
        return new ErrorResponse(code, message, data, path);
    }

    public static <T extends BaseException> ApiResponse ofException(T t, String path) {
        return of(t.getStatus().getCode(), t.getMessage(), t.getData(), path);
    }
}
