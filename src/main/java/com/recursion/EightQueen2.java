package com.recursion;

import java.util.Arrays;

/**
 *
 * 八皇后问题 version 2 回溯
 *
 * 改进:
 *     用一位数组模拟棋盘,下标表示放置的行，值表示放置的列
 *
 *     [0, 1, 0, 0, 0, 0, 0, 0]
 *     [0, 0, 0, 1, 0, 0, 0, 0]
 *     [0, 0, 0, 0, 0, 1, 0, 0]
 *     [0, 0, 0, 0, 0, 0, 0, 1]
 *     [0, 0, 1, 0, 0, 0, 0, 0]  => [1,3,5,7,2,0,6,4]
 *     [1, 0, 0, 0, 0, 0, 0, 0]
 *     [0, 0, 0, 0, 0, 0, 1, 0]
 *     [0, 0, 0, 0, 1, 0, 0, 0]
 *
 *
 */
public class EightQueen2 {


    private int matrix[]  = new int[8];

    public int count = 0;

    public static void main(String[] args) {
        EightQueen2 eightQueen2 = new EightQueen2();
        eightQueen2.setQueen(0);
        System.out.println(eightQueen2.count);
    }

    /**
     * 放第n行的皇后
     * @param n
     */
    public void setQueen(int n){
        /**
         * 思路:
         *    从第n行开始放皇后
         *    如果n行超出棋盘，表示已经放置完成
         *        此时找到了一个解；直接退出
         *        让调用者接着处理
         *
         *    从0列开始放，放完检测是否冲突
         *    如果不冲突
         *      开始放n+1行
         *    否则
         *      将n行放到后一列，直到不冲突为为止
         *      如果皇后无处安放，则退出当前行的操作(函数出栈)
         *      让调用者接着处理
         *
         */
        if(n == matrix.length){
            //找到其中一个解
            print();
            return;
        }
        //依次将皇后放入,每行每列都放一次
        //栈底函数循环完毕之后，就得到了所有的解
        for (int i = 0; i < matrix.length; i++) {
             matrix[n] = i; //将第n行放到第i列
            //判断是否当前行是否冲突
            if(isSetable(n)){
                //不冲突继续放下一行
                setQueen(n+1);
            }
        }

    }

    /**
     * 是否可放置
     * @param n 第几个皇后，也就是第几行
     * @return
     */
    private boolean isSetable(int n){
        /**
         *
         * 分析:
         *    在同一列代表数组有重复值
         *    斜线冲突:
         *       i,n表示行 , matrix[i] ,matrix[n]表示列
         *       如果 |n-i|表示行的距离， |matrix[n] - matrix[i]| 表示列的距离
         *       如果行和列的距离相等则表示他们在同一条斜向上
         */
        for (int i = 0; i < n; i++) {
            if(matrix[i] == matrix[n]) return false;  //同列冲突
            if(Math.abs(n-i) == Math.abs(matrix[n] - matrix[i])) return false; //左上到右下冲突
        }
        return true;
    }

    public void print(){
        count++;
        System.out.println(Arrays.toString(matrix));
    }


}
