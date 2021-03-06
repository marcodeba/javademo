package com.demo.javademo.dataStructure.sort;

import java.util.Arrays;

/**
 * 冒泡排序只会操作相邻的两个数据。每次冒泡操作都会对相邻的两个元素进行比较，看是否满足大小关系要求。
 * 如果不满足就让它俩互换。
 */
public class BubbleSort {
    /**
     * Version 0.1
     *
     * @param array
     */
    public static void sortV1(int[] array) {
        if (array == null || array.length == 0)
            throw new IllegalArgumentException("参数错误");

        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                }
            }
        }
    }

    /**
     * Version 2.0
     *
     * @param array
     */
    public static void sortV2(int[] array) {
        if (array == null || array.length == 0)
            throw new IllegalArgumentException("参数错误");

        for (int i = 0; i < array.length - 1; i++) {
            boolean isSorted = true;
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                    // 此次冒泡有数据交换
                    isSorted = false;
                }
            }
            // 没有数据交换，提前退出
            if (isSorted) break;
        }
    }

    /**
     * Version 3.0
     *
     * @param array
     */
    public static void sortV3(int[] array) {
        if (array == null || array.length == 0)
            throw new IllegalArgumentException("参数错误");

        int lastExchangePosition = 0;
        int sortBorder = array.length - 1;
        for (int i = 0; i < array.length - 1; i++) {
            boolean isSorted = true;
            for (int j = 0; j < sortBorder; j++) {
                if (array[j] > array[j + 1]) {
                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                    isSorted = false;
                    lastExchangePosition = j;
                }
            }
            sortBorder = lastExchangePosition;
            if (isSorted) break;
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{5, 8, 6, 3, 9, 2, 1, 7};
        //sortV1(array);
        sortV2(array);
        //sortV3(array);
        System.out.println(Arrays.toString(array));
    }
}
