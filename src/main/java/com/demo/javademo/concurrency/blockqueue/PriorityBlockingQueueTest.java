package com.demo.javademo.concurrency.blockqueue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.PriorityBlockingQueue;

public class PriorityBlockingQueueTest {
    public static void main(String[] args) {
        PriorityBlockingQueue<Integer> queue = new PriorityBlockingQueue<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            queue.add(new Integer(random.nextInt(100)));
        }
        System.out.println(Arrays.toString(queue.toArray()));

        int length = queue.size();
        List<Integer> list = new ArrayList<>(length);
        for (int i = 0; i < length; i++) {
            list.add(queue.poll());
        }
        System.out.println(Arrays.toString(list.toArray()));
    }
}
