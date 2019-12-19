package me.icro.java.spring.icrospring.aop;

import java.lang.reflect.Proxy;

/**
 * 描述:
 *
 * @author Lin
 * @since 2019-12-19 11:08 AM
 */
public class SimpleAOP {
    public static Object getProxy(Object bean, Advice advice) {
        return Proxy.newProxyInstance(SimpleAOP.class.getClassLoader(), bean.getClass().getInterfaces(), advice);
    }
}
