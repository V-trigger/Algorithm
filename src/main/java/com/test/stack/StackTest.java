package com.test.stack;


import com.stack.Stack;

import java.util.Scanner;

/**
 *
 * 使用栈计算一个四则运算表达式的值
 *
 * 例如 1+2*3+1
 * 扫描     数值栈                  符号栈
 * step1     1
 * ------------------------------------------------
 * step2     1                       +
 * ------------------------------------------------
 * step3     2
 *           1                       +
 * ------------------------------------------------
 * step4     2                       *
 *           1                       +
 * ------------------------------------------------
 * step5     3
 *           2                       *
 *           1                       +
 * ------------------------------------------------
 * step6     6                       +
 *           1                       +
 * ------------------------------------------------
 * step7     1
 *           6                       +
 *           1                       +
 *
 * 分析:
 *     对表达式进行扫描
 *     如果扫描到数值则直接入栈，并把索引移到下一个数值开头
 *     如果扫描到操作符：
 *         与栈顶的操作符进行优先级比较，如果当前优先级 <= 栈顶操作符优先级
 *              从数值栈出栈两个数，从操作符栈出栈一个操作符并进行运算,将运算的结果入数值栈(step6),然后将当前操作符入操作符栈
 *         否则直接将当前操作符入栈
 *         tip: 只要是操作符，任何情况都会入栈
 *
 *     扫描完毕之后:
 *         依次从数值栈pop出两个数，从操作符栈pop出一个操作符,进行计算
 *         直到操作符栈空
 */
public class StackTest {

    public static void main(String[] args) {
        System.out.println("请输入一个表达式:");
        Scanner scanner = new Scanner(System.in);
        String expression = scanner.next();

        Stack<Integer> numStack = new Stack<>(expression.length()); //保存操作数的栈
        Stack<Character> operateStack = new Stack<>(expression.length()); //保存操作符的栈

        char buff[] = new char[expression.length()];
        expression.getChars(0,expression.length(), buff, 0);

        int offset = 0; //扫描索引
        int num1;
        int num2;
        for (int i = 0; i < buff.length; i++) {
             //扫描完毕
             if(i+1 == buff.length){
                 String s = new String(buff, offset, i-offset+1);
                 numStack.push(Integer.parseInt(s));
                 break;
             }

             //多位数值连续扫描
             //当前字符为数字，且后一位字符也为数字表示当前字符为多位数值
             if(Character.isDigit(buff[i]) && Character.isDigit(buff[i+1])) continue;

             if(Character.isDigit(buff[i])){
                 //当前数字扫描完,入操作数栈
                 String s = new String(buff, offset, i - offset+1);
                 numStack.push(Integer.parseInt(s));
                 offset = i+2; //索引后移两位，指向后面的数值
             } else {
                 //符号
                 //判断符号栈是否为空
                 if(!operateStack.isEmpty()){
                     //不为空,比较符号优先级
                     if(priority(buff[i]) <= priority(operateStack.peek())){
                         //如果当前操作符优先级小于栈顶操作符优先级,则先计算前两位操作数的结果
                         //出栈两个操作数
                         num1 = numStack.pop();
                         num2 = numStack.pop();
                         //出栈一个符号
                         Character operate = operateStack.pop();
                         //进行计算
                         int res = cal(num1, num2, operate);
                         //把运算的结果入栈
                         numStack.push(res);
                     }
                 }
                 //把当前操作符入栈
                 operateStack.push(buff[i]);
             }
        }
        //依次计算
        int res;
        while (!operateStack.isEmpty()){
            Character operate = operateStack.pop();
            num1 = numStack.pop();
            num2 = numStack.pop();
            res = cal(num1, num2, operate);
            //结果入栈
            numStack.push(res);
        }
        System.out.println(numStack.pop());
    }

    //判断运算符优先级
    public static int priority(char operate){
        switch (operate){
            case '*':
            case '/':
                return 1;
            case '+':
            case '-':
                return 0;
            default:
                throw new RuntimeException("表达式错误");
        }
    }

    //计算
    public static int cal(int num1, int num2, char operate){
        int res = 0;
        switch (operate){
            case '*':
                res = num2 * num1;
                break;
            case '/':
                if(num1 == 0) throw new RuntimeException("表达式错误");
                res = num2 / num1;
                break;
            case '+':
                res = num2 + num1;
                break;
            case '-':
                res = num2 - num1;
                break;
            default:
                throw new RuntimeException("表达式错误");
        }
        return res;
    }


}
