package com.demo.javademo.concurrency.callable;

import java.util.concurrent.*;

public class CallableDemo implements Callable<String> {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("main start");
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<String> future = executorService.submit(new CallableDemo());
        // 结果返回前会阻塞
        String result = future.get();
        System.out.println(result);
        System.out.println("main finish");
    }

    @Override
    public String call() throws Exception {
        //阻塞案例演示
        TimeUnit.SECONDS.sleep(3);
        return "hello world";
    }
}