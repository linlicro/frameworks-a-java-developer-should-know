package me.icro.java.springboot.scaffold.intercept;

import cn.hutool.json.JSONUtil;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * 描述: 使用 aop 切面记录请求日志信息
 *
 * @author Lin
 * @since 2020-01-21 10:37 上午
 */
@Aspect
@Component
@Slf4j
public class ControllerIntercept {
    private static final String REQUEST_START = "request-start";

    /**
     * 切入点
     */
    @Pointcut("execution(public * me.icro.java.springboot.scaffold.controller.*Controller.*(..))")
    public void log() {

    }

    /**
     * 前置
     * @param point
     */
    @Before("log()")
    public void before(JoinPoint point) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();
        log.info("@[{}; {}] >> {}", request.getRequestURI(), request.getRemoteAddr(), JSONUtil.toJsonStr(request.getParameterMap()));

        Long start = System.currentTimeMillis();
        request.setAttribute(REQUEST_START, start);
    }

    /**
     * 环绕
     * @param point
     * @return
     * @throws Throwable
     */
    @Around("log()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object result = point.proceed();
        log.info("@Response body >> {}", JSONUtil.toJsonStr(result));
        return result;
    }

    /**
     * 后置
     */
    @After("log()")
    public void after() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();

        Long start = (Long) request.getAttribute(REQUEST_START);
        Long end = System.currentTimeMillis();
        String header = request.getHeader("User-Agent");
        UserAgent userAgent = UserAgent.parseUserAgentString(header);

        log.info("@[{} took {}ms; details: {}]", request.getRemoteAddr(), end - start, header);
    }
}
