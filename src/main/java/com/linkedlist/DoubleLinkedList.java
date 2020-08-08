package com.linkedlist;

public class DoubleLinkedList<T> {

    //节点类
    class Node<T> {

        T value = null; //节点数据
        Node<T> next = null;  //后一个节点
        Node<T> prev = null; //前一个节点

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

    //初始化头结点
    private Node<T> head= new Node<T>();

    //添加节点到最后
    public void add(T value){
        Node<T> temp = head;
        //遍历链表,找到最后的节点
        while (true){
            if(temp.next == null) break;
            temp = temp.next;
        }
        temp.next = new Node<T>(value);
        temp.next.prev = temp;
    }

    //删除节点
    public void del(Comparable<T> value){
        Node<T> temp = head.next;
        while (temp != null){
            if(value.compareTo(temp.value) == 0) break;
            temp = temp.next;
        }
        if(temp != null){
            Node<T> node = new Node<T>((T) value);
            temp.prev.next = temp.next;
            if(temp.next != null){
                temp.next.prev = temp.prev;
            }
        }
    }

    //遍历
    public void show(){
        Node<T> temp = head;
        while (temp.next != null){
            System.out.println(temp.next);
            temp = temp.next;
        }
    }

}
