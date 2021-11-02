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
        if (arr == null) throw new IllegalArgumentException("Find failed, array is null");

        if (index < 0 || index >= arr.length)
            throw new IllegalArgumentException("Find failed. Require index >= 0 or index < size.");
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

        int insertIndex = size;
        for (int i = 0; i <= size - 1; i++) {
            if (arr[i] > element) {
                insertIndex = i;
                break;
            }
        }
        // 从数组最后开始右移insertIndex后面所有的元素
        for (int i = arr.length - 1; i >= insertIndex + 1; i--) {
            arr[i] = arr[i - 1];
        }
        arr[insertIndex] = element;
        printArray(arr);
    }

    /**
     * 数组中删除指定位置的元素
     * @param arr           存储元素的数组
     * @param size          数组元素数量
     * @param removeIndex   删除位置
     * @return ret          删除的元素值
     */
    public static int removeByIndex(int[] arr, int size, int removeIndex) {
        if (removeIndex < 0 || removeIndex >= size)
            throw new IllegalArgumentException("Remove failed. Index is illegal.");

        int ret = arr[removeIndex];
        // 从removeIndex开始右移数组元素
        for (int i = removeIndex; i <= size; i++)
            arr[i] = arr[i + 1];
        printArray(arr);
        return ret;
    }

    public static int[] mergeV1(int[] arr1, int size1, int[] arr2, int size2) {
        int[] result = new int[size1 + size2];
        int i = 0, j = 0, index = 0;
        while (i <= size1 - 1 && j <= size2 - 1) {
            if (arr1[i] < arr2[j]) {
                result[index++] = arr1[i++];
            } else if (arr1[i] > arr2[j]) {
                result[index++] = arr2[j++];
            } else {
                result[index++] = arr1[i++];
                result[index++] = arr2[j++];
            }
        }
        if (j > size2 - 1) {
            for (int k = i; k <= arr1.length - 1; k++) {
                result[index++] = arr1[k];
            }
        }
        if (i > size1 - 1) {
            for (int k = j; k <= arr2.length - 1; k++) {
                result[index++] = arr2[k];
            }
        }
        return result;
    }

    public static int[] mergeV2(int[] arr1, int size1, int[] arr2, int size2) {
        int index = size1 + size2 - 1, j = size1 - 1, k = size2 - 1;
        int[] result = new int[index + 1];
        while (j >= 0 && k >= 0) {
            if (arr1[j] <= arr2[k]) {
                result[index--] = arr2[k--];
            } else {
                result[index--] = arr1[j--];
            }
        }
        while (k != -1) result[index--] = arr2[k--];
        while (j != -1) result[index--] = arr1[j--];

        return result;
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
//        int[] arr = initArray();
//        findByIndex(arr, 3);
//        findElementInArray(arr, 12);
//        addByElement(arr, 8, 8);
//        removeByIndex(arr, 8, 5);

//        int[] nums1 = {1,2,3,0,0,0};
//        int[] nums2 = {2,5,6};
//        int[] result = mergeV1(nums1, 0, nums2, 1);
//        printArray(result);

        int[] nums1 = {1,2,3,0,0,0};
        int[] nums2 = {2,5,6};
        int[] result = mergeV2(nums1, 3, nums2, 3);
        printArray(result);
    }
}
