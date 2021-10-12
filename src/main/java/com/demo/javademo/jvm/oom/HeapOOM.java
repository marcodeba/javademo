package com.demo.javademo.jvm.oom;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

// VM args: -Xms20M -Xmx20M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+HeapDumpOnOutOfMemoryError
public class HeapOOM {
    public static void main(String[] args) throws InterruptedException {
        List<OOMObject> list = new ArrayList<>();
        // 每50毫秒向堆中填充大小为64KB的对象，一共填充1000次，使用JConsole监视内存
        for (int i = 0; i < 1000; i++) {
            TimeUnit.MILLISECONDS.sleep(50L);
            list.add(new OOMObject());
        }
        System.gc();
    }

    static class OOMObject {
        public byte[] placeHolder = new byte[64 * 1024];
    }
}
