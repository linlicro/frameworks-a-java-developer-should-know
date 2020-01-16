package me.icro.java.springboot.scaffold.dao.db1.mapper;

import lombok.extern.slf4j.Slf4j;
import me.icro.java.springboot.scaffold.ScaffoldApplicationTests;
import me.icro.java.springboot.scaffold.dao.db1.domain.Account;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

/**
 * 描述:
 *
 * @author Lin
 * @since 2020-01-16 5:43 PM
 */
@Slf4j
public class Account1MapperTest extends ScaffoldApplicationTests {

    @Autowired
    private Account1Mapper account1Mapper;

    @Test
    public void selectAllTest() throws Exception {
        List<Account> accounts = account1Mapper.selectAllAccount();
        Assert.assertTrue(0 < accounts.size());
        log.debug("accounts: {}", accounts);
    }
}