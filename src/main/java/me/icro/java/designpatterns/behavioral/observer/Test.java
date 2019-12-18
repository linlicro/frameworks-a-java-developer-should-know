package me.icro.java.designpatterns.behavioral.observer;

/**
 * 描述:
 *
 * @author Lin
 * @since 2019-12-18 11:42 PM
 */
public class Test {
    public static void main(String[] args) {
        JobSeeker jobSeeker = new JobSeeker("Linnn");
        JobSeeker jobSeeker1 = new JobSeeker("Jack");

        EmploymentAgency employmentAgency = new EmploymentAgency();
        employmentAgency.attach(jobSeeker);
        employmentAgency.attach(jobSeeker1);

        employmentAgency.notify(new JobPost("Software Engineer"));
    }
}
