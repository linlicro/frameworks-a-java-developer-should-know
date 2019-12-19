package me.icro.java.spring.icrospring.aop;

/**
 * 描述:
 *
 * @author Lin
 * @since 2019-12-19 11:10 AM
 */
public class SimpleAOPTest {
    public static void main(String[] args) {
        MethodInvocation methodInvocation = () -> System.out.println("log sth...");
        HelloService helloService = new HelloServiceImpl();

        Advice beforeAdvice = new BeforeAdvice(helloService, methodInvocation);

        HelloService helloServiceProxy = (HelloService) SimpleAOP.getProxy(helloService, beforeAdvice);

        helloServiceProxy.sayHello();
    }
}
