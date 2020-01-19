package me.icro.java.springboot.scaffold.config;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * Created by Lin on 2020/1/16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ApplicationPropertyTest {

    @Autowired
    private ApplicationProperty applicationProperty;

    @Test
    public void test() throws Exception {
        Assert.assertTrue("spring-boot-scaffold-biz".equals(applicationProperty.getName()));
    }
}