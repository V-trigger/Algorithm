package com.linkedlist;

public class SingleLinkedListTest {

    public static void main(String[] args) {
        SingleLinkedList<Person> personSingleLinkedList = new SingleLinkedList<>();
        personSingleLinkedList.add(new Person("张三", 20));
        personSingleLinkedList.add(new Person("李四", 21));
        personSingleLinkedList.add(new Person("王五", 22));

        personSingleLinkedList.show();
    }

}
