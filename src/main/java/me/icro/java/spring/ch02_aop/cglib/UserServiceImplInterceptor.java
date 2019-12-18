package me.icro.java.spring.ch02_aop.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 描述: 方法拦截器
 *
 * @author Lin
 * @since 2019-12-18 9:57 PM
 */
public class UserServiceImplInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println(System.currentTimeMillis() + " - " + method.getName() + " method start");
        // 调用目标方法
        methodProxy.invokeSuper(o, objects);
        System.out.println(System.currentTimeMillis() + " - " + method.getName() + " method over");
        return null;
    }
}
