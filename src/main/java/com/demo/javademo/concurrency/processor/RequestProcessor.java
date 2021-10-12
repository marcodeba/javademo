package com.demo.javademo.concurrency.processor;

public interface RequestProcessor {
    void processRequest(Request request);

    void shutdown();
}
