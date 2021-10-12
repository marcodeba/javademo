package com.demo.javademo.jvm.tools;

import java.io.IOException;

/**
 * universe
 * Heap Parameters:
 * Gen 0:   eden [0x000000011e600000,0x000000011ecde4f0,0x0000000120820000) space capacity = 35782656, 20.127158811240843 used
 *   from [0x0000000120820000,0x0000000120820000,0x0000000120c60000) space capacity = 4456448, 0.0 used
 *   to   [0x0000000120c60000,0x0000000120c60000,0x00000001210a0000) space capacity = 4456448, 0.0 usedInvocations: 0
 *
 * Gen 1:   old  [0x00000001490a0000,0x00000001490a0000,0x000000014e600000) space capacity = 89522176, 0.0 usedInvocations: 0
 *
 * hsdb> scanoops 0x000000011e600000 0x0000000120820000 com.demo.javademo.jvm.tools.HSDBTest$ObjectHolder
 * 0x000000011ea205e0 com/demo/javademo/jvm/tools/HSDBTest$ObjectHolder
 * 0x000000011ea20608 com/demo/javademo/jvm/tools/HSDBTest$ObjectHolder
 * 0x000000011ea20618 com/demo/javademo/jvm/tools/HSDBTest$ObjectHolder
 * hsdb> revptrs 0x000000011ea205e0
 */
public class HSDBTest {
    static class Test {
        static ObjectHolder staticObj = new ObjectHolder();
        ObjectHolder instanceObj = new ObjectHolder();

        void foo() throws IOException {
            ObjectHolder localObj = new ObjectHolder();
            System.in.read();
        }
    }

    private static class ObjectHolder {}

    public static void main(String[] args) throws IOException {
        Test test = new Test();
        test.foo();
    }
}