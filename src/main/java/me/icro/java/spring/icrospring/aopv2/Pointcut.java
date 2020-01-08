package me.icro.java.spring.icrospring.aopv2;

/**
 * 切点
 *
 * Created by Lin on 2020/1/8.
 */
public interface Pointcut {

    ClassFilter getClassFilter();

    MethodFilter getMethodFilter();
}
