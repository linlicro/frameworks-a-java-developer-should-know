package me.icro.java.spring.ch02_aop;

/**
 * 描述:
 *
 * @author Lin
 * @since 2019-12-18 5:09 PM
 */
public class SubjectImpl implements Subject {
    @Override
    public void login() {
        System.out.println("login...");
    }

    @Override
    public void download() {
        System.out.println("download...");
    }
}
