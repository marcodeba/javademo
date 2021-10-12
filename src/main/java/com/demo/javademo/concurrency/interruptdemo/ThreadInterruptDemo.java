package com.demo.javademo.concurrency.interruptdemo;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
public class ThreadInterruptDemo {
    private static Logger logger = LoggerFactory.getLogger(ThreadInterruptDemo.class);
    private volatile static boolean on = false;

    public static void main(String[] args) throws InterruptedException {
        Thread testThread = new Thread(() -> {
            while (!on) {
                try {
                    // 中断一个处于阻塞状态的线程会抛出InterruptedException
                    Thread.sleep(10000000);
                } catch (InterruptedException e) {
                    // 这里会触发线程的复位
                    logger.error(e.toString());
                }
            }
        });
        //Thread testThread = new Thread(new ThreadInterruptDemo(), "InterruptionInJava");
        //start thread
        testThread.start();
        Thread.sleep(1000);

        ThreadInterruptDemo.on = true;
        testThread.interrupt();
        System.out.println("main end");
    }
}
