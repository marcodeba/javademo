package com.demo.javademo.io.readwrite;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class CopyFileTest {
    public static final String INPUT_PATH = "SpringBoot.mp4";
    public static final String OUTPUT_PATH = "new SpringBoot.mp4";
    //ClassPathResource resource = new ClassPathResource(INPUT_PATH);

    public static void main(String[] args) throws IOException {
        CopyFileTest test = new CopyFileTest();
        //test.test10();//75
        test.test12();//67
        //test.test13();//221
        //test.test14();//258
        //test.test11();//1623
        //test.test15();//146159
    }

    public void test10() throws IOException {
        FileInputStream fis = new FileInputStream(INPUT_PATH);
        FileOutputStream fos = new FileOutputStream(OUTPUT_PATH);
        FileChannel inChannel = fis.getChannel();
        FileChannel outChannel = fos.getChannel();

        ByteBuffer buffer = ByteBuffer.allocateDirect(10240);//.allocate(10240);
        //int bytesRead = inChannel.read(buffer);
        long begin = System.currentTimeMillis();
        while (-1 != inChannel.read(buffer)) {
            buffer.flip();
            while (buffer.hasRemaining()) {
                outChannel.write(buffer);
            }
            buffer.clear();
            //bytesRead = inChannel.read(buffer);
        }
        System.out.println("test1复制文件所需的时间：" + (System.currentTimeMillis() - begin));
        inChannel.close();
        outChannel.close();
        fis.close();
        fos.close();
    }

    public void test12() throws IOException {
        // 输入和输出都使用缓冲流
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(INPUT_PATH));
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(OUTPUT_PATH));
        int len = 0;
        byte[] bytes = new byte[10240];
        long begin = System.currentTimeMillis();
        while ((len = in.read(bytes)) != -1) {
            out.write(bytes, 0, len);
        }
        System.out.println("test2复制文件所需的时间：" + (System.currentTimeMillis() - begin));
        in.close();
        out.close();
    }

    public void test11() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(INPUT_PATH));
        BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT_PATH));
        String s;
        long begin = System.currentTimeMillis();
        while ((s = br.readLine()) != null) {
            bw.write(s);
            bw.write("\n");
        }
        System.out.println("test1复制文件所需的时间：" + (System.currentTimeMillis() - begin));
        br.close();
        bw.close();
    }

    public void test13() throws IOException {
        // 只有输入使用缓冲流
        BufferedInputStream inBuffer = new BufferedInputStream(new FileInputStream(INPUT_PATH));
        OutputStream out = new FileOutputStream(OUTPUT_PATH);
        int len = 0;
        byte[] bs = new byte[1024];
        long begin = System.currentTimeMillis();
        while ((len = inBuffer.read(bs)) != -1) {
            out.write(bs, 0, len);
        }
        System.out.println("复制文件所需时间：" + (System.currentTimeMillis() - begin));
        inBuffer.close();
        out.close();
    }

    public void test14() throws IOException {
        // 输入和输出都不使用缓冲流
        InputStream in = new FileInputStream(INPUT_PATH);
        OutputStream out = new FileOutputStream(OUTPUT_PATH);
        int len = 0;
        byte[] bs = new byte[1024];
        long begin = System.currentTimeMillis();
        while ((len = in.read(bs)) != -1) {
            out.write(bs, 0, len);
        }
        System.out.println("复制文件所需时间：" + (System.currentTimeMillis() - begin));
        in.close();
        out.close();
    }

    public void test15() throws IOException {
        // 不使用缓冲
        InputStream in = new FileInputStream(INPUT_PATH);
        OutputStream out = new FileOutputStream(OUTPUT_PATH);
        int len = 0;
        long begin = System.currentTimeMillis();
        while ((len = in.read()) != -1) {
            out.write(len);
        }
        System.out.println("复制文件所需时间：" + (System.currentTimeMillis() - begin));
        in.close();
        out.close();
    }
}
