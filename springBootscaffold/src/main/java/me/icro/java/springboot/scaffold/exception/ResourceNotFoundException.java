package me.icro.java.springboot.scaffold.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * 描述: 自定义异常类，未找到相关资源
 *
 * @author Lin
 * @since 2020-01-15 10:49 AM
 */
public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -5614662802284313031L;

    @Getter
    @Setter
    private String message;

    public ResourceNotFoundException() {
        super();
    }

    public ResourceNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
