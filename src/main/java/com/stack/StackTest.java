package com.stack;

public class StackTest {

    public static void main(String[] args) {
        Stack stack = new Stack(5);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.push(6);

//        Integer res;
//        while ( (res = stack.pop()) != null){
//            System.out.println(res);
//        }
        int res = stack.pop();
        System.out.println(res);
    }

}
