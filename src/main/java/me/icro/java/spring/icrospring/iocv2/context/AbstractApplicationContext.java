package me.icro.java.spring.icrospring.iocv2.context;

import me.icro.java.spring.icrospring.iocv2.factory.AbstractBeanFactory;

/**
 * 描述:
 *
 * @author Lin
 * @since 2019-12-25 3:46 PM
 */
public abstract class AbstractApplicationContext implements ApplicationContext {
    protected AbstractBeanFactory beanFactory;

    public AbstractApplicationContext(AbstractBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public void refresh() throws Exception {

    }

    @Override
    public Object getBean(String beanName) throws Exception {
        return beanFactory.getBean(beanName);
    }
}
