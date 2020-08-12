package com.sort;

import java.util.Arrays;

/**
 *
 * 选择排序
 *    第一次从待排序的数据元素中选出最小的一个元素
 *    存放在序列的起始位置
 *    然后再从剩余的未排序元素中寻找到最小元素
 *    然后放到已排序的序列的末尾
 *
 *    以此类推，直到全部待排序的数据元素的个数为零。选择排序是不稳定的排序方法
 *
 */
public class SelectSorting {

    public static void main(String[] args) {
        int arr[] = new int[100000];
        for (int i = 0; i < 100000; i++) {
            arr[i] = (int) (Math.random() * 100000);
        }

        long l = System.currentTimeMillis();
        SelectSorting.selectSort(arr);
        System.out.println(System.currentTimeMillis() - l );
        System.out.println(ToolSort.isSort(arr));

    }


    //选择排序
    public static void selectSort(int arr[]){
        int min;  //待排序数组的最小值的下标
        for (int i = 0; i < arr.length; i++) {
            min = i;
            for (int j = i+1; j < arr.length; j++) {
                if(arr[j] < arr[min]) {
                    min = j;
                }
            }
            if(min != i) swap(arr, i, min);
        }
    }


    public static void swap(int arr[], int i, int j){
        arr[i] = arr[i] + arr[j];
        arr[j] = arr[i] - arr[j];
        arr[i] = arr[i] - arr[j];
    }

}
