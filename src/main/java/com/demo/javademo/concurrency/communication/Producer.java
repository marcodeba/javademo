package com.demo.javademo.concurrency.communication;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Queue;
import java.util.concurrent.TimeUnit;

@Slf4j
public class Producer implements Runnable {
    Logger logger = LoggerFactory.getLogger(Producer.class);
    private Queue<String> msg;
    private int maxSize;

    public Producer(Queue<String> msg, int maxSize) {
        this.msg = msg;
        this.maxSize = maxSize;
    }

    @Override
    public void run() {
        int messageId = 0;
        while (true) {
            messageId++;
            synchronized (msg) {
                // 队列满了，阻塞等待
                while (msg.size() == maxSize) {
                    try {
                        // wait会立刻释放 synchronized 的锁，以便其他线程可以执行 notify
                        msg.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                    logger.info("生产者生产消息:" + messageId);
                    msg.add("消息" + messageId);
                    // notify 必须要等到 notify 所在线程执行完 synchronized 块中的所有代码才会释放这把锁
                    msg.notify();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
