package com.sort;

import java.util.Arrays;

/**
 *
 * 基数排序(时间换空间经典算法)
 *
 */
public class RadixSorting {


    public static void main(String[] args) {
        int arr[] = new int[10000000];
        for (int i = 0; i < 10000000; i++) {
            arr[i] = (int) (Math.random() * 10000000);
        }

        long l = System.currentTimeMillis();
        radixSort(arr);
        System.out.println(System.currentTimeMillis() - l );
        System.out.println(ToolSort.isSort(arr));

    }

    public static void radixSort(int arr[]){
        /**
         * 思想: 从个位开始排到最大元素的位数位
         * 思路:
         *     定义10个桶，每个桶的大小为序列的长度(可以使用队列进行空间优化)
         *     第一轮将个位数为n的放入第n个桶
         *     放完过后从第一个桶到最后一个桶依次取出里面的元素放回数组
         *
         *     第二轮将十位数为n的放入第n个桶
         *     放完过后从第一个桶到最后一个桶依次取出里面的元素放回数组
         *
         *     整个操作需要进行序列最大数的位数这么多轮
         *
         */
        int len = arr.length;
        int maxLen = maxLength(arr);
        int bucket[][] = new int[10][len];  //定义桶 占len * 11 * 4个Byte = 44*len / 1024 /1024/ 1024 个G
        int bucketIndex[];   //每个桶放了多少个元素

        int index; //放入的桶的索引
        int arrIndex; //数组的索引
        for (int i = 0, offset = 1; i < maxLen; i++, offset *= 10) {
            bucketIndex = new int[10]; //初始化每个桶的索引为0
            for (int j = 0; j < len; j++) {
                index = arr[j] / offset % 10; //offset位数的值,也就是要放入的桶的索引
                bucket[index][bucketIndex[index]++] = arr[j];  //装到对应的桶
            }
//            printBucket(bucket);
            //从桶中倒回数组中去
            arrIndex = 0;
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < bucketIndex[j]; k++) {
                    arr[arrIndex++] = bucket[j][k];
                }
            }
//            System.out.println(Arrays.toString(arr));
        }

    }


    public static void printBucket(int bucket[][]){
        for(int [] b : bucket){
            for(int v : b){
                System.out.print(v+"\t");
            }
            System.out.println();
        }
    }

    //最大数的位数
    public static int maxLength(int arr[]){
        int len = arr.length;
        int max;
        int max1 = arr[0];
        int max2 = arr[len-1];
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] > max1) max1 = arr[i];
            if(arr[len-i-1] > max2) max2 = arr[len-i-1];
            if(i >= len-i-1) break;
        }
        max = max1 >= max2 ? max1 : max2;
        return (max+"").length();
    }

}
