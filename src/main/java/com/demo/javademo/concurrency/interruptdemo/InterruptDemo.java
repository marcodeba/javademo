package com.demo.javademo.concurrency.interruptdemo;

import java.util.concurrent.TimeUnit;

public class InterruptDemo {
    private static int i;

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            // 表示一个中断标记
            while (!Thread.currentThread().isInterrupted()) {
                i++;
            }
            System.out.println(i);
        }, "interrupt Demo");
        thread.start();

        TimeUnit.SECONDS.sleep(1);

        // 在main线程中，将 interrupt 标识设置为true
        thread.interrupt();
        System.out.println(thread.isInterrupted());
    }

//    public static void main(String[] args) throws InterruptedException {
//        Thread thread = new Thread(() -> {
//            while (true) {
//                if (Thread.currentThread().isInterrupted()) {
//                    System.out.println("before:" + Thread.currentThread().isInterrupted());
//                    Thread.interrupted(); //对线程进行复位，由 true 变成 false
//                    System.out.println("after:" + Thread.currentThread().isInterrupted());
//                }
//            }
//        }, "interruptDemo");
//        thread.start();
//        TimeUnit.SECONDS.sleep(1);
//        thread.interrupt();
//    }
}
