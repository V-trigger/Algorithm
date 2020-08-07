package com.linkedlist;

public class SingleLinkedListTest {

    public static void main(String[] args) {
        SingleLinkedList<Person> personSingleLinkedList = new SingleLinkedList<>();
        personSingleLinkedList.insert(new Person("张三", 22));
        personSingleLinkedList.insert(new Person("李四", 21));
        personSingleLinkedList.insert(new Person("王五", 20));
        personSingleLinkedList.insert(new Person("Marry", 19));
        personSingleLinkedList.insert(new Person("Lucy", 22));

        int length = personSingleLinkedList.getLength();
        System.out.println(length);


        System.out.println("原始链表");
        personSingleLinkedList.show();

        System.out.println("反转1");
        personSingleLinkedList.reverse();
        personSingleLinkedList.show();

        System.out.println("反转2");
        personSingleLinkedList.reverse2();
        personSingleLinkedList.show();

//        int k = 3;
//        personSingleLinkedList.getByIndex(length-k+1);
    }

}
