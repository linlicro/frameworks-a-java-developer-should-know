package me.icro.java.designpatterns.behavioral.tempelatemethod;

/**
 * 描述:
 *
 * @author Lin
 * @since 2019-12-23 9:52 PM
 */
public abstract class Builder {

    public abstract void test();

    public abstract void lint();

    public abstract void assemble();

    public abstract void deploy();

    final public void build() {
        test();
        lint();
        assemble();
        deploy();
    }
}
