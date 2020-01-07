package me.icro.java.spring.icrospring.aopv2;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 描述: 被代理的对象 封装
 *
 * @author Lin
 * @since 2020-01-06 4:34 PM
 */
@AllArgsConstructor
@Getter
public class TargetSource {
    private Class targetClass;
    private Object target;
}
