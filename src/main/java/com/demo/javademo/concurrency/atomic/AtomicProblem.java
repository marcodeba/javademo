package com.demo.javademo.concurrency.atomic;

import com.demo.javademo.concurrency.lock.CASLock;

/**
 * @author：marco.pan
 * @ClassName：AtomicProblem
 * @Description：
 * @date: 2021年11月12日 3:00 下午
 */
public class AtomicProblem extends Thread {
    private static int count = 0;
    private static CASLock casLock = new CASLock();

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
//                for (;;) {
//                    if (casLock.getState() == 0 && casLock.cas()) {
//                        try {
                for (int j = 0; j < 10000; j++) {
                    ++count;
                }
//                        } finally {
//                            casLock.setState(0);
//                        }
//                        break;
//                    }
//                }
            }).start();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("static count: " + count);
    }
}
