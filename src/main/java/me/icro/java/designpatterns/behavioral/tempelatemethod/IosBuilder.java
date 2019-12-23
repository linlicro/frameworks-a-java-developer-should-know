package me.icro.java.designpatterns.behavioral.tempelatemethod;

/**
 * 描述:
 *
 * @author Lin
 * @since 2019-12-23 9:55 PM
 */
public class IosBuilder extends Builder {
    @Override
    public void test() {
        System.out.println("Running ios tests");
    }

    @Override
    public void lint() {
        System.out.println("Linting the ios code");
    }

    @Override
    public void assemble() {
        System.out.println("Assembling the ios build");
    }

    @Override
    public void deploy() {
        System.out.println("Deploying ios build to server");
    }
}
