package com.demo.javademo.concurrency.threadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author：marco.pan
 * @ClassName：ThreadTest
 * @Description：使用线程的方式去执行程序
 * @date: 2021年12月19日 5:08 下午
 */
public class ThreadTest {
    public static final int THREAD_COUNT = 100000;

    public static void main(String[] args) throws InterruptedException {
        Long start = System.currentTimeMillis();
        final Random random = new Random();
        final List<Integer> list = new ArrayList<>();
        for (int i = 0; i < THREAD_COUNT; i++) {
            Thread thread = new Thread(() -> list.add(random.nextInt()));
            thread.start();
            thread.join();
        }
        System.out.println("时间：" + (System.currentTimeMillis() - start));
        System.out.println("大小：" + list.size());
    }
}

