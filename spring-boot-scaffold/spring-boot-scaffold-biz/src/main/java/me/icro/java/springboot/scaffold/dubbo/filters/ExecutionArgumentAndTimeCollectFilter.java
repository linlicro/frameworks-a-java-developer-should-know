package me.icro.java.springboot.scaffold.dubbo.filters;

import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;
import com.alibaba.dubbo.rpc.support.RpcUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * 描述:
 *
 * @author Lin
 * @since 2020-01-19 3:32 PM
 */
@Activate
@Slf4j
public class ExecutionArgumentAndTimeCollectFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        String clientIp = RpcContext.getContext().getRemoteHost();
        log.info("[ExecutionArgumentAndTimeCollectFilter]client {} trying to invoke service {} method {} with arguments {}", clientIp, invoker.getInterface().getName(),
                RpcUtils.getMethodName(invocation),
                Arrays.toString(RpcUtils.getArguments(invocation)));
        long start = System.currentTimeMillis();
        Result result;
        try {
            result = invoker.invoke(invocation);
            log.info("[ExecutionArgumentAndTimeCollectFilter]got result of method {} - {}", RpcUtils.getMethodName(invocation), result.getValue());
            return result;
        } catch (Error e) {
            log.error("[ExecutionArgumentAndTimeCollectFilter]got error unchecked and undeclared exception which called by " + RpcContext.getContext().getRemoteHost()
                    + ". service: " + invoker.getInterface().getName() + ", method: " + invocation.getMethodName()
                    + ", exception: " + e.getClass().getName() + ": " + e.getMessage(), e);
            throw new RuntimeException(e);
        } finally {
            log.info("[ExecutionArgumentAndTimeCollectFilter]client {} took {}ms to execute {}", clientIp, System.currentTimeMillis() - start, RpcUtils.getMethodName(invocation));
        }
    }
}
