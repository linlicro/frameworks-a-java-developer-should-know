package me.icro.java.springboot.scaffold.service.impl;

import lombok.extern.slf4j.Slf4j;
import me.icro.java.springboot.scaffold.service.IAsyncService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * 描述:
 *
 * @author Lin
 * @since 2020-02-06 3:40 下午
 */
@Service
@Slf4j
public class AsyncServiceImpl implements IAsyncService {

    private List<String> movies = new ArrayList<>(
            Arrays.asList(
                    "Forrest Gump",
                    "Titanic",
                    "Spirited Away",
                    "The Shawshank Redemption",
                    "Zootopia",
                    "Farewell ",
                    "Joker",
                    "Crawl"));


    @Override
    @Async
    public CompletableFuture<List<String>> task(String start) {
        // 打印日志
        log.info(Thread.currentThread().getName() + "start this task!");
        // 找到特定字符/字符串开头的电影
        List<String> results =
                movies.stream().filter(movie -> movie.startsWith(start)).collect(Collectors.toList());
        // 模拟这是一个耗时的任务
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //返回一个已经用给定值完成的新的CompletableFuture。
        return CompletableFuture.completedFuture(results);
    }
}
