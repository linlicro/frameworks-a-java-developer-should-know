package me.icro.java.designpatterns.behavioral.tempelatemethod;

/**
 * 描述:
 *
 * @author Lin
 * @since 2019-12-23 9:56 PM
 */
public class Test {

    public static void main(String[] args) {
        Builder builder = new AndroidBuilder();
        builder.build();

        System.out.println();

        builder = new IosBuilder();
        builder.build();
    }
}
