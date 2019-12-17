package me.icro.java.designpatterns.behavioral.command;

/**
 * 描述:
 *
 * @author Lin
 * @since 2019-12-18 6:59 AM
 */
public class Client {

    private RemoteControl remoteControl;

    public Client(RemoteControl remoteControl) {
        this.remoteControl = remoteControl;
    }

    public void executeCommand(Command command) {
        remoteControl.submit(command);
    }

    public static void main(String[] args) {
        Bulb bulb = new Bulb();

        TurnOn turnOn = new TurnOn(bulb);
        TurnOff turnOff = new TurnOff(bulb);

        RemoteControl remoteControl = new RemoteControl();
        Client client = new Client(remoteControl);

        client.executeCommand(turnOn);
        client.executeCommand(turnOff);
    }
}
