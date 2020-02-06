package me.icro.java.springboot.scaffold.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * 描述:
 *
 * @author Lin
 * @since 2020-02-06 3:35 下午
 */
public interface IAsyncService {

    /**
     * 异步任务
     * @param start
     * @return
     */
    CompletableFuture<List<String>> task(String start);
}
