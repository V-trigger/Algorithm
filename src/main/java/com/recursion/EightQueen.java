package com.recursion;

import java.util.Arrays;

/**
 * 八皇后问题 穷举
 */
public class EightQueen {

    public static int count = 0;

    public static void main(String[] args) {
        //初始化8*8矩阵
        int matrix[][] = new int[8][8];

        setQueen(matrix,0, 0);
        for (int[] c : matrix){
            System.out.println(Arrays.toString(c));
        }
        System.out.println("调用" + count + "次");
    }

    /**
     *
     * @param matrix 矩阵
     * @param
     * @param setCol  上一行放置的列
     * @return
     */
    public static void setQueen(int matrix[][], int row, int setCol){
        count++;
        /**
         *
         * 思路:
         *  可放置点(0) 占用点(1) 错误点(3)
         *  起始点, (row, col)
         *  找到row行第一个可放置列 emptyCol
         *  如果找到了,并且row是最后一行，并且有对应col列可以放置,代表已经放置完成
         *      将皇后放置，然后返回
         *  否则
         *      如果找到有对应emptyCol
         *          将皇后放置(row, setCol),并把这个点设为占用点
         *          将起始点设置为(row+1,0)
         *          重复以上步骤
         *          如果发现当前标记点被置为了错误点,则再次重复以上步骤
         *      否者
         *          将(row-1,setCol)行的占用点设置为错误点
         *          返回 -> 上一层调用接着处理
         */
        //找是放置点
        int emptyCol = -1;
        if(row == 0){
            emptyCol = setCol;
        } else {
            for (int i = 0; i < matrix.length; i++) {
                if (isSetable(matrix, row, i)) {
                    emptyCol = i;
                    break;
                }
            }
        }
        //最后一行
        if(row == matrix.length - 1 && emptyCol != -1){
            //得到了一个解
            matrix[row][emptyCol] = 1;
            return;
        }
        //不是最后一行
        if(emptyCol != -1){
            matrix[row][emptyCol] = 1;
            setQueen(matrix,row+1, emptyCol);

            if(matrix[row][emptyCol] == 3) {
                //将下一行的错误点全部置为可放置点,并重新放置当前行
                for (int i = 0; i < matrix.length; i++) {
                    matrix[row+1][i] = 0;
                }
                setQueen(matrix, row, setCol);
            }

        } else {
            matrix[row-1][setCol] = 3;
            return;
        }

    }

    //是否能放置
    public static boolean isSetable(int matrix[][], int row, int col){
        ////纵向横向有错误点可以放置，但是放置的点不能为错误点
        if(matrix[row][col] == 3) return false;
        //左上到右下初始点为 (row-min(row, col), col-min(row,col))
        int min = row < col ? row : col;
        int offsetRow1 = row - min;
        int offsetCol1 = col - min;

        /**
         *
         *  分析:  从row行往上找初始点，每移动一行执行 row--, col++ 最终row = 0；col加了row次
         *  左下到右上的初始点为  (0,row+col)
         *  考虑矩阵大小: 如果 row + col > matrix.length - 1 表示超出矩阵
         *               超出部分为 overflow =  (row + col) - (matrix.length - 1)
         *               结果为  (0 + overflow, (row + col) - overflow) => (overflow, matrix.length - 1)
         */
        int offsetRow2 = 0;
        int offsetCol2 = row + col;
        if(offsetCol2 > matrix.length-1){
            int overflow = offsetCol2 - (matrix.length - 1);
            offsetRow2 = overflow;
            offsetCol2 = matrix.length - 1;
        }

        for (int i = 0; i < matrix.length; i++) {
            //纵向是否冲突
            if(matrix[i][col] == 1){
//                System.out.println("纵向冲突");
                return false;
            }
            //左下到右上是否冲突
            if(offsetRow1 < row && offsetCol1 < matrix.length){
                if(matrix[offsetRow1][offsetCol1] == 1){
//                    System.out.println("减斜向冲突");
                    return false;
                }
                offsetRow1++;
                offsetCol1++;
            }
            //左上到右下是否冲突
            if(offsetRow2 < row && offsetCol2 > 0){
                if(matrix[offsetRow2][offsetCol2] == 1){
//                    System.out.println("增斜向冲突");
                    return false;
                }
                offsetRow2++;
                offsetCol2--;
            }
        }
        return true;
    }

}
