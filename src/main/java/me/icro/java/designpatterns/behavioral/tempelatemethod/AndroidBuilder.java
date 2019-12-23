package me.icro.java.designpatterns.behavioral.tempelatemethod;

/**
 * 描述:
 *
 * @author Lin
 * @since 2019-12-23 9:54 PM
 */
public class AndroidBuilder extends Builder {
    @Override
    public void test() {
        System.out.println("Running android tests");
    }

    @Override
    public void lint() {
        System.out.println("Linting the android code");
    }

    @Override
    public void assemble() {
        System.out.println("Assembling the android build");
    }

    @Override
    public void deploy() {
        System.out.println("Deploying android build to server");
    }
}
