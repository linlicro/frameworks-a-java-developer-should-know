package me.icro.java.spring.icrospring.ioc;

/**
 * 描述:
 *
 * @author Lin
 * @since 2019-12-10 3:24 PM
 */
public class BeanDefinition {
    private Class beanClass;
    private String beanClassName;
    private PropertyValues propertyValues;

    public void setBeanClassName(String beanClassName) {
        this.beanClassName = beanClassName;
        try {
            this.beanClass = Class.forName(beanClassName);
        } catch (ClassNotFoundException e) {
            // ignore
        }
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public String getBeanClassName() {
        return beanClassName;
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }
}
