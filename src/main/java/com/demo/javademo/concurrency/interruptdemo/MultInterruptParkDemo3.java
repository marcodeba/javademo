package com.demo.javademo.concurrency.interruptdemo;

import java.util.concurrent.locks.LockSupport;

/**
 * public static boolean interrupted() {
 * return currentThread().isInterrupted(true);
 * }
 * <p>
 * bool os::is_interrupted(Thread* thread, bool clear_interrupted) {
 * assert(Thread::current() == thread || Threads_lock->owned_by_self(),
 * "possibility of dangling Thread pointer");
 * <p>
 * OSThread* osthread = thread->osthread();
 * <p>
 * bool interrupted = osthread->interrupted();
 * <p>
 * if (interrupted && clear_interrupted) {
 * osthread->set_interrupted(false);
 * // consider thread->_SleepEvent->reset() ... optional optimization
 * }
 * <p>
 * return interrupted;
 * }
 */
public class MultInterruptParkDemo3 {
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
            // 设置了中断标记位，所以park直接返回
            LockSupport.park();
            System.out.println("本打印出现在第一个park()之后");

            // 清除中断标记位，中断标记位变成false
            Thread.interrupted();
            LockSupport.park();
            System.out.println("本打印出现在第二个park()之后");
        }
    }
}
