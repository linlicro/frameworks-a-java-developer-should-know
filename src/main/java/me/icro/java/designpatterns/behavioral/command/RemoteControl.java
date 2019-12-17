package me.icro.java.designpatterns.behavioral.command;

/**
 * 描述: Invoker
 *
 * @author Lin
 * @since 2019-12-18 6:57 AM
 */
public class RemoteControl {
    public void submit(Command command) {
        command.execute();
    }
}
