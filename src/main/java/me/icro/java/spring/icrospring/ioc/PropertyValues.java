package me.icro.java.spring.icrospring.ioc;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * 描述:
 *
 * @author Lin
 * @since 2019-12-10 3:22 PM
 */
@RequiredArgsConstructor
@Builder
@Getter
public class PropertyValues {
    private final List<PropertyValue> propertyValueList;

}
