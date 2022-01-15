package com.demo.javademo.concurrency.future;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class CompletableFutureDemo02 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
            }
            if (new Random().nextInt(10) % 2 == 0) {
                int i = 12 / 0;
            }
            System.out.println("执行结束！");
            return "test";
        });

        // 执行结束调这里（无论正常执行还是抛异常，都会执行whenComplete）
        future.whenComplete((t, action) -> System.out.println(t + " 执行完成！"));

        // 执行有异常调这里
        future.exceptionally(t -> {
            System.out.println("执行失败：" + t.getMessage());
            return "异常xxxx";
        }).join();
    }
}