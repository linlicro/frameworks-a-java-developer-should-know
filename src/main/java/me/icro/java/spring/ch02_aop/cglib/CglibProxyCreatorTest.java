package me.icro.java.spring.ch02_aop.cglib;

import me.icro.java.spring.ch02_aop.jdkproxy.ProxyCreator;

/**
 * 描述:
 *
 * @author Lin
 * @since 2019-12-18 9:59 PM
 */
public class CglibProxyCreatorTest {
    public static void main(String[] args) {
        ProxyCreator proxyCreator = new CglibProxyCreator(new UserServiceImpl(), new UserServiceImplInterceptor());
        UserServiceImpl userService = (UserServiceImpl) proxyCreator.getProxy();

        System.out.println("proxy class = " + userService.getClass());
        System.out.println();
        userService.save(null);
        System.out.println();
        userService.update(null);
    }
}
