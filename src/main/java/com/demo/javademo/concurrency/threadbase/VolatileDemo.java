package com.demo.javademo.concurrency.threadbase;

public class VolatileDemo {
    private static volatile boolean flag = true;

    public static void main(String[] args) {
        new Thread(() -> {
            while (true) {
                if (flag) {
                    System.out.println("trun on");
                    flag = false;
                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                if (!flag) {
                    System.out.println("trun off");
                    flag = true;
                }
            }
        }).start();
    }
}