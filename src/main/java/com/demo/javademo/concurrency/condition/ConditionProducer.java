package com.demo.javademo.concurrency.condition;

import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class ConditionProducer implements Runnable {
    private Queue<String> msg;
    private int maxSize;
    private Lock lock;
    private Condition condition;

    public ConditionProducer(Queue<String> msg, int maxSize, Lock lock, Condition condition) {
        this.msg = msg;
        this.maxSize = maxSize;
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run() {
        int i = 0;
        while (true) {
            i++;
            lock.lock();
            try {
                // 队列满了，生产者阻塞
                if (msg.size() == maxSize) {
                    System.out.println("生产者队列满了，请等待");
                    // 阻塞线程并释放锁
                    condition.await();
                }
                TimeUnit.SECONDS.sleep(1);
                System.out.println("生产者生产消息:" + i);
                msg.add("消息：" + i);
                // 将线程从等待队列挪到AQS队列
                condition.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
