package com.stack;

/**
 * 数组实现栈
 */
public class Stack {

    private int maxSize; //栈的大小

    private int stack[];

    private int top = -1; //栈顶

    public Stack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }


    //入栈
    public void push(int value){
        if(isFull()){
            System.out.println("栈满");
            return ;
        }
        top++;
        stack[top] = value;
    }

    //出栈
    public Integer pop(){
        if(isEmpty()){
            return null;
        }
        return stack[top--];
    }

    public void show(){
        int offset = top;
        while (offset >= 0){
            System.out.println(stack[offset--]);
        }
    }

    public boolean isEmpty(){
        return top == -1;
    }

    public boolean isFull(){
        return top == maxSize -1;
    }

}
