package com.sort;

import java.util.Arrays;

/**
 *
 * 希尔排序
 *
 */
public class ShellSorting {

    public static void main(String[] args) {
        int arr[] = new int[10000000];
        for (int i = 0; i < 10000000; i++) {
            arr[i] = (int) (Math.random() * 10000000);
        }

        long l = System.currentTimeMillis();
//        System.out.println(Arrays.toString(arr));
        ShellSorting.shellSort(arr);
//        System.out.println(Arrays.toString(arr));
        System.out.println(System.currentTimeMillis() - l );
//
    }

    public static void shellSort(int arr[]){
        /**
         *
         * 思路:
         *     将数组分成若干组,每组n个数,此时步长为gap(gap = arr.length / n)
         *     arr[0+gap] 到 arr[0+gap*(n-1)] 为一组
         *     arr[1+gap] 到 arr[1+gap*(n-1)] 为一组
         *     .....
         *
         *     对每一组进行插入排序
         *
         *     每组都排完过后gap / 2 再进行以上操作
         *
         *     直到最后gap等于1的时候，相当于进行了整体的插入排序
         *
         *     ps: gap越接近1，数列就越接近有序；直到最后等于1，这时再进行插入排序耗费的时间就非常低了
         *
         */
        int temp;
        for (int gap = (arr.length)/2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                temp = arr[i];
                int j;
                for (j = i; j >= gap && temp < arr[j-gap]; j-=gap) {
                    arr[j] = arr[j-gap];
                }
                arr[j] = temp;
            }

        }
    }



}
