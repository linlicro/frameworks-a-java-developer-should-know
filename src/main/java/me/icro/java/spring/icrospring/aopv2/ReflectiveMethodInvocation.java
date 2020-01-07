package me.icro.java.spring.icrospring.aopv2;

import lombok.AllArgsConstructor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

/**
 * 描述: 切点(Joinpoint)
 *
 * @author Lin
 * @since 2020-01-07 2:33 PM
 */
@AllArgsConstructor
public class ReflectiveMethodInvocation implements MethodInvocation {

    private Object target;
    private Method method;
    private Object[] args;

    @Override
    public Method getMethod() {
        return this.method;
    }

    @Override
    public Object[] getArguments() {
        return this.args;
    }

    @Override
    public Object proceed() throws Throwable {
        return method.invoke(target, args);
    }

    @Override
    public Object getThis() {
        return this.target;
    }

    @Override
    public AccessibleObject getStaticPart() {
        return this.method;
    }
}
