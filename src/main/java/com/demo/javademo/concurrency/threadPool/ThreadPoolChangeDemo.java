package com.demo.javademo.concurrency.threadPool;

import org.joda.time.DateTime;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolChangeDemo {
    public static void main(String[] args) throws InterruptedException {
        dynamicModifyExecutor();
    }

    /**
     * 自定义线程池
     * 核心线程数是2
     * 非核心线程数是3
     * 队列长度是10
     * 非核心线程空闲60秒后回收
     */
    private static ThreadPoolExecutor buildThreadPoolExecutor() {
        return new ThreadPoolExecutor(2, 5, 60, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(10),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }

    /**
     * 提交任务给线程池，修改线程池参数
     */
    private static void dynamicModifyExecutor() throws InterruptedException {
        ThreadPoolExecutor executor = buildThreadPoolExecutor();
        // 给线程池塞15个耗时10秒的任务，直接让它5个最大线程数都在工作，队列长度10个都塞满。
        for (int i = 0; i < 15; i++) {
            executor.submit(() -> {
                threadPoolStatus(executor, "创建任务");
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                System.out.println(Thread.currentThread().getName() + "当前线程完成任务");
            });
        }
        // 5个最大线程都在工作，需要3批处理完15个耗时10秒的任务，一共需要30秒完成全部任务。
        threadPoolStatus(executor, "改变之前");
        TimeUnit.SECONDS.sleep(1);

        // 把核心线程数和最大线程数都设置成10个，这样10个任务直接被10个最大线程数处理，10秒就会被处理完成，剩下的5个任务10秒后执行完成
        executor.setCorePoolSize(10);
        executor.setMaximumPoolSize(10);
        executor.prestartAllCoreThreads();
        threadPoolStatus(executor, "改变之后");
        Thread.currentThread().join();
    }

    /**
     * 打印线程池状态
     */
    private static void threadPoolStatus(ThreadPoolExecutor executor, String name) {
        LinkedBlockingQueue queue = (LinkedBlockingQueue) executor.getQueue();
        System.out.println(new DateTime() +
                " 线程名称" + Thread.currentThread().getName() + "-" + name + "-:" +
                " 核心线程数：" + executor.getCorePoolSize() +
                " 活动线程数：" + executor.getActiveCount() +
                " 最大线程数：" + executor.getMaximumPoolSize() +
                " 线程池活跃度：" + divide(executor.getActiveCount(), executor.getMaximumPoolSize()) +
                " 任务完成数：" + executor.getCompletedTaskCount() +
                " 队列大小：" + (queue.size() + queue.remainingCapacity()) +
                " 当前排队线程数：" + queue.size() +
                " 队列剩余大小：" + queue.remainingCapacity() +
                " 队列使用度：" + divide(queue.size(), queue.size() + queue.remainingCapacity()));
    }

    /**
     * 保留两位小数
     */
    private static String divide(int num1, int num2) {
        return String.format("%1.2f%%", Double.parseDouble(num1 + "") / Double.parseDouble(num2 + "") * 100);
    }
}

