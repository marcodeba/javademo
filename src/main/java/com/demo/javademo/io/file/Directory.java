package com.demo.javademo.io.file;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

public final class Directory {
    // 本地目录中文件构成构成的File数组
    public static File[] local(File dir, final String regex) {
        return dir.listFiles(new FilenameFilter() {
            private Pattern pattern = Pattern.compile(regex);

            public boolean accept(File dir, String name) {
                return pattern.matcher(new File(name).getName()).matches();
            }
        });
    }

    public static File[] local(String path, final String regex) {
        return local(new File(path), regex);
    }

    // 给定目录下整个目录树中所有文件的构成的List
    public static TreeInfo walk(String start, String regex) {
        // Begin recursion
        return recurseDirs(new File(start), regex);
    }

    static TreeInfo recurseDirs(File startDir, String regex) {
        TreeInfo result = new TreeInfo();
        for (File item : startDir.listFiles()) {
            if (item.isDirectory()) {
                result.dirs.add(item);
                result.addAll(recurseDirs(item, regex));
            } else {
                // Regular file
                if (item.getName().matches(regex))
                    result.files.add(item);
            }
        }
        return result;
    }

    public static void main(String[] args) {
//        // All directories:
//        TreeInfo walk = Directory.walk(".", ".*");
//        List<File> dirs = walk.dirs;
//        for (File dir : dirs) {
//            System.out.println(dir.getName());
//        }
        // All files beginning with 'T'
        for (File file : Directory.local(".", ".*")) {
            System.out.println(file);
        }
        System.out.println("----------------------");
        // All Java files beginning with 'T':
        for (File file : Directory.walk(".", "T.*\\.java"))
            System.out.println(file);
        System.out.println("======================");
        // Class files containing "M" or "m":
        for (File file : Directory.walk(".", ".*[Mm].*\\.class"))
            System.out.println(file);
    }

    // A two-tuple for returning a pair of objects:
    public static class TreeInfo implements Iterable<File> {
        // 文件集合
        public List<File> files = new ArrayList<>();
        // 目录集合
        public List<File> dirs = new ArrayList<>();

        // The default iterable element is the file list:
        public Iterator<File> iterator() {
            return files.iterator();
        }

        void addAll(TreeInfo other) {
            files.addAll(other.files);
            dirs.addAll(other.dirs);
        }
    }
}
