package com.demo.javademo.io.readwrite;

import java.io.*;

public class BasicFileOutput {
    static String file = "BasicFileOutput.out";

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(
                new StringReader(
                        BufferedInputFile.read("/Users/marcopan/mysourcecode/javademo/src/main/java/com/demo/javademo/io/readwrite/BasicFileOutput.java")));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file)));
        String s;
        while ((s = in.readLine()) != null) {
            out.println(s);
        }

        out.close();
        in.close();
        // Show the stored file:
        System.out.println(BufferedInputFile.read(file));
    }
}