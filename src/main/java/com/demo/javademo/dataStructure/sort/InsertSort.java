package com.demo.javademo.dataStructure.sort;

import java.util.Arrays;

public class InsertSort {
    public static void insertionSort(int[] array) {
        if (array == null || array.length == 0)
            throw new IllegalArgumentException("参数错误");

        for (int i = 1; i < array.length; ++i) {
            int value = array[i];
            int j = i - 1;
            // 查找插入的位置
            for (; j >= 0; j--) {
                if (array[j] > value) {
                    // 数据移动
                    array[j + 1] = array[j];
                } else {
                    break;
                }
            }
            // 插入数据
            array[j + 1] = value;
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{5, 8, 6, 3, 9, 2, 1, 7};
        insertionSort(array);
        System.out.println(Arrays.toString(array));
    }
}