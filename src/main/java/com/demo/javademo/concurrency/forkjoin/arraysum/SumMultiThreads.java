package com.demo.javademo.concurrency.forkjoin.arraysum;

import com.demo.javademo.concurrency.forkjoin.util.Utils;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SumMultiThreads {
    public final static int NUM = 10000000;

    public static long sum(int[] arr, ExecutorService executor) throws Exception {
        long result = 0;
        int numThreads = arr.length / NUM > 0 ? arr.length / NUM : 1;

        int num = arr.length / numThreads;
        SumTask[] tasks = new SumTask[numThreads];
        Future<Long>[] sums = new Future[numThreads];
        for (int i = 0; i < numThreads; i++) {
            tasks[i] = new SumTask(arr, (i * NUM), ((i + 1) * NUM));
            sums[i] = executor.submit(tasks[i]);
        }

        for (int i = 0; i < numThreads; i++) {
            result += sums[i].get();
        }

        return result;
    }

    public static void main(String[] args) throws Exception {
        int[] arr = Utils.buildRandomIntArray(20000000);
        int numThreads = arr.length / NUM > 0 ? arr.length / NUM : 1;

        System.out.printf("The array length is: %d\n", arr.length);
        // Executors尽量不要使用
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);

        Instant now = Instant.now();
        long result = sum(arr, executor);
        System.out.println(Duration.between(now, Instant.now()).toMillis());
        System.out.printf("The result is: %d\n", result);
        executor.shutdown();
    }
}
