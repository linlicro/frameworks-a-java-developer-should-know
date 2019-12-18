package me.icro.java.spring.ch02_aop.jdkproxy;

/**
 * 描述:
 *
 * @author Lin
 * @since 2019-12-18 9:35 PM
 */
public class UserServiceImpl implements UserService {

    @Override
    public void save(User user) {
        System.out.println("save user.");
    }

    @Override
    public void update(User user) {
        System.out.println("update user.");
    }
}
