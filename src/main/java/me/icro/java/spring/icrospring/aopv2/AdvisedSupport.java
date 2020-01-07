package me.icro.java.spring.icrospring.aopv2;

import lombok.Data;
import org.aopalliance.intercept.MethodInterceptor;

/**
 * 描述: 代理相关的元数据
 *
 * @author Lin
 * @since 2020-01-06 4:36 PM
 */
@Data
public class AdvisedSupport {
    private TargetSource targetSource; // 代理对象
    private MethodInterceptor methodInterceptor;
}
