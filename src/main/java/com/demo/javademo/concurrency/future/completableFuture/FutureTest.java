package com.demo.javademo.concurrency.future.completableFuture;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author：marco.pan
 * @ClassName：FutureTest
 * @Description：
 * @date: 2022年01月19日 5:54 下午
 * <p>
 * <p>
 * runAsync执行CompletableFuture任务，没有返回值。
 * supplyAsync执行CompletableFuture任务，支持返回值。
 */
@Slf4j
public class FutureTest {
    public static void main(String[] args) {
        //可以自定义线程池
        ExecutorService executor = Executors.newCachedThreadPool();
        //runAsync执行CompletableFuture任务，没有返回值。
        CompletableFuture<Void> runFuture = CompletableFuture.runAsync(() -> log.info("run,关注公众号:捡田螺的小男孩"), executor);
        //supplyAsync执行CompletableFuture任务，支持返回值
        CompletableFuture<String> supplyFuture = CompletableFuture.supplyAsync(() -> {
            log.info("supply,关注公众号:捡田螺的小男孩");
            return "捡田螺的小男孩";
        }, executor);
        //runAsync的future没有返回值，输出null
        log.info("runFuture.join: {}", runFuture.join());
        //supplyAsync的future，有返回值
        log.info(supplyFuture.join());
        executor.shutdown(); // 线程池需要关闭
    }
}
