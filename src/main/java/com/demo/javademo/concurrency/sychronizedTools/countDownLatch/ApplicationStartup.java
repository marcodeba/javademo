package com.demo.javademo.concurrency.sychronizedTools.countDownLatch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class ApplicationStartup {
    private static List<BaseHealthChecker> services;
    private static CountDownLatch countDownLatch;

    static {
        countDownLatch = new CountDownLatch(2);
        services = new ArrayList<>();
        services.add(new CacheHealthChecker(countDownLatch));
        services.add(new DatabaseHealthChecker(countDownLatch));
    }

    private ApplicationStartup() {}

    public static boolean checkExternalServices() throws Exception {
        for (BaseHealthChecker baseChecker : services) {
            new Thread(baseChecker).start();
        }
        countDownLatch.await();
        return true;
    }
}
