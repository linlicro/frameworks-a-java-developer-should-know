package me.icro.java.spring.icrospring.aopv2;

import java.lang.reflect.Method;

/**
 *
 * Created by Lin on 2020/1/8.
 */
public interface MethodFilter {
    boolean matches(Method method, Class targetClass);
}
