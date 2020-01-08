package me.icro.java.spring.icrospring.aopv2;

/**
 *
 * Created by Lin on 2020/1/8.
 */
public interface ClassFilter {

    boolean matches(Class targetClass);

}
