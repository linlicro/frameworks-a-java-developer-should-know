package me.icro.java.springboot.scaffold.config;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * 描述:
 *
 * @author Lin
 * @since 2020-01-16 4:08 PM
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class DeveloperPropertyTest {

    @Autowired
    private DeveloperProperty developerProperty;

    @Test
    public void test() throws Exception {
        Assert.assertTrue("icro".equals(developerProperty.getName()));
        Assert.assertTrue("http://blog.icro.me/".equals(developerProperty.getWebsite()));
        Assert.assertTrue("lnetmor".equals(developerProperty.getWechat()));
    }
}