package com.demo.javademo.concurrency.communication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class Consumer implements Runnable {
    Logger logger = LoggerFactory.getLogger(Consumer.class);
    private Queue<String> msg;

    public Consumer(Queue<String> msg) {
        this.msg = msg;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (msg) {
                //如果消息队列为空了，阻塞等待
                while (msg.isEmpty()) {
                    try {
                        // wait会立刻释放 synchronized 的锁，以便其他线程可以执行 notify
                        msg.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                    logger.info("消费者消费消息：" + msg.remove());
                    // notify 必须要等到 notify 所在线程执行完 synchronized 块中的所有代码才会释放这把锁
                    msg.notify();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
