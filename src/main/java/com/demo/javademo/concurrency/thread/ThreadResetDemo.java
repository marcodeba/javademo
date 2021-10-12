package com.demo.javademo.concurrency.thread;

/**
 * 线程复位：
 * 1. Thread.interrupted
 * 2. InterruptedException
 * 复位的目的：
 * 1. 让外界知道我已经收到中断的信号，但什么时候中断还不知道
 * 2. 让外界知道目前isInterrupted变成false
 */
public class ThreadResetDemo {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            //while (true) {
            while (Thread.currentThread().isInterrupted()) {
                System.out.println("before : " + Thread.currentThread().isInterrupted());
                // 复位到初始状态
                Thread.interrupted();
                System.out.println("after : " + Thread.currentThread().isInterrupted());
                //Thread.currentThread().interrupt();
            }
            //}
        }, "ThreadResetDemo");
        thread.start();
        //TimeUnit.SECONDS.sleep(1);
        thread.interrupt(); //设置interrupt标识为true
    }
}
