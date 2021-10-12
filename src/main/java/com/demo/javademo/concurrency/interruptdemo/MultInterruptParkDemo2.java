package com.demo.javademo.concurrency.interruptdemo;

import java.util.concurrent.locks.LockSupport;

/**
 * void Parker::park(bool isAbsolute, jlong time) {
 * // Ideally we'd do something useful while spinning, such
 * // as calling unpackTime().
 * <p>
 * // Optional fast-path check:
 * // Return immediately if a permit is available.
 * // We depend on Atomic::xchg() having full barrier semantics
 * // since we are doing a lock-free update to _counter.
 * if (Atomic::xchg(0, &_counter) > 0) return;
 * 每次调用park都会将_counter直接置为0。
 * <p>
 * void Parker::unpark() {
 * int s, status ;
 * status = pthread_mutex_lock(_mutex);
 * assert (status == 0, "invariant") ;
 * s = _counter;
 * _counter = 1;
 * 每次调用unpark都会将_counter直接置为1.
 * <p>
 * 总结
 * 总的许可数总是保持在1，无论调用多少次unpark，都只会将_counter置为1。
 * 每次park都会将_counter置为0，如果之前为1，则直接返回。后面的park调用就会阻塞。
 */
public class MultInterruptParkDemo2 {
    public static volatile boolean flag = true;

    public static void main(String[] args) {
        ThreadDemo04 t4 = new ThreadDemo04();
        t4.start();
        // 调用unpark都会将_counter直接置为1，即使调用多次，_counter也不会累加
        LockSupport.unpark(t4);
        LockSupport.unpark(t4);
        LockSupport.unpark(t4);
        flag = false;
    }

    public static class ThreadDemo04 extends Thread {
        @Override
        public void run() {
            while (flag) {
            }
            // 调用park都会将_counter直接置为0，如果之前为1，则直接返回，后面的park调用就会阻塞
            LockSupport.park();
            System.out.println("本打印出现在第一个park()之后");
            LockSupport.park();
            System.out.println("本打印出现在第二个park()之后");
        }
    }
}
