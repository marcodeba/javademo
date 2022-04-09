package com.demo.javademo.concurrency.future.completableFuture;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author：marco.pan
 * @ClassName：FutureThenApplyTest
 * @Description：
 * @date: 2022年01月19日 2:19 下午
 * <p>
 * CompletableFuture的thenApply方法表示，第一个任务执行完成后，
 * 执行第二个回调方法任务，会将该任务的执行结果作为入参，传递到回调方法中，
 * 并且回调方法是有返回值的。
 * 两个任务串行行执行，总耗时=total(a,b)，a执行的结果对b可见
 */
@Slf4j
public class FutureThenApplyTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        log.info("FutureThenApplyTest start");
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

        CompletableFuture<String> thenApplyFuture = orgFuture.thenApply((a) -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if ("捡田螺的小男孩".equals(a)) {
                return "关注了" + a;
            }
            return "先考虑考虑";
        });

        log.info(thenApplyFuture.get());
    }
}
