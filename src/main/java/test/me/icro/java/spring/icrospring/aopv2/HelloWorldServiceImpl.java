package test.me.icro.java.spring.icrospring.aopv2;

/**
 * 描述:
 *
 * @author Lin
 * @since 2020-01-07 4:18 PM
 */
public class HelloWorldServiceImpl implements HelloWorldService {
    @Override
    public void helloWorld() {
        System.out.println("Hello World!");
    }
}
