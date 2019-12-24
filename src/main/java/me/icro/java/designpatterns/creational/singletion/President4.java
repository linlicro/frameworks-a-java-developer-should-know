package me.icro.java.designpatterns.creational.singletion;

/**
 * 描述: 单例(DCL, double-checked locking)
 *
 * @author Lin
 * @since 2019-12-24 10:01 AM
 */
public class President4 {
    private static President4 instance;

    private President4() {
    }

    private static President4 getInstance() {
        if (null == instance) {
            synchronized (President4.class) {
                instance = new President4();
            }
        }
        return instance;
    }
}
