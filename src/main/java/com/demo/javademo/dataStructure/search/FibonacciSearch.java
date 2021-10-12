package com.demo.javademo.dataStructure.search;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Fibonacci Search 斐波那契查找，利用黄金分割原理实现
 *  
 * 算法复杂度 O(logn)
 * Fibonacci Search examines relatively closer elements in subsequent steps.
 * So when input array is big that cannot fit in CPU cache or even in RAM, Fibonacci Search can be useful.
 */
public class FibonacciSearch {
    public static void main(String[] args) {
        int[] a = new int[]{1, 5, 15, 22, 25, 31, 39, 42, 47, 49, 59, 68, 88, 88};
        System.out.println("待查找数组 a：" + Arrays.toString(a));
        System.out.println("待查找数组长度为：" + a.length);
        System.out.println("结果在数组 a 角标的 [" + fibonacciSearch(59, a) + "] 位");
    }

    public static int fibonacciSearch(int key, int[] a) {
        //斐波那契数列中的值-1 
        int fibo = 0;
        //斐波那契数列中的角标值 
        int index = 0;
        //用于展示斐波那契数列 
        ArrayList<Integer> fibonacciArray = new ArrayList<>();
        //计算length位于斐波那契数列的位置 
        while (a.length > fibo) {
            fibo = getFibonacci(index++);
            fibonacciArray.add(fibo);
            if (a.length <= fibo) {
                index--;
            }
        }
        //用于展示 
        System.out.println("斐波那契数列为：" + fibonacciArray.toString());
        System.out.println();

        //定义临时数组来扩展待查数组的长度，长度为 fibo 
        int[] temp = new int[fibo - 1];
        System.arraycopy(a, 0, temp, 0, a.length);

        for (int i = a.length; i < temp.length; i++) {
            temp[i] = a[a.length - 1];
        }
        System.out.println("补充后的数组 temp 为：" + Arrays.toString(temp));
        System.out.println("补充后的数组长度为：" + temp.length);

        //初始化记录首位 末位 middle非字面意义上的中间值，仅是将数组分割为两部分
        int low = 0;
        int high = a.length - 1;
        int middle;
        while (low <= high) {
            //计算分割数组处的角标
            middle = low + fibonacciArray.get(index - 1) - 1;
            System.out.println("斐波那契角标 =  " + index + "   将key与数组的角标 [" + (middle) + "] 所在的值做比较");
            if (key < temp[middle]) {
                high = middle - 1;
                index -= 1;
            } else if (key > temp[middle]) {
                low = middle + 1;
                index -= 2;
            } else {
                //如果值相等且角标小于或等于待查数组的最大角标 返回middle 表示找到 
                if (middle <= a.length - 1) {
                    return middle;
                } else {
                    //如果值相等但角标大于待查数组的最大角标 
                    //这样表示在与temp数组比较时，比较的角标超过了待查数组的角标 
                    //但在补充temp数组时，后面的值都是待查数组的最后一位值 
                    //所以所寻找的key正好是待查找数组的最后一位 
                    System.out.println("所寻找的key正好是待查找数组的最后一位");
                    return a.length - 1;
                }
            }
        }
        return 0;
    }

    //生成斐波那契数
    public static int getFibonacci(int n) {
        if (n < 1) {
            return 0;
        }
        int[] fibonacciArray = new int[n + 1];
        fibonacciArray[0] = 0;
        fibonacciArray[1] = 1;
        for (int i = 2; i <= n; i++) {
            fibonacciArray[i] = fibonacciArray[i - 1] + fibonacciArray[i - 2];
        }
        return fibonacciArray[n];
    }
}