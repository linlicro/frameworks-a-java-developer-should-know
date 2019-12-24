package me.icro.java.designpatterns.creational.singletion;

/**
 * 描述: 单例(懒汉式，线程安全)
 *
 * @author Lin
 * @since 2019-12-24 9:54 AM
 */
public class President2 {
    private static President2 instance;

    private President2() {
    }

    private static synchronized President2 getInstance() {
        if (null == instance) {
            instance = new President2();
        }

        return instance;
    }
}
