package me.icro.java.designpatterns.structural.decorator;

/**
 * 描述:
 *
 * @author Lin
 * @since 2020-01-07 6:18 AM
 */
public class MilkCoffee implements Coffee {

    protected Coffee coffee;

    public MilkCoffee(Coffee coffee) {
        this.coffee = coffee;
    }

    @Override
    public int getCost() {
        return coffee.getCost() + 2;
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + ", Milk.";
    }
}
