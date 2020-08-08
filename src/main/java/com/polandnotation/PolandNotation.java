package com.polandnotation;

import com.stack.Stack;

/**
 * 逆波兰表达式的运算
 */
public class PolandNotation {

    public static void main(String[] args) {
        //(1+2)*3-4   1 2 + 3 * 4 -
        String suffixExpression = "1 2 + 3 * 4 -";

        Stack<Integer> stack = new Stack<>(suffixExpression.length());
        //对表达式进行遍历
        String str[] = suffixExpression.split(" ");
        int num1;
        int num2;
        int res;
        char operate[] = new char[1];
        for(String s : str){
            if(s.matches("\\d+")){
                //如果遇到数字则直接入栈
                Integer num = Integer.valueOf(s);
                stack.push(num);
            } else {
                //遇到符号把栈顶元素和次顶元素弹出，进行计算
                num1 = stack.pop();
                num2 = stack.pop();
                s.getChars(0,1,operate, 0);
                res = cal(num1, num2, operate[0]);
                //把计算结果入栈
                stack.push(res);
            }
        }
        Integer pop = stack.pop();
        System.out.println(pop);
    }

    //计算
    public static int cal(int num1, int num2, char operate){
        int res;
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
