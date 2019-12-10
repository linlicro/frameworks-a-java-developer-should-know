package me.icro.java.spring.icrospring.ioc;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 描述:
 *
 * @author Lin
 * @since 2019-12-10 2:41 PM
 */
public class BeanFactory {
    private final Map<String, Object> singletonObjects = new ConcurrentHashMap<String, Object>(64);
    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>();

    public void registerBeanDefintion(String beanName, BeanDefinition definition) {
        beanDefinitionMap.put(beanName, definition);
    }

    public Object getBean(String beanName) {
        Object bean = this.singletonObjects.get(beanName);
        if (null == bean) {
            try {
                bean = doCreateBean(beanName, beanDefinitionMap.get(beanName));
            } catch (Exception e) {
                // ignore
            }
        }
        return bean;
    }

    private Object doCreateBean(final String beanName, final BeanDefinition beanDefinition) throws InstantiationException, IllegalAccessException, NoSuchFieldException {
        Object object = createBeanInstance(beanDefinition);
        applyPropertyValues(object, beanDefinition);
        return object;
    }

    private Object createBeanInstance(final BeanDefinition beanDefinition) throws IllegalAccessException, InstantiationException {
        return beanDefinition.getBeanClass().newInstance();
    }

    private void applyPropertyValues(Object bean, BeanDefinition beanDefinition) throws NoSuchFieldException, IllegalAccessException {
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        if (null == propertyValues) {
            return;
        }
        for (PropertyValue propertyValue : propertyValues.getPropertyValueList()) {
            Field field = bean.getClass().getDeclaredField(propertyValue.getName());
            field.setAccessible(true);
            field.set(bean, propertyValue.getValue());
        }
    }


}
