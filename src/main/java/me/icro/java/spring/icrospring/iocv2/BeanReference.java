package me.icro.java.spring.icrospring.iocv2;

/**
 * 描述:
 *
 * @author Lin
 * @since 2019-12-25 3:08 PM
 */
public class BeanReference {

    private String name;

    private Object bean;

    public BeanReference(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }
}
