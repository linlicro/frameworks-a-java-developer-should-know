package me.icro.java.spring.ch02_aop;

/**
 * 描述: 切面
 *
 * @author Lin
 * @since 2019-12-18 5:10 PM
 */
public class PermissionVerification {
    public void canLogin() {
        System.out.println("校验 通过啦！");
    }

    public void saveMessage() {
        System.out.println("处理 完成啦!");
    }
}
