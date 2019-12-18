package me.icro.java.spring.ch02_aop;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 描述:
 *
 * @author Lin
 * @since 2019-12-18 5:27 PM
 */
public class Test {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("ch02_aop/aop.xml");

        Subject subject1 = (Subject) context.getBean("subject1");
        Subject subject2 = (Subject) context.getBean("subject2");

        subject1.login();
        subject1.download();

        System.out.println("============");

        subject2.login();
        subject2.download();
    }
}
