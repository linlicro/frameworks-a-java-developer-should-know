package me.icro.java.spring.icrospring.iocv2.factory;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述: 包装一个对象的所有属性
 *
 * @author Lin
 * @since 2019-12-25 12:45 PM
 */
public class PropertyValues {

    private List<PropertyValue> propertyValues = new ArrayList<>();

    public PropertyValues() {
    }

    public void addPropertyValue(PropertyValue propertyValue) {
        propertyValues.add(propertyValue);
    }

    public List<PropertyValue> getPropertyValues() {
        return propertyValues;
    }
}
