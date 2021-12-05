package com.demo.javademo.concurrency.threadbase;

import java.util.concurrent.locks.LockSupport;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadStateTest {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> LockSupport.park());
        log.debug("线程状态：{}", thread.getState());
        thread.start();
        log.debug("线程状态：{}", thread.getState());
        Thread.sleep(100);
        log.debug("线程状态：{}", thread.getState());
    }
}
