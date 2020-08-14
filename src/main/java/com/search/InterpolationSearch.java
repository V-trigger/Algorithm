package com.search;

/**
 *
 * 插值查找
 * 思想
 *    基于二分查找，将mid的计算公式做一些改变
 *    (left + right) / 2  <=> (right - left + 2left) / 2  <=> (right - left) /2 + left
      改为
 *    =>  left + (right-left) * (value - arr[left]) / (arr[right] - arr[left])
 *
 *    对于分布均匀的查找表来说，插值查找速度一般比二分查找快
 *    对于分布不均匀的查找表，插值查找不一定比二分查找快
 *
 */
public class InterpolationSearch {

    public static void main(String[] args) {
        int arr[] = {217, 258, 353, 365, 376, 384, 409, 452, 461, 479, 536, 549, 593, 619, 672, 699, 817, 854, 880, 980,981};
        int index1 = interpolationSearch(arr, 0,arr.length-1, 981);
        System.out.println(index1);
    }

    public static int interpolationSearch(int arr[], int left, int right, int value){

        if(left > right || value < arr[0] || value > arr[arr.length-1]) return -1;


        int mid = left + (right-left) * (value - arr[left]) / (arr[right] - arr[left]);
        System.out.println("left="+left+"right="+right+"mid="+mid);


        if(arr[mid] == value){
            return mid;
        } else if(arr[mid] > value){
            return interpolationSearch(arr, left, mid-1, value);
        } else {
            return interpolationSearch(arr, mid+1, right, value);
        }
    }



}
