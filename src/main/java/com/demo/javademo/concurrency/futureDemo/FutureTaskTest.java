package com.demo.javademo.concurrency.futureDemo;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTaskTest implements Callable<String> {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask futureTask = new FutureTask(new FutureTaskTest());
        new Thread(futureTask).start();
        System.out.println(futureTask.get());
    }

    @Override
    public String call() {
        return "call method is called";
    }
}
