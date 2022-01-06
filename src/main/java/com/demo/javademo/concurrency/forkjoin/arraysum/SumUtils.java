package com.demo.javademo.concurrency.forkjoin.arraysum;

public class SumUtils {

    public static long sumRange(int[] arr, int low, int high) {
        long result = 0;
        for (int j = low; j < high; j++) {
            result += arr[j];
        }
        return result;
    }
}
