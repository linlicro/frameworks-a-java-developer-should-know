package test.me.icro.java.spring.icrospring.iocv2.context;

import me.icro.java.spring.icrospring.iocv2.context.ApplicationContext;
import me.icro.java.spring.icrospring.iocv2.context.ClassPathXmlApplicationContext;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import test.me.icro.java.spring.icrospring.iocv2.HelloWorldService;

/**
 * ClassPathXmlApplicationContext Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Dec 25, 2019</pre>
 */
public class ClassPathXmlApplicationContextTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: refresh()
     */
    @Test
    public void test() throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("icov2.xml");
        HelloWorldService helloWorldService = (HelloWorldService) applicationContext.getBean("helloWorldService");
        Assert.assertTrue("Hello World!".equals(helloWorldService.getText()));
    }


} 
