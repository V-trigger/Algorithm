package com.search;

/**
 * 线性查找
 * 逐个比对
 */
public class SeqSearch {

    public static void main(String[] args) {
        int arr[] = {365, 461, 880, 409, 217, 353, 479, 980, 593, 452, 384, 376, 672, 549, 258, 699, 536, 619, 854, 817};
        int index = search(arr, 672);
        System.out.println(index);
        System.out.println(arr[index]);

    }

    public static int search(int arr[], int value){
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] == value) return i;
        }
        return -1;
    }

}
