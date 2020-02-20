package me.icro.java.springboot.scaffold.elasticsearch.repository;

import me.icro.java.springboot.scaffold.elasticsearch.model.Person;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * 描述: 用户持久层
 *
 * @author Lin
 * @since 2020-02-20 3:03 下午
 */
public interface PersonRepository extends ElasticsearchRepository<Person, Long> {

    /**
     * 根据年龄区间查询
     *
     * @param min 最小值
     * @param max 最大值
     * @return 满足条件的用户列表
     */
    List<Person> findByAgeBetween(Integer min, Integer max);
}
