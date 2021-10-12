package com.demo.javademo.concurrency.sychronizedTools.countDownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CacheHealthChecker extends BaseHealthChecker {
    private CountDownLatch countDownLatch;

    public CacheHealthChecker(CountDownLatch countDownLatch) {
        super("CacheHealthChecker");
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void verifyService() throws Exception {
        System.out.println("checking " + this.getServiceName());
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
            throw e;
        }
        countDownLatch.countDown();
        System.out.println(this.getServiceName() + "状态正常");
    }
}
