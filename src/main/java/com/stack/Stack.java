package com.stack;

/**
 * 数组模拟栈
 */
public class Stack<T> {

    private int maxSize; //栈的大小

    private T stack[];

    private int top = -1; //栈顶

    public Stack(int maxSize) {
        this.maxSize = maxSize;
        stack = (T[]) new Object[maxSize];
    }

    //入栈
    public void push(T value){
        if(isFull()){
            System.out.println("栈满");
            return ;
        }
        top++;
        stack[top] = value;
    }

    //出栈
    public T pop(){
        if(isEmpty()){
            return null;
        }
        return (T) stack[top--];
    }

    public void show(){
        int offset = top;
        while (offset >= 0){
            System.out.println(stack[offset--]);
        }
    }

    public T peek(){
        if(isEmpty()){
            throw new RuntimeException("栈为空");
        }
        return stack[top];
    }

    public boolean isEmpty(){
        return top == -1;
    }

    public boolean isFull(){
        return top == maxSize -1;
    }

}
