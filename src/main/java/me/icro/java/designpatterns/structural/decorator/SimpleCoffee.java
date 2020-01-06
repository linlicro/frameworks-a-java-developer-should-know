package me.icro.java.designpatterns.structural.decorator;

/**
 * 描述:
 *
 * @author Lin
 * @since 2020-01-07 6:17 AM
 */
public class SimpleCoffee implements Coffee {
    @Override
    public int getCost() {
        return 10;
    }

    @Override
    public String getDescription() {
        return "Simple coffee";
    }
}
