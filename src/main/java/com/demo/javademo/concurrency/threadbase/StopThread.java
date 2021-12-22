package com.demo.javademo.concurrency.threadbase;

import java.util.concurrent.TimeUnit;

/**
 * @author：marco.pan
 * @ClassName：StopThread
 * @Description：
 * @date: 2021年12月13日 5:15 下午
 */
public class StopThread implements Runnable {
    @Override
    public void run() {
        int count = 0;
        while (!Thread.currentThread().isInterrupted() && count < 1000) {
            System.out.println("count = " + count++);
            /**
             * 注意：使用中断机制时一定要注意是否存在中断标志位被清除的情况
             * 处于休眠中的线程被中断，线程是可以感受到中断信号的，并且会抛出一个 InterruptedException 异常，
             * 同时清除中断信号，将中断标记位设置成 false。这样就会导致 Thread.currentThread().isInterrupted()为false，
             * 程序会在不满足count < 1000这个条件时退出。如果不在catch中重新手动添加中断信号，不做任何处理，就会屏蔽中断请求，
             * 有可能导致线程无法正确停止。
             */
//            try {
//                Thread.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//                //重新设置线程中断状态为true
//                Thread.currentThread().interrupt();
//            }
        }
        System.out.println("线程停止: stop thread");
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new StopThread());
        thread.start();
        TimeUnit.MILLISECONDS.sleep(5);
        thread.interrupt();
    }
}
