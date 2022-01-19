package com.demo.javademo.concurrency.future.completableFuture;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author：marco.pan
 * @ClassName：AcceptEitherTest
 * @Description：
 * @date: 2022年01月19日 2:59 下午
 */
@Slf4j
public class AcceptEitherTest {
    public static void main(String[] args) {
        log.info("AcceptEitherTest start");
        //第一个异步任务，休眠2秒，保证它执行晚点
        CompletableFuture<String> first = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                log.info("执行完第一个异步任务");
            } catch (Exception e) {
                return "第一个任务异常";
            }
            return "第一个异步任务";
        });
        ExecutorService executor = Executors.newSingleThreadExecutor();
        //第二个异步任务
        CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                log.info("执行完第二个异步任务");
            } catch (Exception e) {
                return "第二个任务异常";
            }
            return "第二个任务";
        }, executor)
                //第三个任务
                .acceptEitherAsync(first, System.out::println, executor);

        executor.shutdown();
    }
}
