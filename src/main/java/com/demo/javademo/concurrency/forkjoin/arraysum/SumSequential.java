package com.demo.javademo.concurrency.forkjoin.arraysum;

import com.demo.javademo.concurrency.forkjoin.util.Utils;

import java.time.Duration;
import java.time.Instant;

public class SumSequential {
    public static long sum(int[] arr) {
        return SumUtils.sumRange(arr, 0, arr.length);
    }

    public static void main(String[] args) {
        int[] arr = Utils.buildRandomIntArray(20000000);
        System.out.printf("The array length is: %d\n", arr.length);
        Instant now = Instant.now();
        long result = sum(arr);
        System.out.println(Duration.between(now, Instant.now()).toMillis());
        System.out.printf("The result is: %d\n", result);
    }
}
