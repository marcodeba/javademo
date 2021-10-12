package com.demo.javademo.concurrency.thread;

import java.util.concurrent.TimeUnit;

public class ThreadStatusDemo {

    public static void main(String[] args) {
        // 该线程不断的睡眠
        //  waiting on condition, TIMED_WAITING (sleeping)
        new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "time waiting").start();// 阻塞状态

        // 该线程在ThreadStatusDemo.class实例上等待
        // in Object.wait(), WAITING (on object monitor)
        new Thread(() -> {
            while (true) {
                synchronized (ThreadStatusDemo.class) {
                    try {
                        ThreadStatusDemo.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "waiting").start();// 阻塞状态

        // 两个Blocked线程，一个获取锁，另一个被阻塞
        new Thread(new BlockDemo(), "BlockDemo-0").start();// waiting on condition, TIMED_WAITING (sleeping)
        new Thread(new BlockDemo(), "BlockDemo-1").start();// waiting for monitor entry, BLOCKED (on object monitor)
    }

    static class BlockDemo extends Thread {
        public void run() {
            synchronized (BlockDemo.class) {
                while (true) {
                    try {
                        TimeUnit.SECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
