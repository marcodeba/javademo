package com.demo.javademo.io.readwrite;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

public class BufferedInputFile {
    private static final String BASIC_PATH = "/Users/marcopan/mysourcecode/javademo/src/main/java/com/demo/javademo";

    public static String read(String filename) throws IOException {
//        long begin = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String s;
        StringBuffer sb = new StringBuffer();
        while ((s = br.readLine()) != null) {
            sb.append(s).append("\n");
        }
        br.close();
//        long last = System.currentTimeMillis() - begin;
//        System.out.println(last);
        return sb.toString();
    }

    public static void read1(String filename) throws IOException {
        StringReader in = new StringReader(
                BufferedInputFile.read(filename));
        int c;
        while ((c = in.read()) != -1) {
            System.out.print((char) c);
        }
    }

    public static void main(String[] args) throws IOException {
        //System.out.print(read(BASIC_PATH + "/io/readwrite/BufferedInputFile.java"));
        System.out.println(read("/Users/marcopan/Desktop/商城测试环境页面URL.txt"));
        //read1("/Users/marcopan/Desktop/商城测试环境页面URL.txt");
    }
}
