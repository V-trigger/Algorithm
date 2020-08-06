package com.linkedlist;

public class Node1<T> {

    T value = null; //当前节点数据
    Node1<T> next = null;  //下一个节点数据

    public Node1(){}

    public Node1(T value) {
        this.value = value;
    }


    @Override
    public String toString() {
        return this.value.toString();
    }
}
