package com.demo.javademo;

import io.netty.util.NettyRuntime;
import io.netty.util.internal.SystemPropertyUtil;

class Base {
    private int i = 2;

    public Base() {
        this.display();
    }

    public void display() {
        System.out.println(i);
    }
}

class Derived extends Base {
    private int i = 22;

    public Derived() {
        i = 222;
    }

    public Derived(int i) {
        this.i = i;
    }

    @Override
    public void display() {
        System.out.println(i);
    }
}

public class Test {
    public static void main(String[] args) {
        //new Derived();
        //while (true) ;

        System.out.println(Math.max(1, SystemPropertyUtil.getInt(
                "io.netty.eventLoopThreads", NettyRuntime.availableProcessors() * 2)));
    }
}
