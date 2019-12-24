package me.icro.java.designpatterns.creational.singletion;

/**
 * 描述: 单例(懒汉式，线程不安全)
 *
 * @author Lin
 * @since 2019-12-24 9:49 AM
 */
public class President {
    private static President instance;

    private President() {
    }

    private static President getInstance() {
        if (null == instance) {
            instance = new President();
        }

        return instance;
    }
}
