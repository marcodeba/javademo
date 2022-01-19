package com.demo.javademo.concurrency.future.completableFuture;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author：marco.pan
 * @ClassName：allOfFutureTest
 * @Description：
 * @date: 2022年01月19日 3:05 下午
 */
@Slf4j
public class AllOfFutureTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture<Void> a = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "我执行完了");
        });
        CompletableFuture<Void> b = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "我也执行完了");
        });
        CompletableFuture<Void> allOfFuture = CompletableFuture.allOf(a, b).whenComplete((m, k) ->
                System.out.println(Thread.currentThread().getName() + "-finish"));
    }
}
