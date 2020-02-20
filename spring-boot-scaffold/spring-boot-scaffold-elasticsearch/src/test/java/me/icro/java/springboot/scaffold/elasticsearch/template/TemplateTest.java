package me.icro.java.springboot.scaffold.elasticsearch.template;

import me.icro.java.springboot.scaffold.elasticsearch.SearchApplicationTest;
import me.icro.java.springboot.scaffold.elasticsearch.model.Person;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

/**
 * 描述: 测试 ElasticTemplate 的创建/删除
 *
 * @author Lin
 * @since 2020-02-20 3:07 下午
 */
public class TemplateTest extends SearchApplicationTest {
    @Autowired
    private ElasticsearchTemplate template;

    /**
     * 测试 ElasticTemplate 创建 index
     */
    @Test
    public void testCreateIndex() {
        // 创建索引，会根据Item类的@Document注解信息来创建
        template.createIndex(Person.class);

        // 配置映射，会根据Item类中的id、Field等字段来自动完成映射
        template.putMapping(Person.class);
    }

    /**
     * 测试 ElasticTemplate 删除 index
     */
    @Test
    public void testDeleteIndex() {
        template.deleteIndex(Person.class);
    }
}
