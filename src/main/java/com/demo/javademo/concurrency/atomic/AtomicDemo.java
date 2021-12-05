package com.demo.javademo.concurrency.atomic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AtomicDemo {
    private static int count = 0;
    private static Lock lock = new ReentrantLock();

    public static void inc() {
        try {
            lock.lock();
            TimeUnit.MILLISECONDS.sleep(1);
            count++;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            new Thread(AtomicDemo::inc).start();
        }
        TimeUnit.SECONDS.sleep(2);
        System.out.println("y运行结果：" + count);
    }
}
