package com.demo.javademo.concurrency.threadPoolMonitor;

import java.util.concurrent.ExecutorService;

public class Test implements Runnable {
    private static ExecutorService es = Demo1.newCachedThreadPool();

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            es.execute(new Test());
        }
        es.shutdown();
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}