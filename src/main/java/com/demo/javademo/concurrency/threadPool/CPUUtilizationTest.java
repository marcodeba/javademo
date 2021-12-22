package com.demo.javademo.concurrency.threadPool;

/**
 * @author：marco.pan
 * @ClassName：CPUUtilizationTest
 * @Description：
 * @date: 2021年12月21日 4:32 下午
 */
public class CPUUtilizationTest {
//    public static void main(String[] args) {
//        for (int j = 0; j < 8; j++) {
//            new Thread(() -> {
//                while (true) {
//                }
//            }).start();
//        }
//    }
    public static void main(String[] args) {
        for (int n = 0; n < 8; n++) {
            new Thread(() -> {
                while (true) {
                    //每次空循环1亿次后，sleep 50ms，模拟 I/O等待、切换
                    for (int i = 0; i < 100_000_000l; i++) {
                    }
                    try {
                        Thread.sleep(50);
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
