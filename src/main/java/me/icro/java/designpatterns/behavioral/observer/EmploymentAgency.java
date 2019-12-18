package me.icro.java.designpatterns.behavioral.observer;

import java.util.List;

/**
 * 描述: 订阅的招聘信息
 *
 * @author Lin
 * @since 2019-12-18 11:35 PM
 */
public class EmploymentAgency implements Observable {

    protected List<Observer> jobSeekers;

    @Override
    public void notify(JobPost jobPost) {
        for (Observer jobSeeker : jobSeekers) {
            jobSeeker.onJobPosted(jobPost);
        }
    }

    @Override
    public void attach(Observer observer) {
        jobSeekers.add(observer);
    }
}
