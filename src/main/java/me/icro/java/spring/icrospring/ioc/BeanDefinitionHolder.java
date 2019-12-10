package me.icro.java.spring.icrospring.ioc;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 描述:
 *
 * @author Lin
 * @since 2019-12-10 3:27 PM
 */
@RequiredArgsConstructor
@Getter
public class BeanDefinitionHolder {
    private final BeanDefinition beanDefinition;
    private final String beanName;
}
