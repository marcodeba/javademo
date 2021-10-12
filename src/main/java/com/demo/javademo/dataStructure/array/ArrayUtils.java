package com.demo.javademo.dataStructure.array;

import java.util.Arrays;

/**
 * @author：marco.pan
 * @ClassName：ArrayUtils
 * @Description：
 * @date: 2021年10月12日 11:39 下午
 */
public class ArrayUtils {
    /**
     * 打印数组
     * @param array
     */
    public static void printArray(int[] array) {
        if (array != null) {
            System.out.println(Arrays.toString(array));
        }
    }

    /**
     * 根据索引下标返回数组索引下标值
     * @param arr
     * @param index
     * @return
     */
    public static int findByIndex(int[] arr, int index) {
        if (index < 0 || index >= arr.length)
            throw new IllegalArgumentException("Find failed. Require index >= 0 and index < size.");
        System.out.println("array[" + index + "] = " + arr[index]);
        return arr[index];
    }

    /**
     * 数组中找到指定element的索引下标值
     * @param arr
     * @param element
     * @return
     */
    public static int findElementInArray(int[] arr, int element) {
        if (arr == null) throw new IllegalArgumentException("Find failed, array is null");
        for (int i = 0; i <= arr.length - 1; i++) {
            if (arr[i] == element) {
                System.out.println("index of " + element + " is " + i);
                return i;
            }
        }
        System.out.println("there is no element in this array");
        return -1;
    }

    /**
     * 数组中添加元素，如果数组满了则报错
     * @param arr       存储元素的数组
     * @param size      数组中已经存储的元素数量，注意这个size只有在数组满了的情况下才会等于length
     * @param element   要插入数组的新元素
     */
    public static void addByElement(int[] arr, int size, int element) {
        if (size >= arr.length) {
            throw new IllegalArgumentException("Add failed. array is full.");
        }
        int index = size;
        for (int i = 0; i <= size - 1; i++) {
            if (arr[i] > element) {
                index = i;
                break;
            }
        }
        System.out.println("index = " + index);
        for (int i = arr.length - 1; i >= index + 1; i--) {
            arr[i] = arr[i - 1];
        }
        arr[index] = element;
        printArray(arr);
    }

    /**
     *
     * @param arr           存储元素的数组
     * @param size          数组元素数量
     * @param removeIndex   删除位置
     * @return ret          删除的元素值
     */
    public static int removeByIndex(int[] arr, int size, int removeIndex) {
        if (removeIndex < 0 || removeIndex >= size)
            throw new IllegalArgumentException("Remove failed. Index is illegal.");
        int ret = arr[removeIndex];
        for (int i = removeIndex; i <= size; i++)
            arr[i] = arr[i + 1];
        printArray(arr);
        return ret;
    }

    private static int[] initArray() {
        int[] arr = new int[10];
        arr[0] = 2;
        arr[1] = 3;
        arr[2] = 4;
        arr[3] = 9;
        arr[4] = 10;
        arr[5] = 11;
        arr[6] = 12;
        arr[7] = 13;
        printArray(arr);
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = initArray();
//        findByIndex(arr, 3);
//        findElementInArray(arr, 12);
//        addByElement(arr, 8, 8);
        removeByIndex(arr, 8, 5);
    }
}
