package me.icro.java.springboot.scaffold.impl;

import lombok.extern.slf4j.Slf4j;
import me.icro.java.springboot.scaffold.ScaffoldApplicationTest;
import me.icro.java.springboot.scaffold.utils.impl.RedisUtilsImpl;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * Created by Lin on 2020/1/14.
 */
@Slf4j
public class RedisUtilsImplTest extends ScaffoldApplicationTest {

    @Autowired
    private RedisUtilsImpl redisUtils;

    @Test
    public void testString() throws Exception {
        /**
         * 测试点:
         * * redis是否支持多并发
         * * key-value 的简单操作
         * * key-value 其中value是自定义类
         * * 删除
         */
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
        /**
         * 过期时间操作 测试
         */
        try {
            redisUtils.set("var1", "val1", 100);
            redisUtils.expire("var1", 5);
            Assert.assertTrue(redisUtils.getExpire("var1") <= 5);
        } finally {
            redisUtils.del("var1");
        }
    }
}