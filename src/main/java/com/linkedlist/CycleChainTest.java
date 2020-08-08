package com.linkedlist;


public class CycleChainTest {

    public static void main(String[] args) {
        CycleChain<Student> studentCycleChain = new CycleChain<>();
        studentCycleChain.add(new Student(1,"一号小孩"));
        studentCycleChain.add(new Student(2,"二号小孩"));
        studentCycleChain.add(new Student(3,"三号小孩"));

        studentCycleChain.show();

    }

}

class Student {

    int no;
    String name;

    public Student(int no, String name) {
        this.no = no;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}
