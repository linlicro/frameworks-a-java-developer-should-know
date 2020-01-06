package me.icro.java.designpatterns.structural.decorator;

/**
 * 描述:
 *
 * @author Lin
 * @since 2020-01-07 6:20 AM
 */
public class WhipCoffee implements Coffee {

    protected Coffee coffee;

    public WhipCoffee(Coffee coffee) {
        this.coffee = coffee;
    }

    @Override
    public int getCost() {
        return coffee.getCost() + 5;
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + ", wipe.";
    }
}
