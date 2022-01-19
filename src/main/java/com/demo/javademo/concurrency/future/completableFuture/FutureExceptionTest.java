package com.demo.javademo.concurrency.future.completableFuture;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author：marco.pan
 * @ClassName：FutureExceptionTest
 * @Description：
 * @date: 2022年01月19日 2:22 下午
 * <p>
 * CompletableFuture的exceptionally方法表示，某个任务执行异常时，执行的回调方法;
 * 并且有抛出异常作为参数，传递到回调方法。
 */
@Slf4j
public class FutureExceptionTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> orgFuture = CompletableFuture.supplyAsync(
                () -> {
                    log.info("当前线程名称：{}", Thread.currentThread().getName());
                    throw new RuntimeException();
                }
        );

        CompletableFuture<String> exceptionFuture = orgFuture.exceptionally((e) -> {
            e.printStackTrace();
            return "你的程序异常啦";
        });

        log.info(exceptionFuture.get());
    }
}
