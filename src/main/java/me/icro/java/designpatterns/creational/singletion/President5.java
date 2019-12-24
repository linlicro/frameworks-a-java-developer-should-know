package me.icro.java.designpatterns.creational.singletion;

/**
 * 描述: 单例(登记式)
 *
 * @author Lin
 * @since 2019-12-24 10:05 AM
 */
public class President5 {
    private static class SingletonHolder {
        private static final President5 INSTANCE = new President5();
    }

    private President5() {
    }

    public static final President5 getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
