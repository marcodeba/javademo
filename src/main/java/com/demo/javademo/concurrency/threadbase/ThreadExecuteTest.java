package com.demo.javademo.concurrency.threadbase;

import java.util.concurrent.ExecutionException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadExecuteTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Runnable runnable = () -> log.debug("通过Runnable方式执行任务");

        // 操作系统创建线程
        // java Thread --> jvm JavaThread---->os Thread

        // new Thread(runnable).start();
        // new Object()--->jvm JavaThread
        new Thread(runnable).start();
        // 这是一个真正的线程吗？  普通对象的方法调用
        new Thread(runnable).run();
        runnable.run();
    }
}
