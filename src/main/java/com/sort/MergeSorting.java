package com.sort;

import java.util.Arrays;
import java.util.logging.Level;

public class MergeSorting {

    public static void main(String[] args) {
        int arr[] = new int[10000000];
        for (int i = 0; i < 10000000; i++) {
            arr[i] = (int) (Math.random() * 10000000);
        }

        int arr1[] = arr.clone();

        System.out.println("迭代");
        long l = System.currentTimeMillis();
        mergeSort(arr);
        System.out.println(System.currentTimeMillis() - l );
        System.out.println(ToolSort.isSort(arr));


        System.out.println("递归");
        l = System.currentTimeMillis();
        mergeSort1(arr1, 0,  arr1.length-1);
        System.out.println(System.currentTimeMillis() - l );
        System.out.println(ToolSort.isSort(arr));

    }

    //合并两个有序序列
    public static void merge(int arr[], int left, int mid, int right){
        /**
         * left左边序列开始索引, mid右边序列开始索引, right序列最后
         *
         * 思路:
         *     创建一个临时数组
         *     将left, mid对应的值中较小(大)的一个放到临时数组中
         *     并将小(大)的一个值的索引后移
         *     直到left移过最初mid的位置,说明左边元素已经全部放到了临时数组中
         *         将此时mid到right的元素依次放到临时数组中
         *     或者mid移过right, 说明右边元素已经全部放到了临时数组中
         *         将left到最初mid的值依次放入临时数组中
         *
         *     此时两个有序序列就合并成了一个有序序列,并放到了临时数组中
         *
         *     将临时数组中的值放到最初left到right的位置,归并完成
         *
         */
        int temp[] = new int[right-left+1];
        int l = left;
        int m = mid++;
        int index = 0;
        while (index < temp.length){
            if(mid > right){
                //右边放完 把左边剩下的依次放入临时数组后面
                temp[index] = arr[left++];
            } else if(left > m){
                //左边放完 把右边剩下的依次放入临时数组后面
                temp[index] = arr[mid++];
            } else {
                //两边都没放完, 把小的依次放入临时数组后面
                if(arr[left] <= arr[mid]){
                    temp[index] = arr[left++];
                } else {
                    temp[index] = arr[mid++];
                }
            }
            index++;
        }
        for (int i = l; i <= right; i++) {
            arr[i] = temp[i-l];
        }
    }

    //归并排序, 迭代
    public static void mergeSort(int arr[]){
        /**
         *
         * 思路:
         *     将数组分成i个序列，i从1开始
         *     将每两个序列当做一组进行归并
         *        第一组为left = 0, mid = left+i-1, right = mid + i
         *        第二组为left = 前一组的right+1, mid = left+i-1, right = mid + i
         *        第三组    ...
         *     归并完成后，数组就为两两有序的
         *     (如果right超出数组，right就为数组最后一个元素的索引)
         *
         *     将i规模扩大一倍再进行归并，数组变成四四有序
         *     ....
         *     直到最后第一组right为整个序列的长度，再进行一次归并，序列变为全部有序
         *
         *
         */
        int left, mid, right;
        int len = arr.length;
        for (int i = 1; i < len; i *= 2) {
            left = 0;
            while (left + i < len) {
                mid = left + i - 1;
                right = mid + i < len ? mid + i : len - 1;
                merge(arr, left, mid, right);
                left = right + 1;
            }
        }
    }

    //归并排序 递归
    public static void mergeSort1(int arr[], int left,  int right){
        /**
         *
         * 思路:
         *     将序列不断拆分成两个部分，分别归并
         *     从left, mid, right(left从0开始, mid从(length + 0) / 2开始, right从序列最后一个索引开始)开始拆
         *     拆成 (left, (mid + left) / 2, mid), (mid, (right+mid)/2, right)两组进行归并排序
         *     直到最后left = right 说明序列已经拆分成了一个一个的，此时就进行归并
         */
        if(left == right) return;
        int mid = (left + right) / 2;
        mergeSort1(arr, left, mid);
        mergeSort1(arr, mid+1, right);
        merge(arr, left, mid, right);
    }

}
