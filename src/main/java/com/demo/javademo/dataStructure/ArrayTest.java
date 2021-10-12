package com.demo.javademo.dataStructure;

public class ArrayTest {
    public static void main(String[] args) {
        int[] huffmanNodes = new int[]{5, 32, 18, 7, 25, 13, -1, -1, -1, -1, -1, -1};
        int m1, m2;
        int index1, index2;
        m1 = m2 = Integer.MAX_VALUE;
        index1 = index2 = -1;
        for (int i = 0; i < huffmanNodes.length; i++) {
            if (huffmanNodes[i] < m1) {
                m2 = m1;
                m1 = huffmanNodes[i];
                index1 = i;
            } else if (huffmanNodes[i] < m2) {
                m2 = huffmanNodes[i];
                index2 = i;
            }
        }
        System.out.println(m1);
        System.out.println(index1);
        System.out.println(m2);
        System.out.println(index2);
    }
}
