package com.demo.javademo.concurrency.future.completableFuture;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @author：marco.pan
 * @ClassName：ThenCombineTest
 * @Description：
 * @date: 2022年01月19日 2:52 下午
 * <p>
 * thenCombine / thenAcceptBoth / runAfterBoth都表示：将两个CompletableFuture组合起来，
 * 只有这两个都正常执行完了，才会执行某个任务。
 */
@Slf4j
public class ThenCombineTest {
    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        log.info("ThenCombineTest start");
        ExecutorService executor = Executors.newFixedThreadPool(10);

        CompletableFuture<String> first = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //log.info("first task finished");
            return "第一个异步任务";
        }, executor);

        //第二个异步任务
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //log.info("second task finished");
            return "第二个异步任务";
        }, executor)
                .thenCombineAsync(first, (s, w) -> {
                    log.info(s);
                    log.info(w);
                    return "两个异步任务的组合";
                }, executor);
        log.info(future.join());
        executor.shutdown();
    }
}
