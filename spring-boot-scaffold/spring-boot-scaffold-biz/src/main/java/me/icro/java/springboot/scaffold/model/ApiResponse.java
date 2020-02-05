package me.icro.java.springboot.scaffold.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.icro.java.springboot.scaffold.exception.constant.Status;

import java.io.Serializable;

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
@ApiModel(value = "通用PI接口返回", description = "Common Api Response")
public class ApiResponse<T> implements Serializable {
    private static final long serialVersionUID = 8491595037632187626L;

    /**
     * 状态码
     */
    @ApiModelProperty(value = "状态码", required = true)
    private Integer code;
    /**
     * 消息
     */
    @ApiModelProperty(value = "信息", required = true)
    private String message;
    /**
     * 返回数据
     */
    @ApiModelProperty(value = "数据", required = true)
    private T data;

    public static <T> ApiResponse<T> of(Integer code, String message, T data) {
        return ApiResponse.<T>builder()
                .code(code)
                .message(message)
                .data(data)
                .build();
    }

    public static <T> ApiResponse<T> ofSuccess(T data) {
        return of(Status.OK.getCode(), null, data);
    }

    public static <T> ApiResponse<T> ofMessage(String message) {
        return of(Status.OK.getCode(), message, null);
    }

    public static <T> ApiResponse<T> ofStatus(Status status) {
        return ofStatus(status, null);
    }

    public static <T> ApiResponse<T> ofStatus(Status status, T data) {
        return of(status.getCode(), null, data);
    }
}
