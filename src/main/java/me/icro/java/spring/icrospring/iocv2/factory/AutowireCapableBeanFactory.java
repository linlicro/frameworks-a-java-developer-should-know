package me.icro.java.spring.icrospring.iocv2.factory;

import me.icro.java.spring.icrospring.iocv2.BeanDefinition;
import me.icro.java.spring.icrospring.iocv2.BeanReference;

import java.lang.reflect.Field;

/**
 * 描述: 可自动装配的容器
 *
 * @author Lin
 * @since 2019-12-25 11:26 AM
 */
public class AutowireCapableBeanFactory extends AbstractBeanFactory {

    @Override
    protected Object doCreateBean(BeanDefinition beanDefinition) throws Exception {
        Object bean = createBeanInstance(beanDefinition);
        beanDefinition.setBean(bean);
        applyPropertyValues(bean, beanDefinition);
        return bean;
    }

    protected Object createBeanInstance(BeanDefinition beanDefinition) throws Exception {
        return beanDefinition.getBeanClass().newInstance();
    }

    protected void applyPropertyValues(Object bean, BeanDefinition beanDefinition) throws Exception {
        for (PropertyValue propertyValue : beanDefinition.getPropertyValues().getPropertyValues()) {
            Field declareField = bean.getClass().getDeclaredField(propertyValue.getKey());
            declareField.setAccessible(true);
            Object value = propertyValue.getValue();
            if (value instanceof BeanReference) {
                BeanReference beanReference = (BeanReference) value;
                value = getBean(beanReference.getName());
            }
            declareField.set(bean, value);
        }
    }
}
