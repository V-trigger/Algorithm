package com.sort;

public class ToolSort {

    //检查数组是否有序
    public static boolean isSort(int arr[]){
        boolean res = true;
        for (int i = 1; i < arr.length; i++) {
            if(arr[i-1] > arr[i]){
                res = false;
                break;
            }
        }
        return res;
    }

}
