package com.demo.javademo.concurrency.future.completableFuture;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author：marco.pan
 * @ClassName：AnyOfFutureTest
 * @Description：
 * @date: 2022年01月19日 10:27 下午
 */
@Slf4j
public class AnyOfFutureTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture<Void> a = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("我执行完了");
        });
        CompletableFuture<Void> b = CompletableFuture.runAsync(() -> log.info("我也执行完了"));
        CompletableFuture<Object> anyOfFuture = CompletableFuture.anyOf(a, b).whenComplete((m, k) -> {
            log.info("finish");
//            return "捡田螺的小男孩";
        });
        anyOfFuture.join();
    }
}
