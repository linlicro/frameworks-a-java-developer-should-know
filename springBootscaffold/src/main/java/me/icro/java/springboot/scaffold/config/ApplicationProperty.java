package me.icro.java.springboot.scaffold.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 描述: 项目配置
 *
 * @author Lin
 * @since 2020-01-16 3:57 PM
 */
@Data
@Component
public class ApplicationProperty {
    @Value("${application.name}")
    private String name;
    @Value("${application.version}")
    private String version;
}
