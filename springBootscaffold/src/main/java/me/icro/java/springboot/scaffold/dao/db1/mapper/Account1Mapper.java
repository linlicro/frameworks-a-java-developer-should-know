package me.icro.java.springboot.scaffold.dao.db1.mapper;

import me.icro.java.springboot.scaffold.dao.db1.domain.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 描述:
 *
 * @author Lin
 * @since 2020-01-16 5:11 PM
 */
@Mapper
@Component
public interface Account1Mapper {
    /**
     * 查询所有的账号
     *
     * @return 账号列表
     */
    @Select("SELECT * FROM sc_account")
    List<Account> selectAllAccount();

    /**
     * 根据id查询账号
     *
     * @param id 主键id
     * @return 主键为id的用户，不存在则是{@code null}
     */
    @Select("SELECT * from sc_account WHERE id = #{id}")
    Account selectAccountById(@Param("id") Long id);

    /**
     * 保存账号
     *
     * @param account 账号
     * @return 成功 - {@code 1} 失败 - {@code 0}
     */
    int saveUser(@Param("account") Account account);

    /**
     * 删除账号
     *
     * @param id 主键
     * @return 成功 - {@code 1} 失败 - {@code 0}
     */
    int deleteById(@Param("id") Long id);
}
