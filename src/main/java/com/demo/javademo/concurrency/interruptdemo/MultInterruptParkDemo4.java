package com.demo.javademo.concurrency.interruptdemo;

import java.util.concurrent.locks.LockSupport;

public class MultInterruptParkDemo4 {
    public static volatile boolean flag = true;

    public static void main(String[] args) {
        ThreadDemo04 t4 = new ThreadDemo04();
        t4.start();
        // 设置线程的中断标志位为true(中断标记位默认是false)
        t4.interrupt();
        flag = false;
    }

    public static class ThreadDemo04 extends Thread {
        @Override
        public void run() {
            while (flag) {
            }
            try {
                // sleep如果检测到中断会直接清除中断标志，并抛出异常。
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 此处中断标记位是false（InterruptedException中进行了复位）
            // 由于interrupt()方法会调用unpark方法，将_count设置成1，所以能够跳过第一个park
            LockSupport.park();
            System.out.println("本打印出现在第一个sleep()之后");

            System.out.println(Thread.interrupted());
            LockSupport.park();
            System.out.println("本打印出现在第二个park()之后");
        }
    }
}
