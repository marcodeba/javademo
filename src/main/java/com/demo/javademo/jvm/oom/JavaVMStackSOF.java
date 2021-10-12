package com.demo.javademo.jvm.oom;

// VM Args: -Xss160k
public class JavaVMStackSOF {
    private int stackLength = 1;

    public static void main(String[] args) {
        JavaVMStackSOF oom = new JavaVMStackSOF();
        oom.stackLeak();
    }

    public void stackLeak() {
        System.out.println(stackLength++);
        stackLeak();
    }
}
