package me.icro.java.spring.icrospring.ioc;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 描述:
 *
 * @author Lin
 * @since 2019-12-10 2:47 PM
 */
@RequiredArgsConstructor
@Getter
public class PropertyValue {
    private final String name;
    private final Object value;
}
