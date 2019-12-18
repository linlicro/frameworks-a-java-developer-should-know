package me.icro.java.spring.ch02_aop.cglib;

/**
 * 描述: 目标类
 *
 * @author Lin
 * @since 2019-12-18 9:53 PM
 */
public class UserServiceImpl {

    public void save(User user) {
        System.out.println("save user.");
    }

    public void update(User user) {
        System.out.println("update user.");
    }
}
