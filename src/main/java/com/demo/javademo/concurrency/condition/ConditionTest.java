package com.demo.javademo.concurrency.condition;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {
    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<>();
        int maxSize = 5;
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        Thread producer = new Thread(new ConditionProducer(queue, maxSize, lock, condition));
        Thread consumer = new Thread(new ConditionConsumer(queue, lock, condition));
        producer.start();
        consumer.start();
    }
}
