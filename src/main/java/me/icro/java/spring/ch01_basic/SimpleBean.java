package me.icro.java.spring.ch01_basic;

/**
 * 描述:
 *
 * @author Lin
 * @since 2019-11-18 5:49 PM
 */
public class SimpleBean {

    private String message;

    public void send() {
        System.out.println("I am send method from SimpleBean!");
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
