package me.icro.java.designpatterns.creational.singletion;

/**
 * 描述: 单例(饿汉式)
 *
 * @author Lin
 * @since 2019-12-24 9:57 AM
 */
public class President3 {
    private static President3 instance = new President3();

    private President3() {
    }

    private static President3 getInstance() {
        return instance;
    }
}
