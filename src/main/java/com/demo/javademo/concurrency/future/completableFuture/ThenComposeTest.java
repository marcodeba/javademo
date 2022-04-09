package com.demo.javademo.concurrency.future.completableFuture;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author：marco.pan
 * @ClassName：ThenComposeTest
 * @Description：
 * @date: 2022年01月19日 10:33 下午
 * 两个任务并行执行，返回一个新的CompletableFuture，总耗时=Max(a,b)
 */
@Slf4j
public class ThenComposeTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();
        log.info("ThenComposeTest start");
        CompletableFuture<String> firstFuture = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "1st task";
            }
        }, executor);
        CompletableFuture<String> secondFuture = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "2nd task";
            }
        }, executor).thenComposeAsync(new Function<String, CompletionStage<String>>() {
            @Override
            public CompletionStage<String> apply(String data) {
                log.info("thenComposeAsync, data:{}", data);
                log.info("firstFuture.join()={}", firstFuture.join());
                return firstFuture;
            }
        }, executor);
        log.info(secondFuture.join());
        executor.shutdown();
//        CompletableFuture<String> firstFuture = CompletableFuture.supplyAsync(() -> {
//            try {
//                TimeUnit.SECONDS.sleep(2);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            log.info("first task finished");
//            return "第一个任务";
//        }, executor);
//        //第二个异步任务
//        CompletableFuture<String> secondFuture = CompletableFuture.supplyAsync(() -> {
//            try {
//                TimeUnit.SECONDS.sleep(3);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            return "第二个任务";
//        }, executor)
//                .thenComposeAsync(data -> {
//                    log.info("thenComposeAsync, data={}", data);
//                    log.info("firstFuture.join()={}", firstFuture.join());
//                    //使用第一个任务作为返回
//                    return firstFuture;
//                }, executor);
//        log.info(secondFuture.join());
//        executor.shutdown();
    }
}
