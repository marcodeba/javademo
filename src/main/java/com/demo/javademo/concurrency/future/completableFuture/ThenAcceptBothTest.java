package com.demo.javademo.concurrency.future.completableFuture;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

/**
 * @author：marco.pan
 * @ClassName：ThenAcceptBothTest
 * @Description：
 * @date: 2022年01月20日 1:06 下午
 * <p>
 * thenAcceptBoth 函数的作用是，当两个 CompletionStage 都正常完成计算的时候，就会执行提供的action消费两个异步的结果。
 * 两个任务并行执行，总耗时=Max(a,b)
 */
@Slf4j
public class ThenAcceptBothTest {
    public static void main(String[] args) {
        log.info("ThenAcceptBothTest start");
        CompletableFuture<Integer> futrue1 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                int number = 2;
                try {
                    TimeUnit.SECONDS.sleep(number);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("第一阶段:" + number);
                return number;
            }
        });

        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                int number = 1;
                try {
                    TimeUnit.SECONDS.sleep(number);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("第二阶段:" + number);
                return number;
            }
        });

        futrue1.thenAcceptBoth(future2, new BiConsumer<Integer, Integer>() {
            @Override
            public void accept(Integer x, Integer y) {
                log.info("最终结果:" + (x + y));
            }
        }).join();
    }
}
