package me.icro.java.springboot.scaffold.dao.db2.mapper;

import lombok.extern.slf4j.Slf4j;
import me.icro.java.springboot.scaffold.ScaffoldApplicationTest;
import me.icro.java.springboot.scaffold.dao.db2.domain.Money;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 描述:
 *
 * @author Lin
 * @since 2020-01-16 6:02 PM
 */
@Slf4j
public class MoneyMapperTest extends ScaffoldApplicationTest {

    @Autowired
    private MoneyMapper moneyMapper;

    @Test
    public void testSelectAll() throws Exception {
        List<Money> monies = moneyMapper.selectAllMoney();
        Assert.assertTrue(0 < monies.size());
        log.debug("monies: {}", monies);
    }
}