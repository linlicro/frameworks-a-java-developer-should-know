package me.icro.java.springboot.scaffold.utils.impl;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import me.icro.java.springboot.scaffold.ScaffoldApplicationTests;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

/**
 * Created by Lin on 2020/1/14.
 */
@Slf4j
public class RedisUtilsImplTest extends ScaffoldApplicationTests {

    @Autowired
    private RedisUtilsImpl redisUtils;

    @Test
    public void testString() throws Exception {
        try {
            ExecutorService executorService = Executors.newFixedThreadPool(1000);
            IntStream.range(0, 1000).forEach(i -> executorService.execute(() -> redisUtils.incr("count", 1)));

            redisUtils.set("var1", "val1");
            String strFromCache = (String) redisUtils.get("var1");
            Assert.assertTrue(redisUtils.hasKey("var1"));
            Assert.assertEquals("val1", strFromCache);
            log.debug("[var1] = {}", strFromCache);

            User user = new User();
            user.setId(1);
            user.setName("name");
            redisUtils.set("user1", user);
            User userFromCache = (User) redisUtils.get("user1");
            Assert.assertEquals(user, userFromCache);
            log.debug("[user1] = {}", userFromCache);

            Assert.assertEquals(1000, redisUtils.get("count"));
            log.debug("[count] = {}", redisUtils.get("count"));
            redisUtils.decr("count", 1);
            log.debug("[count] = {}", redisUtils.get("count"));
            Assert.assertEquals(999, redisUtils.get("count"));

            redisUtils.del("count", "var1", "user1");

            Assert.assertFalse(redisUtils.hasKey("count"));
            Assert.assertFalse(redisUtils.hasKey("var1"));
            Assert.assertFalse(redisUtils.hasKey("user1"));
        } finally {
            redisUtils.del("count", "var1", "user1");
        }
    }

    @Test
    public void expire() throws Exception {
        try {
            redisUtils.set("var1", "val1", 10);
            Assert.assertEquals(10, redisUtils.getExpire("var1"));
            redisUtils.expire("var1", 5);
            Assert.assertEquals(5, redisUtils.getExpire("var1"));
        } finally {
            redisUtils.del("var1");
        }
    }
}