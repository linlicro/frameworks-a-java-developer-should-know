package me.icro.java.spring.icrospring.iocv2.factory;

import me.icro.java.spring.icrospring.iocv2.BeanDefinition;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 描述:
 *
 * @author Lin
 * @since 2019-12-25 11:23 AM
 */
public abstract class AbstractBeanFactory implements BeanFactory {

    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
    private final List<String> beanDefinitionNames = new ArrayList<>();

    @Override
    public Object getBean(String beanName) throws Exception {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if (null == beanDefinition) {
            throw new IllegalArgumentException("No bean named '" + beanName + "' is defined.");
        }
        Object bean = beanDefinition.getBean();
        if (null == bean) {
            bean = doCreateBean(beanDefinition);
        }
        return bean;
    }

    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) throws Exception {
        beanDefinitionMap.put(beanName, beanDefinition);
        beanDefinitionNames.add(beanName);
    }

    public void preInstantiateSingletons() throws Exception {
        for (String beanDefinitionName : this.beanDefinitionNames) {
            getBean(beanDefinitionName);
        }
    }

    /**
     * 初始化bean
     *
     * @param beanDefinition
     * @return
     * @throws Exception
     */
    protected abstract Object doCreateBean(BeanDefinition beanDefinition) throws Exception;
}
