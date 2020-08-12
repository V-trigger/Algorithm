package com.sort;

import java.util.Arrays;

/**
 *
 *  快速排序
 *
 */
public class QuickSorting {


    public static void main(String[] args) {
        int arr[] = new int[10];
        for (int i = 0; i < 10; i++) {
            arr[i] = (int) (Math.random() * 100000);
        }

        long l = System.currentTimeMillis();
        System.out.println(Arrays.toString(arr));
        quickSort(arr, 0, arr.length-1);
        System.out.println(Arrays.toString(arr));
        System.out.println(System.currentTimeMillis() - l );

    }

    public static void quickSort(int arr[], int l, int r){
        /**
         * 思路:
         *     将待排数组左边第一个设置为基准数
         *     从待排数组右边到左边找一个小于基准数的数，并记录索引
         *     从待排数组左边到右边找一个大于基准数的数，并记录索引
         *     将两个数交换位置
         *     直到右边和左边的索引走到一起，交换基准数和走到一起时的索引
         *     完成这些操作过后，基准数左边的数都是小于基准数的，基准数右边的数都是大于基准数的
         *
         *     将待排数组分成两个 左边到最后交换的索引为一组， 最后交换的索引+1 到右边为一组。将两组分别进行以上操作
         *
         *     直到最后每组的最左边的索引和最右边的索引都无法进行排序，此时数组就为有序的
         */
        if(l >= r) return;
        int lBak = l;
        int rBak = r;
        int temp = arr[l];
        for(;l<r;){
            while (r > l && arr[r] >= temp){
                r--;
            }
            while (r > l && arr[l] <= temp){
                l++;
            }
            swap(arr, r, l);
        }
        arr[lBak] = arr[l];
        arr[l] = temp;

        quickSort(arr, lBak, l);
        quickSort(arr, l+1, rBak);
    }

    public static void swap(int arr[], int i, int j){
        if(i == j) return;
        arr[i] = arr[i] + arr[j];
        arr[j] = arr[i] - arr[j];
        arr[i] = arr[i] - arr[j];
    }

}
