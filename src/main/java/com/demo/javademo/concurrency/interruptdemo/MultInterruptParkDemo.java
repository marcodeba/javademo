package com.demo.javademo.concurrency.interruptdemo;

import java.util.concurrent.locks.LockSupport;

/*
LockSupport及HotSpot层Parker::park/unpark分析中park源码：
    void Parker::park(bool isAbsolute, jlong time) {
        // Ideally we'd do something useful while spinning, such
        // as calling unpackTime().

        // Optional fast-path check:
        // Return immediately if a permit is available.
        // We depend on Atomic::xchg() having full barrier semantics
        // since we are doing a lock-free update to _counter.
        if (Atomic::xchg(0, &_counter) > 0) return;

        Thread* thread = Thread::current();
        assert(thread->is_Java_thread(), "Must be JavaThread");
        JavaThread *jt = (JavaThread *)thread;

        // Optional optimization -- avoid state transitions if there's an interrupt pending.
        // Check interrupt before trying to wait
        if (Thread::is_interrupted(thread, false)) {
            return;
        }
        ......
    }
 */
public class MultInterruptParkDemo {
    public static volatile boolean flag = true;

    public static void main(String[] args) {
        ThreadDemo04 t4 = new ThreadDemo04();
        t4.start();
        // 设置线程的中断标志位为true(中断标记位默认是false)
        t4.interrupt();
        flag = false;
    }

    public static class ThreadDemo04 extends Thread {
        @Override
        public void run() {
            while (flag) {
            }
            // LockSupport.park()会检查线程是否设置了中断标志位，如果设置了，则返回（这里并不会清除中断标志位）
            // 注意：如果没有设置中断标记位，则看_count，不是没有设中断标记位就直接中断的!!!!!!!!!
            LockSupport.park();
            System.out.println("本打印出现在第1个park()之后");
            LockSupport.park();
            System.out.println("本打印出现在第2个park()之后");
            LockSupport.park();
            System.out.println("本打印出现在第3个park()之后");
        }
    }
}
