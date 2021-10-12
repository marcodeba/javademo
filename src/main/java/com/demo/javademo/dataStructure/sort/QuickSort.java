package com.demo.javademo.dataStructure.sort;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 快速排序思想是基于分治策略，算法思想如下：
 * 1. 分解：从数列中选出一个元素作为基准元素。以基准元素为基础，将问题分解为两个子序列，使小于等于基准元素的子序列在左侧，大于基准元素的子序列在右侧
 * 2. 治理：对两个子序列进行快速排序
 * 3. 合并：将排好序的两个子序列合并，得到原问题的解
 */
public class QuickSort {
    /**
     * 递归算法
     * @param array
     * @param low
     * @param high
     */
//    public static void quickSort(int[] array, int low, int high) {
//        if (array == null)
//            throw new IllegalArgumentException("参数错误");
//        if (low >= high) return;
//
//        int pivotIndex = partition(array, low, high);
//        quickSort(array, low, pivotIndex - 1);
//        quickSort(array, pivotIndex + 1, high);
//    }

    /**
     * 非递归算法
     *
     * @param array
     * @param low
     * @param high
     */
    public static void quickSort(int[] array, int low, int high) {
        Stack<Map<String, Integer>> stack = new Stack<>();
        Map<String, Integer> rootParam = new HashMap<>();
        rootParam.put("low", low);
        rootParam.put("high", high);
        stack.push(rootParam);

        while (!stack.isEmpty()) {
            Map<String, Integer> param = stack.pop();
            int pivotIndex = partition2(array, param.get("low"), param.get("high"));
            if (param.get("low") < pivotIndex - 1) {
                Map<String, Integer> leftParam = new HashMap<>();
                leftParam.put("low", param.get("low"));
                leftParam.put("high", pivotIndex - 1);
                stack.push(leftParam);
            }
            if (param.get("high") > pivotIndex + 1) {
                Map<String, Integer> rightParam = new HashMap<>();
                rightParam.put("low", pivotIndex + 1);
                rightParam.put("high", param.get("high"));
                stack.push(rightParam);
            }
        }
    }

    private static int partition(int[] array, int low, int high) {
        int i = low, j = high, pivot = array[low];
        while (i < j) {
            // 从右往左扫描，找小于等于pivot的数，如果找到，array[i]和array[j]交换，i++
            while (i < j && array[j] > pivot) {
                j--;
            }
            if (i < j) {
                int temp = array[j];
                array[j] = array[i];
                array[i++] = temp;
            }
            // 从左往右扫描，找到大于pivot的数，如果找到，array[i]和array[j]交换，j--
            while (i < j && array[i] <= pivot) {
                i++;
            }
            if (i < j) {
                int temp = array[j];
                array[j--] = array[i];
                array[i] = temp;
            }
        }
        return i;
    }

    public static int partition2(int[] array, int low, int high) {
        int i = low, j = high, pivot = array[low];
        while (i < j) {
            while (i < j && array[j] > pivot) j--;
            while (i < j && array[i] <= pivot) i++;
            if (i < j) {
                // array[i]和array[j]互换
                int temp = array[i];
                array[i++] = array[j];
                array[j--] = temp;
            }
        }
        // i = j，如果array[i] > pivot，则array[i - 1] 与 pivot 互换
        if (array[i] > pivot) {
            int temp = array[i - 1];
            array[i - 1] = array[low];
            array[low] = temp;
            return i - 1;
        }
        // 否则array[i] 与 pivot 互换
        int temp = array[i];
        array[i] = array[low];
        array[low] = temp;
        return i;
    }

    public static void main(String[] args) {
        int[] array = new int[]{4, 4, 6, 5, 3, 2, 8, 1};
        quickSort(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));
    }
}
