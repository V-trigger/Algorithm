package com.search;


import com.sort.QuickSorting;
import com.sort.ShellSorting;

import java.util.Arrays;

/**
 *
 * 二分查找
 * 必须先排序，再查找
 *
 */
public class BinarySearch {


    public static void main(String[] args){
        int arr[] = {217, 258, 353, 365, 376, 384, 409, 452, 461, 479, 536, 549, 593, 619, 672, 699, 817, 854, 880, 980,9800};
        ShellSorting.shellSort(arr);
        System.out.println(Arrays.toString(arr));
        int index1 = binarySearch(arr, 0,arr.length, 11);
        System.out.println(index1);
        int index2 = binarySearch1(arr, 11);
        System.out.println(index2);
    }

    //二分查找，递归
    public static int binarySearch(int arr[],int left, int right, int value){

        int mid = (right + left) /2;
        if(left > right) return  -1;

        if(arr[mid] == value){
            return mid;
        } else if(arr[mid] > value){
            return binarySearch(arr, left, mid - 1, value);
        } else {
            return binarySearch(arr, mid + 1, right, value);
        }
    }

    //二分查找 迭代
    public static int binarySearch1(int arr[],int value){

        int left = 0;
        int right = arr.length;
        int mid = (right + left) /2;
        while (left <= right){
            if(arr[mid] == value){
                return mid;
            } else if(arr[mid] > value){
                right = mid - 1;
            } else {
                left = mid + 1;
            }
            mid = (right + left) /2;
        }
        return -1;
    }

}
