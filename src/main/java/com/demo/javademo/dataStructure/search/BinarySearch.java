package com.demo.javademo.dataStructure.search;

/**
 * 时间复杂度O(logn)，空间复杂度O(1)
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] a = new int[]{0, 1, 16, 24, 35, 47, 59, 62, 73, 88, 99};
        int key = binarySearch(a, 73);
        System.out.println(key);
    }

    public static int binarySearch(int[] a, int key) {
        int low, high, middle;
        low = 0;
        high = a.length - 1;
        while (low <= high) {
            middle = (low + high) / 2;
            //middle = low + (high - low) * (key - a[low]) / (a[high] - a[low]);
            if (key < a[middle]) {
                high = middle - 1;
            } else if (key > a[middle]) {
                low = middle + 1;
            } else {
                return middle;
            }
        }
        return -1;
    }
}
