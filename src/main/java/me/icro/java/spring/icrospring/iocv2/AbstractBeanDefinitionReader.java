package me.icro.java.spring.icrospring.iocv2;

import me.icro.java.spring.icrospring.iocv2.io.ResourceLoader;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述:
 *
 * @author Lin
 * @since 2019-12-25 1:58 PM
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {

    private Map<String, BeanDefinition> registry;

    private ResourceLoader resourceLoader;

    public AbstractBeanDefinitionReader(ResourceLoader resourceLoader) {
        this.registry = new HashMap<>();
        this.resourceLoader = resourceLoader;
    }

    public Map<String, BeanDefinition> getRegistry() {
        return registry;
    }

    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }
}
