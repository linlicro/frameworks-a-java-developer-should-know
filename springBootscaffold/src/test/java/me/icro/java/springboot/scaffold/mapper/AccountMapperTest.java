package me.icro.java.springboot.scaffold.mapper;

import lombok.extern.slf4j.Slf4j;
import me.icro.java.springboot.scaffold.ScaffoldApplicationTests;
import me.icro.java.springboot.scaffold.entity.Account;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

/**
 *
 * Created by Lin on 2020/1/10.
 */
@Slf4j
public class AccountMapperTest extends ScaffoldApplicationTests {

    @Autowired
    private AccountMapper accountMapper;

    @Ignore
    public void test() throws Exception {
        Assert.assertTrue(false); // 金丝雀
    }

    @Test
    public void selectAllAccount() throws Exception {
        List<Account> accounts = accountMapper.selectAllAccount();
        Assert.assertTrue(0 < accounts.size());
        log.debug("accounts: {}", accounts);
    }

    @Test
    public void selectAccountById() throws Exception {
        Account account = accountMapper.selectAccountById(1L);
        Assert.assertNotNull(account);
        log.debug("account: {}", account);
    }
}