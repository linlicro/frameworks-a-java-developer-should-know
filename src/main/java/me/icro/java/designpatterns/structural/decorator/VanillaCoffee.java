package me.icro.java.designpatterns.structural.decorator;

/**
 * 描述:
 *
 * @author Lin
 * @since 2020-01-07 6:21 AM
 */
public class VanillaCoffee implements Coffee {

    protected Coffee coffee;

    public VanillaCoffee(Coffee coffee) {
        this.coffee = coffee;
    }

    @Override
    public int getCost() {
        return coffee.getCost() + 3;
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + ", vanilla.";
    }
}
