package me.icro.java.designpatterns.behavioral.command;

/**
 * 描述:
 *
 * @author Lin
 * @since 2019-12-18 6:55 AM
 */
public class TurnOn implements Command {

    private Bulb bulb;

    public TurnOn(Bulb bulb) {
        this.bulb = bulb;
    }

    @Override
    public void execute() {
        bulb.turnOn();
    }

    @Override
    public void undo() {
        bulb.turnOff();
    }

    @Override
    public void redo() {
        this.execute();
    }
}
