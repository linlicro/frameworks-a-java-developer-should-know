package me.icro.java.springboot.scaffold.exception;

import lombok.extern.slf4j.Slf4j;
import me.icro.java.springboot.scaffold.exception.constant.Status;
import me.icro.java.springboot.scaffold.model.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashSet;

/**
 * 描述: 全局异常处理类
 *
 * @author Lin
 * @since 2020-01-15 3:56 PM
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    private static HashSet<Integer> HTTP_STATU_CODE_SET = new HashSet<>();
    static {
        for (Status status : Status.values()) {
            HTTP_STATU_CODE_SET.add(status.getCode());
        }
    }

    /**
     * 统一的json异常处理
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(value = JsonException.class)
    public ResponseEntity<ApiResponse> jsonExceptionHandler(JsonException exception) {
        log.error("JsonException: {}.\nException details: {}", exception.getMessage(), exception);

        if (HTTP_STATU_CODE_SET.contains(exception.getCode())) {
            return ResponseEntity.status(exception.getCode()).body(ApiResponse.ofException(exception));
        } else {
            return ResponseEntity.status(Status.ERROR.getCode()).body(ApiResponse.ofException(exception));
        }
    }

}
