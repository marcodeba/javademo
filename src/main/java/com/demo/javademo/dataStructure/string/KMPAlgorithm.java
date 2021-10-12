package com.demo.javademo.dataStructure.string;

public class KMPAlgorithm {
    public static void main(String[] args) {
        System.out.println(kmpMatch("abaabaabbabaaabaabbabaab", "abaabbabaab"));
//        int[] next = getNext("abaabbabaab".toCharArray());
//        System.out.println(java.util.Arrays.toString(next));
    }

    // 字符组成的这个子串最长的相同前缀后缀的长度数组
    public static int[] getNext(char[] t) {
        int[] next = new int[t.length];
        next[0] = -1;
        next[1] = 0;
        int k;
        for (int i = 2; i < t.length; i++) {
            k = next[i - 1];
            while (k != -1) {
                if (t[k] == t[i - 1]) {
                    next[i] = k + 1;
                    break;
                } else {
                    next[i] = 0;
                    k = next[k];
                }
            }
        }
        return next;
    }

    // i不回退
    public static int kmpMatch(String dest, String pattern) {
        char[] destArray = dest.toCharArray();
        char[] patternArray = pattern.toCharArray();
        int[] next = getNext(patternArray);
        int i = 0, j = 0;
        while (i < destArray.length && j < patternArray.length) {
            if (j == -1 || (destArray[i] == patternArray[j])) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }
        return (j == patternArray.length) ? (i - j) : -1;
    }
}
