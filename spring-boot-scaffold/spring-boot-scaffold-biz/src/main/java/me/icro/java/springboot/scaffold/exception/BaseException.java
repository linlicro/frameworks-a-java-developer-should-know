package me.icro.java.springboot.scaffold.exception;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import me.icro.java.springboot.scaffold.exception.constant.Status;

/**
 * 描述: 异常基类
 *
 * @author Lin
 * @since 2020-01-15 3:05 PM
 */
@Getter
@EqualsAndHashCode(callSuper = true)
public class BaseException extends RuntimeException {

    private static final long serialVersionUID = -5261065340320027651L;

    private Status status;
    private Object data;

    public BaseException(Status status, Object data) {
        super(status.getMessage());
        this.status = status;
        this.data = data;
    }

    public BaseException(Throwable cause, Status status, Object data) {
        super(status.getMessage(), cause);
        this.status = status;
        this.data = data;
    }
}
