package me.icro.java.spring.icrospring.aop;

import java.lang.reflect.Method;

/**
 * 描述: 前置通知
 *
 * @author Lin
 * @since 2019-12-19 11:02 AM
 */
public class BeforeAdvice implements Advice {

    private Object bean;
    private MethodInvocation methodInvocation;

    public BeforeAdvice(Object bean, MethodInvocation methodInvocation) {
        this.bean = bean;
        this.methodInvocation = methodInvocation;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 在目标方法执行前调用通知
        methodInvocation.invoke();
        return method.invoke(bean, args);
    }
}
