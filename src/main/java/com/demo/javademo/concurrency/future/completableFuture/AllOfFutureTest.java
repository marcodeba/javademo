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

        log.info("AnyOfFutureTest start");
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "future1 finished";
        });

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "future2 finished";
        });

        CompletableFuture<Void> combindFuture = CompletableFuture.allOf(future1, future2);
        try {
            combindFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        log.info("future1:{}，future2: {}", future1.get(), future2.get());

//        CompletableFuture<Void> a = CompletableFuture.runAsync(() -> {
//            System.out.println(Thread.currentThread().getName() + "我执行完了");
//        });
//        CompletableFuture<Void> b = CompletableFuture.runAsync(() -> {
//            try {
//                TimeUnit.SECONDS.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(Thread.currentThread().getName() + "我也执行完了");
//        });
//        CompletableFuture<Void> allOfFuture = CompletableFuture.allOf(a, b).whenComplete((m, k) ->
//                System.out.println(Thread.currentThread().getName() + "-finish"));
    }
}
