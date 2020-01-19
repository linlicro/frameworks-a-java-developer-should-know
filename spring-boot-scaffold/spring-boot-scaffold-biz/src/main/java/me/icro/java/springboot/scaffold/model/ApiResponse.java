package me.icro.java.springboot.scaffold.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.icro.java.springboot.scaffold.exception.constant.Status;

/**
 * 描述: 通用的 API 接口封装
 *
 * @author Lin
 * @since 2020-01-15 3:58 PM
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
}
