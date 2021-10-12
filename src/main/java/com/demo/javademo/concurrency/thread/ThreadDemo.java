package com.demo.javademo.concurrency.thread;

public class ThreadDemo extends Thread {
    public static void main(String[] args) {
        ThreadDemo demo = new ThreadDemo();
        demo.start();
        System.out.println(Thread.currentThread().getName() + " finish main");
    }

    @Override
    public void run() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + " finish run");
        }
    }
}
