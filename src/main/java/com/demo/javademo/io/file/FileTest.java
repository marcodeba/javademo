package com.demo.javademo.io.file;

import java.io.*;

public class FileTest {
    public static void main(String[] args) throws IOException {
        FileTest test = new FileTest();
        test.test06();
        test.test07();
        test.test08();
        System.out.println("----------------------------------");
        test.test09();
    }

    public void test06() throws IOException {
        FileWriter fileWriter = new FileWriter(new File("").getAbsolutePath() + "/io/test.txt");
        fileWriter.write("Hello，world！\n欢迎来到 java 世界\n");
        fileWriter.write("不会覆盖文件原本的内容\n");
//        fileWriter.write(null); 不能直接写入 null
        fileWriter.append("并不是追加一行内容，不要被方法名迷惑\n");
        fileWriter.append(null);
        fileWriter.flush();
        System.out.println("文件的默认编码为" + fileWriter.getEncoding());
        fileWriter.close();
    }

    public void test07() throws IOException {
        FileWriter fileWriter = new FileWriter(new File("").getAbsolutePath() + "/io/test.txt", false); // 关闭追加模式，变为覆盖模式
        fileWriter.write("Hello，world！欢迎来到 java 世界\n");
        fileWriter.write("我来覆盖文件原本的内容");
        fileWriter.append("我是下一行");
        fileWriter.flush();
        System.out.println("文件的默认编码为" + fileWriter.getEncoding());
        fileWriter.close();
    }

    public void test08() throws IOException {
        FileReader fileReader = new FileReader(new File("").getAbsolutePath() + "/io/test.txt");
        BufferedReader br = new BufferedReader(fileReader);
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        br.close();
        fileReader.close();
    }

    public void test09() throws IOException {
        FileReader fileReader = new FileReader(new File("").getAbsolutePath() + "/io/test.txt");

        int c;
        while ((c = fileReader.read()) != -1) {
            System.out.print((char) c);
        }
    }
}
