package me.icro.java.designpatterns.behavioral.command;

/**
 * 描述: Receiver
 *
 * @author Lin
 * @since 2019-12-18 6:50 AM
 */
public class Bulb {

    public void turnOn() {
        System.out.println("Bulb has been lit");
    }

    public void turnOff() {
        System.out.println("Darkness!");
    }
}
