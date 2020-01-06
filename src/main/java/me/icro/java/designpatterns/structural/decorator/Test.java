package me.icro.java.designpatterns.structural.decorator;

/**
 * 描述:
 *
 * @author Lin
 * @since 2020-01-07 6:27 AM
 */
public class Test {
    public static void main(String[] args) {
        Coffee coffee = new SimpleCoffee();
        System.out.println(coffee.getCost());
        System.out.println(coffee.getDescription());
        System.out.println("=====");

        Coffee milkCoffee = new MilkCoffee(coffee);
        System.out.println(milkCoffee.getCost());
        System.out.println(milkCoffee.getDescription());
        System.out.println("=====");

        Coffee whipCoffee = new WhipCoffee(coffee);
        System.out.println(whipCoffee.getCost());
        System.out.println(whipCoffee.getDescription());
        System.out.println("=====");

        Coffee vanillaCoffee = new VanillaCoffee(coffee);
        System.out.println(vanillaCoffee.getCost());
        System.out.println(vanillaCoffee.getDescription());
        System.out.println("=====");
    }
}
