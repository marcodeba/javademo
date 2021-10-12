package com.demo.javademo.concurrency.forkjoin.recursivetask;

import com.demo.javademo.concurrency.forkjoin.util.Utils;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.IntStream;

public class LongSumMain {
    // 获取逻辑处理器数量
    static final int NCPU = Runtime.getRuntime().availableProcessors();
    static long calcSum;

    public static void main(String[] args) {
        int[] array = Utils.buildRandomIntArray(200000000);
        // 1. 单线程计算数组总和
        calcSum = seqSum(array);
        System.out.println("seq sum = " + calcSum);

        // 2. 管理workThread，并分配对应的workQueue
        ForkJoinPool forkJoinPool = new ForkJoinPool(NCPU);
        ForkJoinTask<Long> result = forkJoinPool.submit(new LongSumForkJoinTask(array, 0, array.length));
        try {
            System.out.println("fork join sum = " + result.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            forkJoinPool.shutdown();
        }

        // 3
        long sum = IntStream.of(array).asLongStream().parallel().sum();
        System.out.println(sum);
    }

    static long seqSum(int[] array) {
        long sum = 0;
        for (int i = 0; i < array.length; ++i) {
            sum += array[i];
        }
        return sum;
    }
}