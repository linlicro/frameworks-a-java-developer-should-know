package test.me.icro.java.spring.icrospring.iocv2;

/**
 * 描述:
 *
 * @author Lin
 * @since 2019-12-25 1:48 PM
 */
public class HelloWorldService {
    private String text;

    private HelloWorldService2 helloWorldService2;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void helloWorld() {
        System.out.println(this.text);
    }

    public HelloWorldService2 getHelloWorldService2() {
        return helloWorldService2;
    }

    public void setHelloWorldService2(HelloWorldService2 helloWorldService2) {
        this.helloWorldService2 = helloWorldService2;
    }
}
