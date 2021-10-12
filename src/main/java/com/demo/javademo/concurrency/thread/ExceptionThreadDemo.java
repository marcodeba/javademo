package com.demo.javademo.concurrency.thread;

import java.util.concurrent.TimeUnit;

// 异常复位
public class ExceptionThreadDemo {
    private static int i;

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    // 中断阻塞线程，会先复位interrupt，然后抛InterruptedException
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println("test");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Num:" + i);
        }, "interruptDemo");
        thread.start();
        TimeUnit.SECONDS.sleep(1);
        thread.interrupt();
        System.out.println(thread.isInterrupted());
    }
}






