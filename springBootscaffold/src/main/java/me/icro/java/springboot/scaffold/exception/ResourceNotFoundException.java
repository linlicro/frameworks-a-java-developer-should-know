package me.icro.java.springboot.scaffold.exception;

import me.icro.java.springboot.scaffold.exception.constant.Status;

/**
 * 描述: 自定义异常类，未找到相关资源
 *
 * @author Lin
 * @since 2020-01-15 10:49 AM
 */
public class ResourceNotFoundException extends BaseException {

    private static final long serialVersionUID = -5614662802284313031L;

    public ResourceNotFoundException(Object data) {
        super(Status.RESOURCE_NOT_FOUND, data);
    }
}
