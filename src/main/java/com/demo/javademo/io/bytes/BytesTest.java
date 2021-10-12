package com.demo.javademo.io.bytes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BytesTest {
    public static void main(String[] args) throws IOException {
        BytesTest test = new BytesTest();
        test.test04();
        test.test05();
    }

    public void test04() throws IOException {
        byte[] bytes = {12, 21, 34, 11, 21};
        FileOutputStream fileOutputStream = new FileOutputStream(new File("").getAbsolutePath() + "/io/test.txt");
        // 写入二进制文件，直接打开会出现乱码
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }

    public void test05() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File("").getAbsolutePath() + "/io/test.txt");
        int c;
        // 读取写入的二进制文件，输出字节数组
        while ((c = fileInputStream.read()) != -1) {
            System.out.print(c);
        }
    }
}
