package com.linkedlist;

/**
 * 环形链表
 */
public class CycleChain<T> {

    //节点类
    class Node<T> {

        T value = null; //节点数据
        Node<T> next = null;  //下一个节点

        //用于头结点初始化
        public Node(){}

        //构造节点
        public Node(T value) {
            this.value = value;
        }

        @Override
        public String toString() {
            if(this.value != null){
                return this.value.toString();
            } else {
                return " ";
            }
        }
    }


    private Node<T> first = null;

    //添加节点
    public void add(T value){
        if(first == null){
            first = new Node<T>(value);
            first.next = first;
            return;
        }
        Node<T> temp = first;
        while (temp.next != first){
            temp = temp.next;
        }
        Node<T> node = new Node<>(value);
        node.next = first;
        temp.next = node;
    }

    //遍历
    public void show(){
        Node<T> temp = first;
        do {
            System.out.println(temp);
            temp = temp.next;
        } while (temp != first);
    }

}
