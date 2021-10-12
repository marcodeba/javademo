package com.demo.javademo.algorithm;

public class FibonacciArray {
    public static void main(String[] args) {
        System.out.println(getFibonacci1(10));
        System.out.println(getFibonacci2(10));
    }

    //生成斐波那契数
    public static int getFibonacci1(int n) {
        if (n < 1) {
            return 0;
        }
        int[] fibonacciArray = new int[n + 1];
        fibonacciArray[0] = 0;
        fibonacciArray[1] = 1;
        for (int i = 2; i <= n; i++) {
            fibonacciArray[i] = fibonacciArray[i - 1] + fibonacciArray[i - 2];
        }
        return fibonacciArray[n];
    }

    public static int getFibonacci2(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        int s1 = 1, s2 = 1;
        for (int i = 3; i <= n; i++) {
            s2 = s1 + s2;
            s1 = s2 - s1;
        }
        return s2;
    }
}
