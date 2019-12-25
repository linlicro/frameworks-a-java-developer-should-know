package me.icro.java.spring.icrospring.iocv2.factory;

import lombok.Builder;
import lombok.Data;

/**
 * 描述: bean的属性
 *
 * @author Lin
 * @since 2019-12-25 11:37 AM
 */
@Data
@Builder
public class PropertyValue {
    private String key;
    private Object value;
}
