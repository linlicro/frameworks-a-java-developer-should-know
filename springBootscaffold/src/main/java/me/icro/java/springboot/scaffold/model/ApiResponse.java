package me.icro.java.springboot.scaffold.model;

import lombok.Builder;
import lombok.Data;
import me.icro.java.springboot.scaffold.exception.BaseException;
import me.icro.java.springboot.scaffold.exception.constant.Status;

/**
 * 描述: 通用的 API 接口封装
 *
 * @author Lin
 * @since 2020-01-15 3:58 PM
 */
@Data
@Builder
public class ApiResponse {

    /**
     * 状态码
     */
    private Integer code;
    /**
     * 返回内容
     */
    private String message;
    /**
     * 返回数据
     */
    private Object data;

    public ApiResponse() {
    }

    public ApiResponse(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static ApiResponse of(Integer code, String message, Object data) {
        return new ApiResponse(code, message, data);
    }

    public static ApiResponse ofSuccess(Object data) {
        return of(Status.OK.getCode(), null, data);
    }

    public static ApiResponse ofMessage(String message) {
        return of(Status.OK.getCode(), message, null);
    }

    public static ApiResponse ofStatus(Status status) {
        return ofStatus(status, null);
    }

    public static ApiResponse ofStatus(Status status, Object data) {
        return of(status.getCode(), null, data);
    }

    public static <T extends BaseException> ApiResponse ofException(T t, Object data) {
        return of(t.getCode(), t.getMessage(), data);
    }

    public static <T extends BaseException> ApiResponse ofException(T t) {
        return ofException(t, null);
    }
}
