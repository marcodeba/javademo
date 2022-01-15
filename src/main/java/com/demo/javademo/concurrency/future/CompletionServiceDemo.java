package com.demo.javademo.concurrency.future;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CompletionServiceDemo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        //创建线程池
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        //创建CompletionService
        CompletionService<Integer> service = new ExecutorCompletionService<>(executor);
        final long start = System.currentTimeMillis();
        //异步向电商S1询价
        service.submit(() -> {
            TimeUnit.MILLISECONDS.sleep(5000);
            log.debug("电商S1询价信息5000");
            return 5000;
        });
        //异步向电商S2询价
        service.submit(() -> {
            TimeUnit.MILLISECONDS.sleep(8000);
            log.debug("电商S2询价信息8000");
            return 8000;
        });
        //异步向电商S3询价
        service.submit(() -> {
            TimeUnit.MILLISECONDS.sleep(3000);
            log.debug("电商S3询价信息3000");
            return 3000;
        });
        //将询价结果异步保存到数据库
        for (int i = 0; i < 3; i++) {
            //从阻塞队列获取futureTask
            Integer r = service.take().get();
            executor.execute(() -> {
                log.debug("保存询价结果:{}", r);
            });
        }
        log.info("cost " + (System.currentTimeMillis() - start));

        executor.shutdown();
    }
}
