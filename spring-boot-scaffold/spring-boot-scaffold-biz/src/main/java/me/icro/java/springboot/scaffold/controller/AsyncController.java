package me.icro.java.springboot.scaffold.controller;

import me.icro.java.springboot.scaffold.model.ApiResponse;
import me.icro.java.springboot.scaffold.service.IAsyncService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * 描述:
 *
 * @author Lin
 * @since 2020-02-06 3:45 下午
 */
@RestController
@RequestMapping("/async")
public class AsyncController {

    private final IAsyncService asyncService;

    public AsyncController(IAsyncService asyncService) {
        this.asyncService = asyncService;
    }

    @GetMapping("/movies")
    public ApiResponse<String> completableFutureTask() {
        //开始时间
        long start = System.currentTimeMillis();
        // 开始执行大量的异步任务
        List<String> words = Arrays.asList("F", "T", "S", "Z", "J", "C");
        List<CompletableFuture<List<String>>> completableFutureList =
                words.stream()
                        .map(asyncService::task)
                        .collect(Collectors.toList());
        // CompletableFuture.join（）方法可以获取他们的结果并将结果连接起来
        List<List<String>> results = completableFutureList.stream().map(CompletableFuture::join).collect(Collectors.toList());
        // 打印结果以及运行程序运行花费时间
        System.out.println("Elapsed time: " + (System.currentTimeMillis() - start));
        return ApiResponse.ofSuccess(results.toString());
    }
}
