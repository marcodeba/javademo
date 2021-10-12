package com.demo.javademo.concurrency.condition;

import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class ConditionConsumer implements Runnable {
    private Queue<String> msg;
    private Lock lock;
    private Condition condition;

    public ConditionConsumer(Queue<String> msg, Lock lock, Condition condition) {
        this.msg = msg;
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run() {
        while (true) {
            lock.lock();
            try {
                if (msg.isEmpty()) {
                    System.out.println("消费者队列空了，请等待");
                    // 阻塞线程并释放锁
                    condition.await();
                }
                TimeUnit.SECONDS.sleep(1);
                System.out.println("消费者消费消息:" + msg.remove());
                condition.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
