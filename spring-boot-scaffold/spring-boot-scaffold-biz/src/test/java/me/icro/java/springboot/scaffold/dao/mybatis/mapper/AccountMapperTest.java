package me.icro.java.springboot.scaffold.dao.mybatis.mapper;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import me.icro.java.springboot.scaffold.ScaffoldApplicationTest;
import me.icro.java.springboot.scaffold.dao.mybatis.entity.Account;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

/**
 * 描述:
 *
 * @author Lin
 * @since 2020-02-04 10:49 上午
 */
@Slf4j
public class AccountMapperTest extends ScaffoldApplicationTest {

    @Autowired
    private AccountMapper accountMapper;

    /**
     * 测试通用Mapper - insert
     */
    @Test
    public void testInsert() {
        String salt = IdUtil.fastSimpleUUID();
        Account account = Account.builder().name("testSave3").password(SecureUtil.md5("123456" + salt)).salt(salt).email("testSave3@icro.me").phoneNumber("159110110110").status(1).lastLoginTime(new DateTime()).createTime(new DateTime()).lastUpdateTime(new DateTime()).build();
        accountMapper.insert(account);
        Assert.assertNotNull(account.getId());
        log.debug("account.id = {}", account.getId());
    }

    /**
     * 测试通用Mapper - batch insert
     */
    @Test
    public void testInsertList() {
        List<Account> accounts = Lists.newArrayList();
        for (int i = 5; i < 10; i++) {
            String salt = IdUtil.fastSimpleUUID();
            Account account = Account.builder().name("testSave" + i).password(SecureUtil.md5("123456" + salt)).salt(salt).email("testSave" + i + "@icro.me").phoneNumber("15911011011" + i).status(1).lastLoginTime(new DateTime()).createTime(new DateTime()).lastUpdateTime(new DateTime()).build();
            accounts.add(account);
        }
        int i = accountMapper.insertList(accounts);
        Assert.assertEquals(accounts.size(), i);
        List<Long> ids = accounts.stream().map(Account::getId).collect(Collectors.toList());
        log.debug("accounts - ids:" + ids);
    }

    /**
     * 测试通用Mapper - delete
     */
    @Test
    public void testDelete() {
        Long primaryKey = 1L;
        int i = accountMapper.deleteByPrimaryKey(primaryKey);
        Assert.assertEquals(1, i);
        Account account = accountMapper.selectByPrimaryKey(primaryKey);
        Assert.assertNull(account);
    }

    /**
     * 测试通用Mapper - update
     */
    @Test
    public void testUpdate() {
        Long primaryKey = 1L;
        Account account = accountMapper.selectByPrimaryKey(primaryKey);
        account.setName("update_name");
        int i = accountMapper.updateByPrimaryKeySelective(account);
        Assert.assertEquals(1, i);
        Account update = accountMapper.selectByPrimaryKey(primaryKey);
        Assert.assertNotNull(update);
        Assert.assertEquals("update_name", update.getName());
    }

    /**
     * 测试通用Mapper - select、selectAll
     */
    @Test
    public void testSelect() {
        Account account = accountMapper.selectByPrimaryKey(1L);
        Assert.assertNotNull(account);
        List<Account> accounts = accountMapper.selectAll();
        Assert.assertTrue(accounts.size() > 0);
    }

    @Test
    public void testPageHelper() {
        // init
        testInsertList();

        List<Account> accounts;
        int count = accountMapper.selectCount(null);
        // 分页
        // PageHelper 方法使用了静态的 ThreadLocal 参数，分页参数和线程是绑定的。
        PageHelper.startPage(1, 3);
        try {
            accounts = accountMapper.selectAll();
        } finally {
            PageHelper.clearPage();
        }
        PageInfo<Account> pageInfo = new PageInfo<>(accounts);
        Assert.assertEquals(3, accounts.size());
        Assert.assertEquals(count, pageInfo.getTotal());
        log.debug("page info: {}", pageInfo);
    }

    /**
     * 测试通用Mapper - select by condition
     */
    @Test
    public void testQueryByCondition() {
        Example example = new Example(Account.class);
        example.createCriteria().andGreaterThan("id", 2);
        example.setOrderByClause("id desc");

        // init
        testInsertList();

        int count = accountMapper.selectCountByExample(example);
        List<Account> accounts;
        PageHelper.startPage(1, 3);
        try {
            accounts = accountMapper.selectByExample(example);
        } finally {
            PageHelper.clearPage();
        }
        PageInfo<Account> pageInfo = new PageInfo<>(accounts);
        Assert.assertEquals(3, pageInfo.getSize());
        Assert.assertEquals(count, pageInfo.getTotal());
        log.debug("page info: {}", pageInfo);
    }
}