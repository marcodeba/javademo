package com.demo.javademo.concurrency.future.completableFuture;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author：marco.pan
 * @ClassName：FutureThenAcceptTest
 * @Description：
 * @date: 2022年01月19日 2:13 下午
 * <p>
 * CompletableFuture的thenAccept方法表示，第一个任务执行完成后，执行第二个回调方法任务，会将该任务的执行结果作为入参，
 * 传递到回调方法中，但是回调方法是没有返回值的。
 */
@Slf4j
public class FutureThenAcceptTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        log.info("FutureThenAcceptTest start");

        CompletableFuture<String> orgFuture = CompletableFuture.supplyAsync(
                () -> {
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    log.info("原始CompletableFuture方法任务");
                    return "捡田螺的小男孩";
                }
        );

        CompletableFuture<Void> thenAcceptFuture = orgFuture.thenAccept((result) -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if ("捡田螺的小男孩".equals(result)) {
                log.info("关注了{}", result);
            }
            log.info("先考虑考虑");
        });
        log.info("thenAcceptFuture.get: {}", thenAcceptFuture.get());
    }
}
