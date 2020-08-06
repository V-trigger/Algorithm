package com.arrayqueue;


import java.util.Arrays;

/**
 * 数组模拟环形队列
 *
 *
 *
 *
 *
 *
 *
 *
 */
public class ArrayQueue {

    //队列对容量
    private int maxSize;
    //队首 数组的第一个元素的index
    private int first;
    //队尾 数组的最后一个元素的index+1
    private int last;
    //队列数组
    int arr[];

    public ArrayQueue(int maxSize){
        //创建队列
        this.arr = new int[maxSize+1]; //多出一个空间给环形计算做预留
        this.maxSize = maxSize+1;
        this.first = 0;
        this.last = 0;
    }


    //入队
    public void add(int n){
        if(isFull()){
            System.out.println("队列已满");
            return;
        }
        arr[last] = n;
        //当last没到maxSize时，后移一位，当last超过maxSize时，移到数组第1位;实现队列首位相连
        last = (last + 1)  % maxSize;
    }

    //出队
    public int pop(){
        if(isEmpty()){
            throw new RuntimeException("队列为空");
        }
        int res = arr[first];
        first = (first + 1)  % maxSize;
        return res;
    }

    //显示队列
    public void show(){
        if(isEmpty()){
            System.out.println("empty...");
            return ;
        }
        for (int i = first; i != last; i = (i + 1)  % maxSize) {
            System.out.print(arr[i]+"\t");
        }
        System.out.println(Arrays.toString(arr));
    }


    //判断队列是否满
    public boolean isFull(){
        return (last + 1)  % maxSize == first;
    }

    //判断队列是否为空
    public boolean isEmpty(){
        return last == first;
    }


}
