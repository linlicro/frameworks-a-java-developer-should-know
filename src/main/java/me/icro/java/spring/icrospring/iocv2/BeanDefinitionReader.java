package me.icro.java.spring.icrospring.iocv2;

/**
 * 从配置文件中读取BeanDefinition
 *
 * Created by Lin on 2019/12/25.
 */
public interface BeanDefinitionReader {

    void loadBeanDefinitions(String location) throws Exception;
}
