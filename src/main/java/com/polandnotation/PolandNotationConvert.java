package com.polandnotation;

import com.stack.Stack;

/**
 *
 * 中缀表达式转后缀表达式
 * TODO: 小数兼容，空白、制表符过滤
 *
 */
public class PolandNotationConvert {


    public static Stack<String> convert(String expression){
        String res = "";
        //使用两个栈, 其中一个用户保存运算符，另外一个保存后缀表达式
        Stack<Character> stack1 = new Stack<>(expression.length());  //保存运算符
        Stack<String> stack2 = new Stack<>(expression.length());  //保存表达式

        //从左到右进行扫描
        char buff[] = new char[expression.length()];
        expression.getChars(0,expression.length(), buff, 0);


        int offset = 0;
        Character operate;
        for (int i = 0; i < buff.length; i++) {
            //扫描完毕
            if(i+1 == buff.length){
                String s = new String(buff, offset, i-offset+1);
                stack2.push(s);
                break;
            }

            if(Character.isDigit(buff[i]) && Character.isDigit(buff[i+1])) continue; //多位数处理

            if(Character.isDigit(buff[i])) {  //匹配到操作数
                //操作数直接入表达式栈
                String s = new String(buff, offset, i - offset + 1);
                stack2.push(s);
                offset += s.length();
            } else if('(' == buff[i]) {
                //遇到左括号，直接入操作符栈
                stack1.push(buff[i]);
                offset++;
            } else if(')' == buff[i]){
                //如果遇到右括号,则将符号栈中的符号依次弹出，直到遇到左括号为止(左括号出栈，但不入表达式栈)
                while (true){
                    operate = stack1.pop();
                    if(operate == '(') break;
                    stack2.push(operate.toString());
                }
                offset++;
            } else { //匹配到操作符
                /**
                 * 如果操作符栈为空，
                 * 或者栈顶元素为"("，
                 * 或者优先级比栈顶优先级高，
                 * 将其直接入栈
                 * 否则将操作符栈弹出一位放入表达式栈 直到满足上述条件为止
                 */
                while (true) {
                    if (stack1.isEmpty() || '(' == stack1.peek() || priority(buff[i]) > priority(stack1.peek())) {
                        stack1.push(buff[i]);
                        break;
                    } else {
                        stack2.push(stack1.pop().toString());
                    }
                }
                offset++;
            }
        }

        //操作符栈剩余的操作符压入表达式栈
        while (!stack1.isEmpty()){
            stack2.push(stack1.pop().toString());
        }

        //当前 表达式栈的出栈顺序为后缀表达式的逆序
        //新建一个栈，保存顺序的后缀表达式
        Stack<String> stack = new Stack<>(expression.length());
        while (!stack2.isEmpty()){
            stack.push(stack2.pop());
        }
        return stack;
    }

    //判断运算符优先级
    private static int priority(char operate){
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

}
