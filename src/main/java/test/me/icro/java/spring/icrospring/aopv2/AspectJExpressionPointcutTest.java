package test.me.icro.java.spring.icrospring.aopv2;

import me.icro.java.spring.icrospring.aopv2.AspectJExpressionPointcut;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

/**
 * AspectJExpressionPointcut Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Jan 8, 2020</pre>
 */
public class AspectJExpressionPointcutTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: matches(Class targetClass)
     */
    @Test
    public void testMatchesTargetClass() throws Exception {
        String expression = "execution(* test.me.icro.java.spring.icrospring.aopv2.*.*(..))";
        AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut();
        aspectJExpressionPointcut.setExpression(expression);
        boolean matches = aspectJExpressionPointcut.getClassFilter().matches(HelloWorldServiceImpl.class);
        Assert.assertTrue(matches);
    }

    /**
     * Method: matches(Method method, Class targetClass)
     */
    @Test
    public void testMatchesForMethodTargetClass() throws Exception {
        String expression = "execution(* test.me.icro.java.spring.icrospring.aopv2.*.*(..))";
        AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut();
        aspectJExpressionPointcut.setExpression(expression);
        boolean matches = aspectJExpressionPointcut.getMethodFilter().matches(HelloWorldServiceImpl.class.getDeclaredMethod("helloWorld"), HelloWorldServiceImpl.class);
        Assert.assertTrue(matches);
    }

}
