package me.icro.java.designpatterns.behavioral.command;

/**
 * 描述:
 *
 * @author Lin
 * @since 2019-12-18 6:56 AM
 */
public class TurnOff implements Command {

    private Bulb bulb;

    public TurnOff(Bulb bulb) {
        this.bulb = bulb;
    }

    @Override
    public void execute() {
        bulb.turnOff();
    }

    @Override
    public void undo() {
        bulb.turnOn();
    }

    @Override
    public void redo() {
        this.execute();
    }
}
