package me.icro.java.designpatterns.behavioral.observer;

/**
 *
 * Created by Lin on 2019/12/18.
 */
public interface Observable {
    void notify(JobPost jobPost);

    void attach(Observer observer);

}
