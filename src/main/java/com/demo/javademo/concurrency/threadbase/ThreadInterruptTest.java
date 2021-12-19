package com.demo.javademo.concurrency.threadbase;

/**
 * 中断机制
 */
public class ThreadInterruptTest {
    static int i = 0;

    public static void main(String[] args) {
        System.out.println("begin");
        Thread t1 = new Thread(() -> {
            while (true) {
                System.out.println(i++);

                //Thread.interrupted()  清除中断标志位
                //Thread.currentThread().isInterrupted() 不会清除中断标志位
                if (Thread.currentThread().isInterrupted()) {
//                if (Thread.interrupted()) {
                    System.out.println("=========");
                }
                if (i == 10) {
                    break;
                }
            }
        });

        t1.start();
        //不会停止线程t1,只会设置一个中断标志位 flag=true
        t1.interrupt();
    }
}
