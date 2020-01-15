package me.icro.java.springboot.scaffold.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import me.icro.java.springboot.scaffold.exception.constant.Status;

/**
 * 描述: 异常基类
 *
 * @author Lin
 * @since 2020-01-15 3:05 PM
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BaseException extends RuntimeException {

    private static final long serialVersionUID = -5261065340320027651L;

    private Integer code;
    private String message;

    public BaseException(Status status) {
        super(status.getMessage());
        this.code = status.getCode();
        this.message = status.getMessage();
    }

    public BaseException(String message, Integer code) {
        super(message);
        this.code = code;
        this.message = message;
    }
}
