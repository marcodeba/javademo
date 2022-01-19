package com.demo.javademo.concurrency.future.completableFuture;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author：marco.pan
 * @ClassName：FutureWhenCompleteTest
 * @Description：
 * @date: 2022年01月19日 2:24 下午
 * <p>
 * CompletableFuture的whenComplete方法表示，某个任务执行完成后，执行的回调方法，无返回值；
 * 并且whenComplete方法返回的CompletableFuture的result是上个任务的结果。
 */
@Slf4j
public class FutureWhenCompleteTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> orgFuture = CompletableFuture.supplyAsync(
                () -> {
                    log.info("当前线程名称：{}", Thread.currentThread().getName());
                    try {
                        Thread.sleep(2000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return "捡田螺的小男孩";
                }
        );

        CompletableFuture<String> rstFuture = orgFuture.whenComplete((a, throwable) -> {
            log.info("当前线程名称：{}", Thread.currentThread().getName());
            log.info("上个任务执行完啦，还把 {} 传过来", a);
            if ("捡田螺的小男孩".equals(a)) {
                log.info("666");
            }
            log.info("233333");
        });

        log.info(rstFuture.get());
    }
}
