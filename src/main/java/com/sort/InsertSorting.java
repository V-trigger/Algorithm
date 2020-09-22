package com.sort;


import java.util.Arrays;

/**
 *
 * 直接插入排序
 *
 */

public class InsertSorting {

    public static void main(String[] args) {
        int arr[] = new int[100000];
        for (int i = 0; i < 100000; i++) {
            arr[i] = (int) (Math.random() * 100000);
        }

        long l = System.currentTimeMillis();
        InsertSorting.insertSort(arr);
        System.out.println(System.currentTimeMillis() - l );
        System.out.println(ToolSort.isSort(arr));

    }

    public static void insertSort(int[] arr) {
        /**
         *
         * 思路:
         *     把数组分成有序和无序部分，开始默认第一位有序
         *     从无序序列里面拿出第一个待插入数
         *     反向遍历有序部分，依次比较，如果大于(小于)待插入数就后移一位
         *     找到了第一个小于待插入数的位置，将待插入数放到这个数后面
         */
        int temp;
        for (int i = 1; i < arr.length; i++) {
            temp = arr[i];
            int j;
            for (j = i; j > 0 && temp < arr[j-1]; j--) {
                arr[j] = arr[j-1];
            }
            arr[j] = temp;
        }
    }

}
