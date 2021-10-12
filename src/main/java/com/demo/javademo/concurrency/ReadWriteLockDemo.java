package com.demo.javademo.concurrency;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读读不互斥
 * 读写互斥
 * 写写互斥
 */
public class ReadWriteLockDemo {
    static Map<String, Object> cacheMap = new HashMap<>();
    static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    static Lock readLock = readWriteLock.readLock();
    static Lock writeLock = readWriteLock.writeLock();

    public static final Object read(String key) {
        try {
            readLock.lock();
            return cacheMap.get(key);
        } finally {
            readLock.unlock();
        }
    }

    public static final Object write(String key, Object object) {
        try {
            writeLock.lock();
            return cacheMap.put(key, object);
        } finally {
            writeLock.unlock();
        }
    }

    public static final void clear() {
        try {
            writeLock.lock();
            cacheMap.clear();
        } finally {
            writeLock.unlock();
        }
    }
}
