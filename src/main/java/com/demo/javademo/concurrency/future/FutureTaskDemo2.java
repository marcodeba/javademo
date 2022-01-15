package com.demo.javademo.concurrency.future;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class FutureTaskDemo2 {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        FutureTask<String> ft1 = new FutureTask<>(() -> {
            System.out.println("T1:查询商品基本信息...");
            TimeUnit.MILLISECONDS.sleep(5000);
            return "商品基本信息查询成功";
        });
        FutureTask<String> ft2 = new FutureTask<>(() -> {
            System.out.println("T2:查询商品价格...");
            TimeUnit.MILLISECONDS.sleep(500);
            return "商品价格查询成功";
        });
        FutureTask<String> ft3 = new FutureTask<>(() -> {
            System.out.println("T3:查询商品库存...");
            TimeUnit.MILLISECONDS.sleep(500);
            return "商品库存查询成功";
        });
        FutureTask<String> ft4 = new FutureTask<>(() -> {
            System.out.println("T4:查询商品图片...");
            TimeUnit.MILLISECONDS.sleep(500);
            return "商品图片查询成功";
        });
        FutureTask<String> ft5 = new FutureTask<>(() -> {
            System.out.println("T5:查询商品销售状态...");
            TimeUnit.MILLISECONDS.sleep(500);
            return "商品销售状态查询成功";
        });

        //构建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        executorService.submit(ft1);
        executorService.submit(ft2);
        executorService.submit(ft3);
        executorService.submit(ft4);
        executorService.submit(ft5);

        long start = System.currentTimeMillis();
        //获取执行结果
        System.out.println(ft1.get(5, TimeUnit.SECONDS));
        System.out.println(ft2.get(5, TimeUnit.SECONDS));
        System.out.println(ft3.get(5, TimeUnit.SECONDS));
        System.out.println(ft4.get(5, TimeUnit.SECONDS));
        System.out.println(ft5.get(5, TimeUnit.SECONDS));

        log.info("cost:" + (System.currentTimeMillis() - start));
        executorService.shutdown();
    }
}
