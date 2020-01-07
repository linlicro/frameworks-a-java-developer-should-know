package test.me.icro.java.spring.icrospring.aopv2;

import me.icro.java.spring.icrospring.aopv2.AdvisedSupport;
import me.icro.java.spring.icrospring.aopv2.JdkDynamicAopProxy;
import me.icro.java.spring.icrospring.aopv2.TargetSource;
import me.icro.java.spring.icrospring.iocv2.context.ApplicationContext;
import me.icro.java.spring.icrospring.iocv2.context.ClassPathXmlApplicationContext;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

/**
 * JdkDynamicAopProxyTest Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Jan 7, 2020</pre>
 */
public class JdkDynamicAopProxyTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    @Test
    public void testInterceptor() throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("aopv2.xml");
        HelloWorldService helloWorldService = (HelloWorldService) applicationContext.getBean("helloWorldService");
        helloWorldService.helloWorld();

        // aop
        // 1. 设置被代理对象(Joinpoint)
        AdvisedSupport advisedSupport = new AdvisedSupport();
        TargetSource targetSource = new TargetSource(HelloWorldService.class, helloWorldService);
        advisedSupport.setTargetSource(targetSource);

        // 2. 设置拦截器(Advice)
        TimerInterceptor timerInterceptor = new TimerInterceptor();
        advisedSupport.setMethodInterceptor(timerInterceptor);

        // 3. 创建代理(Proxy)
        JdkDynamicAopProxy jdkDynamicAopProxy = new JdkDynamicAopProxy(advisedSupport);
        HelloWorldService helloWorldServiceProxy = (HelloWorldService) jdkDynamicAopProxy.getProxy();

        // 4. 基于AOP调用
        helloWorldServiceProxy.helloWorld();
    }

} 
