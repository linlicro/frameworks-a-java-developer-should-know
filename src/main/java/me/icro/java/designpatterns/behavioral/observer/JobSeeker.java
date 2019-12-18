package me.icro.java.designpatterns.behavioral.observer;

/**
 * 描述:
 *
 * @author Lin
 * @since 2019-12-18 11:31 PM
 */
public class JobSeeker implements Observer {

    private String name;

    public JobSeeker(String name) {
        this.name = name;
    }

    @Override
    public void onJobPosted(JobPost jobPost) {
        // 有新的职位招聘发布
        System.out.println("Hola, " + this.name + "! New job posted: " + jobPost.getTitle());
    }
}
