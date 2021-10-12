package com.demo.javademo.dataStructure.search;

/**
 * 插值查找
 */
public class InterpolationSearch {
    public static void main(String[] args) {
        int[] a = new int[]{0, 1, 16, 24, 35, 47, 59, 62, 73, 88, 99};
        int key = search(a, 73);
        System.out.println(key);
    }

    public static int search(int[] a, int key) {
        int low = 0;
        int high = a.length - 1;
        int mid;
        while (a[low] != a[high] && key >= a[low] && key <= a[high]) {
            mid = low + (high - low) * (key - a[low]) / (a[high] - a[low]);
            if (key < a[mid]) {
                high = mid - 1;
            } else if (key > a[mid]) {
                low = mid + 1;
            } else {
                return mid;
            }
        }
        if (key == a[low]) return low;
        else return -1;
    }
}
