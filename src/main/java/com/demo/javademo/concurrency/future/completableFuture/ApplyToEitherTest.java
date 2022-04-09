package com.demo.javademo.concurrency.future.completableFuture;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author：marco.pan
 * @ClassName：AcceptEitherTest
 * @Description：
 * @date: 2022年01月19日 2:59 下午
 * <p>
 * 将两个CompletableFuture组合起来，只要其中一个执行完了，就会执行这个任务。
 * 总耗时=Min(a,b)
 */
@Slf4j
public class ApplyToEitherTest {
    public static void main(String[] args) {
        log.info("AcceptEitherTest start");
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            int number = new Random().nextInt(10);
            log.info("第一阶段start:" + number);
            try {
                TimeUnit.SECONDS.sleep(number);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("第一阶段end:" + number);
            return number;
        });
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            int number = new Random().nextInt(10);
            log.info("第二阶段start:" + number);
            try {
                TimeUnit.SECONDS.sleep(number);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("第二阶段end:" + number);
            return number;
        });
        future1.applyToEither(future2, number -> {
            log.info("最快结果:" + number);
            return number;
        }).join();
    }
}
