package me.icro.java.springboot.scaffold.exception;

import lombok.extern.slf4j.Slf4j;
import me.icro.java.springboot.scaffold.exception.constant.Status;
import me.icro.java.springboot.scaffold.model.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * 描述: 全局异常处理类
 *
 * @author Lin
 * @since 2020-01-15 3:56 PM
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 统一的 BaseException异常 处理
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<?> handleAppException(BaseException ex, HttpServletRequest request) {
        return new ResponseEntity<>(ErrorResponse.ofException(ex,
                request.getRequestURI()),
                new HttpHeaders(),
                ex.getStatus().getHttpStatus());
    }

    /**
     * 统一的 Exception异常 处理
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception ex, HttpServletRequest request) {
        return new ResponseEntity<>(ErrorResponse.of(Status.REQUEST_INTERNAL_SERVER_ERROR.getCode(),
                Status.REQUEST_INTERNAL_SERVER_ERROR.getMessage(),
                null,
                request.getRequestURI()),
                new HttpHeaders(),
                Status.REQUEST_INTERNAL_SERVER_ERROR.getHttpStatus());
    }
}
