package com.demo.javademo.concurrency.forkjoin.recursivetask;

import com.demo.javademo.concurrency.forkjoin.util.Utils;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.IntStream;

@Slf4j
public class LongSumMain {
    // 获取逻辑处理器数量
    static final int NCPU = Runtime.getRuntime().availableProcessors();
    static long calcSum;

    public static void main(String[] args) {
        int[] array = Utils.buildRandomIntArray(200000000);
        Instant start = Instant.now();

        // 1. 单线程计算数组总和
        calcSum = seqSum(array);
        log.info("seq sum = {}, cost : {}", calcSum, Duration.between(start, Instant.now()).toMillis());

        // 2. 管理workThread，并分配对应的workQueue
        start = Instant.now();
        ForkJoinPool forkJoinPool = new ForkJoinPool(NCPU);
        ForkJoinTask<Long> result = forkJoinPool.submit(new LongSumForkJoinTask(array, 0, array.length));
        try {
            log.info("fork join sum = {}, cost : {}", result.get(), Duration.between(start, Instant.now()).toMillis());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            forkJoinPool.shutdown();
        }

        // 3、Java并行流
        start = Instant.now();
        long sum = IntStream.of(array).asLongStream().parallel().sum();
        log.info("parallel sum = {}, cost : {}", sum, Duration.between(start, Instant.now()).toMillis());
    }

    static long seqSum(int[] array) {
        long sum = 0;
        for (int i = 0; i < array.length; ++i) {
            sum += array[i];
        }
        return sum;
    }
}