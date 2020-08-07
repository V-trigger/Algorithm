package com.linkedlist;


/**
 *
 * 单向列表
 * 只能单向查找
 * 不能自我删除
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
            if(this.value != null){
                return this.value.toString();
            } else {
                return " ";
            }
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

    //顺序插入链表，链表保存的对象必须实现Comparable接口
    public void insert(Comparable<T> value){
        Node<T> temp = head;
        //遍历链表，取到最后一个值小于待插入值的节点
        while (temp.next != null){
            if(value.compareTo(temp.next.value) < 0)  break;
            temp = temp.next;
        }
        //生成节点，插入链表
        Node<T> node = new Node<T>((T) value);
        node.next = temp.next;
        temp.next = node;
    }

    //获得链表长度
    public int getLength(){
        int len = 0;
        Node<T> temp = head;
        while (temp.next != null){
            len++;
            temp = temp.next;
        }
        return len;
    }

    //反转1 节点插入新链表的首位
    public void reverse(){
        Node<T> reverseHead = new Node<T>();
        Node<T> temp = head.next; //当前节点
        Node<T> next = null; //后一个节点
        while (temp != null){
            next = temp.next;
            //放入队首
            temp.next = reverseHead.next;
            reverseHead.next = temp;
            temp = next;
        }
        head.next = reverseHead.next;
    }

    //反转2 复制节点重新建立链表
    public void reverse2(){
        int len = getLength();
        Node<T> stack[] = new Node[len];
        Node<T> temp = head;
        int offset = 0;
        while (temp.next != null){
            stack[len - offset -1] = temp.next;
            temp = temp.next;
            offset++;
        }
        //去掉最后一个节点的next域,避免无限循环的next域
        stack[len-1].next = null;

        temp = head;
        for(Node<T> value : stack){
            temp.next = value;
            temp = temp.next;
        }
    }

    //反向遍历 栈
    public void reverseShow(){

    }



    //指定位置节点
    public void getByIndex(int n){
        int offset = 1;
        Node<T> temp = head.next;
        while (temp != null){
            if(n == offset){
                System.out.println(temp);
                return;
            }
            temp = temp.next;
            offset++;
        }
    }

}
