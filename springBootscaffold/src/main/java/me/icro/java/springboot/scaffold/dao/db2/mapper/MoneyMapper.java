package me.icro.java.springboot.scaffold.dao.db2.mapper;

import me.icro.java.springboot.scaffold.dao.db2.domain.Money;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 描述:
 *
 * @author Lin
 * @since 2020-01-16 5:12 PM
 */
@Mapper
@Component
public interface MoneyMapper {

    /**
     * 获取所有的数据
     * @return
     */
    @Select("SELECT * FROM sc_money;")
    List<Money> selectAllMoney();

    /**
     * 保存数据
     * @param money
     * @return
     */
    int saveMoney(@Param("monry") Money money);
}
