package test.me.icro.java.spring.icrospring.ioc;

import me.icro.java.spring.ch01_basic.SimpleBean;
import me.icro.java.spring.icrospring.ioc.BeanFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

/**
 * BeanFactory Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Dec 17, 2019</pre>
 */
public class BeanFactoryTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: registerBeanDefintion(String beanName, BeanDefinition definition)
     */
    @Test
    public void testRegisterBeanDefintion() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getBean(String beanName)
     */
    @Test
    public void testGetBean() throws Exception {
        String location = BeanFactory.class.getClassLoader().getResource("spring-test.xml").getPath();
        BeanFactory beanFactory = new BeanFactory(location);
        SimpleBean simpleBean = (SimpleBean) beanFactory.getBean("simpleBean");
        Assert.assertNotNull("Can not get the bean.", simpleBean);
        Assert.assertTrue("message".equals(simpleBean.getMessage()));
    }


    /**
     * Method: refresh()
     */
    @Test
    public void testRefresh() throws Exception {
//TODO: Test goes here... 
/* 
try { 
   Method method = BeanFactory.getClass().getMethod("refresh"); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/
    }

    /**
     * Method: setConfigLocation(String location)
     */
    @Test
    public void testSetConfigLocation() throws Exception {
//TODO: Test goes here... 
/* 
try { 
   Method method = BeanFactory.getClass().getMethod("setConfigLocation", String.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/
    }

    /**
     * Method: doCreateBean(final String beanName, final BeanDefinition beanDefinition)
     */
    @Test
    public void testDoCreateBean() throws Exception {
//TODO: Test goes here... 
/* 
try { 
   Method method = BeanFactory.getClass().getMethod("doCreateBean", final.class, final.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/
    }

    /**
     * Method: createBeanInstance(final BeanDefinition beanDefinition)
     */
    @Test
    public void testCreateBeanInstance() throws Exception {
//TODO: Test goes here... 
/* 
try { 
   Method method = BeanFactory.getClass().getMethod("createBeanInstance", final.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/
    }

    /**
     * Method: applyPropertyValues(Object bean, BeanDefinition beanDefinition)
     */
    @Test
    public void testApplyPropertyValues() throws Exception {
//TODO: Test goes here... 
/* 
try { 
   Method method = BeanFactory.getClass().getMethod("applyPropertyValues", Object.class, BeanDefinition.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/
    }

    /**
     * Method: loadBeans(String location)
     */
    @Test
    public void testLoadBeans() throws Exception {
//TODO: Test goes here... 
/* 
try { 
   Method method = BeanFactory.getClass().getMethod("loadBeans", String.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/
    }

} 
