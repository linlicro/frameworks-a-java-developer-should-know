# 单例模式(Singleton)

## 目的

> 保证一个类仅有一个实例，并提供一个访问它的全局访问点。

## 现实世界的例子

> 一次只能有一个国家的总统。无论何时打电话，都必须将同一位总统付诸行动。这里的总统是单身人士。

## 程序化

```java
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

    private static synchronized President3 getInstance() {
        return instance;
    }
}

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

/**
 * 描述: 单例(枚举方式)
 *
 * Joshua Bloch, Effective Java 2nd Edition p.18 > A single-element enum type is the best way to implement a singleton
 *
 * @author Lin
 * @since 2019-12-24 10:23 AM
 */
public enum President6 {
    INSTANCE;

    public void method() {

    }
}
```

## 何时使用

> 当您想控制实例数目，节省系统资源的时候。

## 参考

* [design-patterns-for-humans](https://github.com/kamranahmedse/design-patterns-for-humans)
* [singleton](https://github.com/iluwatar/java-design-patterns/tree/master/singleton)
* [单例模式](https://www.runoob.com/design-pattern/singleton-pattern.html)
