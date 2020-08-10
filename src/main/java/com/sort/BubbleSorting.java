package com.sort;

import java.util.Arrays;

/**
 * 排序算法
 */
public class BubbleSorting {

    public static void main(String[] args) {
        int arr[] = new int[100000];
        for (int i = 0; i < 100000; i++) {
            arr[i] = (int) (Math.random() * 100000);
        }

        int arr1[] = arr.clone();
        int arr2[] = arr.clone();

        long l = System.currentTimeMillis();
        System.out.println("冒泡排序");
        BubbleSorting.bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println(System.currentTimeMillis() - l );

        l = System.currentTimeMillis();
        System.out.println("冒泡排序 优化1");
        BubbleSorting.bubbleSort1(arr1);
        System.out.println(Arrays.toString(arr1));
        System.out.println(System.currentTimeMillis() - l );

        l = System.currentTimeMillis();
        System.out.println("冒泡排序 优化2");
        BubbleSorting.bubbleSort2(arr2);
        System.out.println(Arrays.toString(arr2));
        System.out.println(System.currentTimeMillis() - l );
    }

    //冒泡排序
    public static void bubbleSort(int arr[]){
        /**
         *
         * 思路:
         *     数组有n个元素 从第一个数开始前后两个比较,
         *     如果后者大于前者
         *        交换两个数的位置,否则什么都不做
         *     如此比较n-1次，就能把最大的那一个数放到数组的最后一个位置
         *
         *     对以上操作重复n次，每次不用比较已经排好的数(每次比较次数-1)
         *     直到最后一次比较完毕，数组就已经有序
         */
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if(arr[j] > arr[j+1]){
                    swap(arr, j, j+1);
                }
            }
        }

    }


    //冒泡排序 优化1
    public static void bubbleSort1(int arr[]){
        /**
         *
         * 优化:
         *     如果发现任意次排序中, 发生交换的次数为0,表示已经有序，此时结束排序即可
         *
         */
        boolean flag;  //是否交换
        for (int i = 0; i < arr.length - 1; i++) {
            flag = false;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if(arr[j] > arr[j+1]){
                    swap(arr, j, j+1);
                    flag = true;
                }
            }
            if(!flag) break;
        }

    }

    //冒泡排序 优化2
    public static void bubbleSort2(int arr[]){
        /**
         *
         * 在优化1的基础上再进行优化
         * 思路:
         *     第一轮
         *     从头到尾，开始比较，比较出一个最大数，并记录交换最后交换的位置
         *     从最后交换的位置开始，往前比较，找到一个最小数
         *     这样一趟排序就排好了一个第一个数和最后一个数
         *
         *     第二轮
         *     从第二个数开始往倒数第二个数，比较出一个最大的数，并记录最后交换的位置
         *     从最后交换的位置开始往第二个数，比较出一个最下的数
         *     这样第二趟排序就找到了排好了第二个数和倒数第二个数
         *
         *     第三轮
         *     ....重复
         */
        int count = 0;
        boolean flag;  //是否交换
        int k = arr.length - 1;
        for (int i = 0; i < arr.length - 1; i++) {
            flag = false;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if(arr[j] > arr[j+1]){
                    swap(arr, j, j+1);
                    k = j;
                    flag = true;
                }
            }

            for (int j = k; j > i; j--) {
                if(arr[j] < arr[j-1]){
                    swap(arr, j, j-1);
                    flag = true;
                }
            }
            if(!flag) break;
        }

    }


    public static void swap(int arr[], int i, int j){
        arr[i] = arr[i] + arr[j];
        arr[j] = arr[i] - arr[j];
        arr[i] = arr[i] - arr[j];
    }



}
