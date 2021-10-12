package com.demo.javademo.io.file;

import java.io.File;

/**
 * args:.*\.txt
 */
public class DirList {
    public static final String path = "/Users/marcopan/mysourcecode/javademo/src/main/java/com/demo/javademo/io/file";

    public static void main(final String[] args) {
        showFileList(path);

//        File file = new File(".");
//        String[] list;
//        if (args[0] == null) {
//            list = file.list();
//        } else {
//            list = file.list(new FilenameFilter() {
//                private Pattern pattern = Pattern.compile(args[0]);
//                @Override
//                public boolean accept(File dir, String name) {
//                    return pattern.matcher(name).matches();
//                }
//            });
//        }
//        Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
//        for (String dirItem : list) {
//            System.out.println(dirItem);
//        }
    }

    // 通过传参的方式来对方法实现调用
    private static void showFileList(String scanPackage) {
        //File[] listFiles = dir.listFiles((dir1, name) -> name.endsWith(".java"));
        File files = new File(scanPackage);
        for (File file : files.listFiles()) {
            // 遍历的文件是文件夹的时候
            if (file.isDirectory()) {
                // 使用递归的思想在循环里面重复之前操作,但是要有恰当的退出时机
                showFileList(scanPackage + File.separator + file.getName());
            } else {
                // 遍历的是文件直接打印
                System.out.println(file.getName());
            }
        }
    }
}
