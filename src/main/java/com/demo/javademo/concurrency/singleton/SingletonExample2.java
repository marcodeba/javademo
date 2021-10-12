package com.demo.javademo.concurrency.singleton;

import com.demo.javademo.annoations.ThreadSafe;

/**
 * 饿汉模式
 * 单例实例在类装载时进行创建
 */
@ThreadSafe
public class SingletonExample2 {
    // 单例对象
    private static SingletonExample2 instance = new SingletonExample2();

    // 私有构造函数
    private SingletonExample2() {

    }

    // 静态的工厂方法
    public static SingletonExample2 getInstance() {
        return instance;
    }
}
