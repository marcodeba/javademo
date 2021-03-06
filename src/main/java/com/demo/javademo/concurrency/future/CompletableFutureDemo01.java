package com.demo.javademo.concurrency.future;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Slf4j
public class CompletableFutureDemo01 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Runnable runnable = () -> log.info("执行无返回结果的异步任务");
        // 执行无返回值得异步任务
        CompletableFuture.runAsync(runnable);

        // 执行有返回值的异步任务，返回值类型是String
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            log.info("执行有返回值的异步任务");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hello World";
        });
        log.info(completableFuture.join());
    }
}