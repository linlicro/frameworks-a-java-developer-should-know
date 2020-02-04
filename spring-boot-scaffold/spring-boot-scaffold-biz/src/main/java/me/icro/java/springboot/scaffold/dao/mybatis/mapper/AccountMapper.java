package me.icro.java.springboot.scaffold.dao.mybatis.mapper;

import me.icro.java.springboot.scaffold.dao.mybatis.entity.Account;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 描述: 集成 通用Mapper 和 pagehelper
 *
 * @author Lin
 * @since 2020-02-04 10:30 上午
 */
@Repository
@Component
public interface AccountMapper extends Mapper<Account>, MySqlMapper<Account> {
}
