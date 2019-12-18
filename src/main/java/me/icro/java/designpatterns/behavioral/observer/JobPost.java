package me.icro.java.designpatterns.behavioral.observer;

import lombok.Getter;

/**
 * 描述:
 *
 * @author Lin
 * @since 2019-12-18 11:30 PM
 */
@Getter
public class JobPost {

    private String title;

    public JobPost(String title) {
        this.title = title;
    }
}
