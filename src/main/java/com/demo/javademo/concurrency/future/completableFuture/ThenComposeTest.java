package com.demo.javademo.concurrency.future.completableFuture;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @author：marco.pan
 * @ClassName：ThenComposeTest
 * @Description：
 * @date: 2022年01月19日 10:33 下午
 */
@Slf4j
public class ThenComposeTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();
        log.info("ThenComposeTest start");
        CompletableFuture<String> f = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("first task finished");
            return "第一个任务";
        }, executor);
        //第二个异步任务
        CompletableFuture<String> future = CompletableFuture
                .supplyAsync(() -> {
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return "第二个任务";
                }, executor)
                .thenComposeAsync(data -> {
                    log.info(data);
                    //使用第一个任务作为返回
                    return f;
                }, executor);
        log.info(future.join());
        executor.shutdown();
    }
}
