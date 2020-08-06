package com.arrayqueue;

import java.util.Scanner;

//
public class ArrayQueueTest {

    public static void main(String[] args) {

        ArrayQueue arrayQueue = new ArrayQueue(3);

        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop){
            System.out.println("s(show): 显示队列; a(add): 添加数据; p(pop): 取出数据; e(exit): 退出");
            System.out.print("请输入: ");
            key = scanner.next().charAt(0);
            switch (key){
                case 's':
                    arrayQueue.show();
                    System.out.println();
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                case 'a':
                    System.out.print("请输入添加的元素:");
                    int value = scanner.nextInt();
                    arrayQueue.add(value);
                    break;
                case 'p':
                    try{
                        int res = arrayQueue.pop();
                        System.out.println(res+"出队");
                    } catch (Exception e){
                        System.out.println(e.getMessage());
                    }
            }
        }
    }

}
