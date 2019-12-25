package test.me.icro.java.spring.icrospring.iocv2;

import me.icro.java.spring.icrospring.iocv2.BeanDefinition;
import me.icro.java.spring.icrospring.iocv2.factory.AbstractBeanFactory;
import me.icro.java.spring.icrospring.iocv2.factory.AutowireCapableBeanFactory;
import me.icro.java.spring.icrospring.iocv2.io.ResourceLoader;
import me.icro.java.spring.icrospring.iocv2.xml.XmlBeanDefinitionReader;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.util.Map;

/**
 * BeanFactory Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Dec 25, 2019</pre>
 */
public class BeanFactoryTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getBean(String beanName)
     */
    @Test
    public void testGetBean() throws Exception {

        // 0. 读取配置
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new ResourceLoader());
        xmlBeanDefinitionReader.loadBeanDefinitions("icov2.xml");

        // 1. 初始化beanfactory
        AbstractBeanFactory beanFactory = new AutowireCapableBeanFactory();

        // 2. 注入bean && 设置属性
        for (Map.Entry<String, BeanDefinition> beanDefinitionEntry : xmlBeanDefinitionReader.getRegistry().entrySet()) {
            beanFactory.registerBeanDefinition(beanDefinitionEntry.getKey(), beanDefinitionEntry.getValue());
        }

        // 3. 初始化beans
        beanFactory.preInstantiateSingletons();

        // 4. 获取bean
        HelloWorldService helloWorldService = (HelloWorldService) beanFactory.getBean("helloWorldService");
        Assert.assertTrue("Hello World!".equals(helloWorldService.getText()));
        Assert.assertTrue(beanFactory.getBean("helloWorldService2").equals(helloWorldService.getHelloWorldService2()));
    }

    /**
     * Method: registerBeanDefinition(String beanName, BeanDefinition beanDefinition)
     */
    @Test
    public void testRegisterBeanDefinition() throws Exception {
//TODO: Test goes here... 
    }


} 
