package com.demo.javademo.concurrency.thread;

public class JoinDemo {
    private static int i = 10;

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> i = 30);
        t.start();
        // happens-before：t线程中执行的结果对main线程可见
        t.join();
        System.out.println("i = " + i);
    }
}
