package me.icro.java.spring.icrospring.iocv2.factory;

/**
 * 容器自管理Bean的创建
 *
 * Created by Lin on 2019/12/25.
 */
public interface BeanFactory {
    Object getBean(String beanName) throws Exception;
}
