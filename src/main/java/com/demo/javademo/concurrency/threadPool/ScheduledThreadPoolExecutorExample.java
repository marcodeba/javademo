package com.demo.javademo.concurrency.threadPool;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author：marco.pan
 * @ClassName：ScheduledThreadPoolExecutorExample
 * @Description：
 * @date: 2021年12月19日 9:33 下午
 */
public class ScheduledThreadPoolExecutorExample {
    public static void main(String[] args) {
        ScheduledThreadPoolExecutor executor = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(5);
        Task task = new Task("任务");
        System.out.println("Created : " + task.getName());
        // 延迟执行任务（只会执行一次，不是周期性的执行任务）
//        executor.schedule(task, 2, TimeUnit.SECONDS);
        //任务+延迟（周期性的执行任务，上次任务执行的时间+延迟时间+执行时间）
//        executor.scheduleWithFixedDelay(task, 0, 2, TimeUnit.SECONDS);
        //任延迟取最大值，稳定定时器，取的是period和任务执行时间的最大值作为执行周期
        executor.scheduleAtFixedRate(task, 0, 5, TimeUnit.SECONDS);
    }
}

class Task implements Runnable {
    private String name;

    public Task(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    //AtomicInteger atomicInteger = new AtomicInteger();

    public void run() {
        //atomicInteger.incrementAndGet();
        System.out.println("Executing : " + name + ", Current Seconds : " + new Date().getSeconds());
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}