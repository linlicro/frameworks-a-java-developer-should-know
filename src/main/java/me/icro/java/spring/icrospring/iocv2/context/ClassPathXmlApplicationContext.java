package me.icro.java.spring.icrospring.iocv2.context;

import me.icro.java.spring.icrospring.iocv2.BeanDefinition;
import me.icro.java.spring.icrospring.iocv2.factory.AbstractBeanFactory;
import me.icro.java.spring.icrospring.iocv2.factory.AutowireCapableBeanFactory;
import me.icro.java.spring.icrospring.iocv2.io.ResourceLoader;
import me.icro.java.spring.icrospring.iocv2.xml.XmlBeanDefinitionReader;

import java.util.Map;

/**
 * 描述:
 *
 * @author Lin
 * @since 2019-12-25 3:49 PM
 */
public class ClassPathXmlApplicationContext extends AbstractApplicationContext {
    private String configLocation;

    public ClassPathXmlApplicationContext(String configLocation) throws Exception {
        this(configLocation, new AutowireCapableBeanFactory());
    }

    public ClassPathXmlApplicationContext(String configLocation, AbstractBeanFactory beanFactory) throws Exception {
        super(beanFactory);
        this.configLocation = configLocation;
        refresh();
    }

    @Override
    public void refresh() throws Exception {
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new ResourceLoader());
        xmlBeanDefinitionReader.loadBeanDefinitions(configLocation);
        for (Map.Entry<String, BeanDefinition> beanDefinitionEntry : xmlBeanDefinitionReader.getRegistry().entrySet()) {
            beanFactory.registerBeanDefinition(beanDefinitionEntry.getKey(), beanDefinitionEntry.getValue());
        }
    }
}
