package com.demo.javademo.concurrency.threadlocal;

public class ThreadLocalDemo {
    static ThreadLocal<Integer> local = ThreadLocal.withInitial(() -> 0);

    public static void main(String[] args) {
        Thread[] threads = new Thread[5];
        for (int i = 0; i < 5; i++) {
            threads[i] = new Thread(() -> {
                int num = local.get();
                local.set(num += 5);
                System.out.println(Thread.currentThread().getName() + "-" + num);
            });
        }
        for (int i = 0; i < 5; i++) {
            threads[i].start();
        }
    }
}
