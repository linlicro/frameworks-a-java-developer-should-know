package me.icro.java.designpatterns.behavioral.command;

/**
 * 命令
 *
 * Created by Lin on 2019/12/18.
 */
public interface Command {
    void execute();

    void undo();

    void redo();
}
