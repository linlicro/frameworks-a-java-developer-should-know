package me.icro.java.spring.icrospring.aop;

/**
 * 描述:
 *
 * @author Lin
 * @since 2019-12-19 11:10 AM
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public void sayHello() {
        System.out.println("hello...");
    }
}
