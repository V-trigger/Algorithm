package com.linkedlist;

/**
 *
 * 单向列表
 *
 */
public class SingleLinkedList<T> {

    //节点类
    class Node<T> {

        T value = null; //当前节点数据
        Node<T> next = null;  //下一个节点数据

        //用于头结点初始化
        public Node(){}

        //构造节点
        public Node(T value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value.toString();
        }
    }

    //初始化头结点
    private Node<T> head= new Node<T>();

    //添加节点到单项列表
    public void add(T value){
        Node<T> temp = head;
        //遍历链表,找到最后的节点
        while (true){
            if(temp.next == null) break;
            temp = temp.next;
        }
        temp.next = new Node<T>(value);
    }

    //显示链表
    public void show(){
        Node<T> temp = head;
        while (temp.next != null){
            System.out.println(temp.next);
            temp = temp.next;
        }
    }

    //插入链表 若需使用此方法，链表保存的对象必须实现Comparable接口
    public void insert(Comparable<? extends T> value){

    }

}
