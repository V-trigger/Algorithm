package com.linkedlist;

public class DoubleLinkedListTest {

    public static void main(String[] args) {
        DoubleLinkedList<Person> personDoubleLinkedList = new DoubleLinkedList<>();
        personDoubleLinkedList.add(new Person("张三", 22));
        personDoubleLinkedList.add(new Person("李四", 21));
        personDoubleLinkedList.add(new Person("王五", 20));
        personDoubleLinkedList.add(new Person("Marry", 19));
        personDoubleLinkedList.add(new Person("Lucy", 22));

        personDoubleLinkedList.show();
    }

}
