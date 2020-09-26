package com.sparsematrix;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * 稀疏矩阵
 */
public class SparseMatrix {

    static String file = "map.dat";

    public static void main(String[] args) {
        //原始矩阵 11 * 11
        int matrix[][] = new int[11][11];
        //矩阵数据位置
        matrix[1][2] = 1;
        matrix[2][4] = 2;
        matrix[3][5] = 2;
        matrix[3][6] = 1;

        //输出原始矩阵
        System.out.println("原始矩阵:");
        SparseMatrix.printMatrix(matrix);

        int[][] sparseMatrix = SparseMatrix.encode(matrix);

        System.out.println("稀疏矩阵:");
        SparseMatrix.printMatrix(sparseMatrix);

        int[][] matrixDecode = SparseMatrix.decode(sparseMatrix);

        System.out.println("恢复后原始矩阵:");
        SparseMatrix.printMatrix(matrixDecode);
        SparseMatrix.save(sparseMatrix);
        int[][] read = SparseMatrix.read();
        System.out.println("读取的矩阵:");
        SparseMatrix.printMatrix(read);

    }

    //创建稀疏矩阵
    public static int[][] encode(int[][] matrix){
        //遍历矩阵
        int count = 0;

        for(int[] row : matrix){
            for(int value : row){
                if(value != 0) count++;
            }
        }

        //创建稀疏矩阵
        int sparseMatrix[][] = new int[count+1][3];
        //填充稀疏矩阵行、列、有效值个数
        sparseMatrix[0][0] = matrix.length;
        sparseMatrix[0][1] = matrix[0].length;
        sparseMatrix[0][2] = count;
        //填充稀疏矩阵值
        int offset = 1;
        for (int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix.length ; j++){
                if(matrix[i][j] != 0) {
                    sparseMatrix[offset][0] = i;
                    sparseMatrix[offset][1] = j;
                    sparseMatrix[offset][2] = matrix[i][j];
                    offset++;
                }
            }
        }
        return sparseMatrix;
    }

    //还原始矩阵
    public static int[][] decode(int[][] sparseMatrix){
        //创建原始矩阵
        int matrix[][] = new int[sparseMatrix[0][0]][sparseMatrix[0][1]];

        //遍历稀疏矩阵 给原始矩阵赋值
        for (int i = 1; i < sparseMatrix.length; i++) {
            matrix[sparseMatrix[i][0]][sparseMatrix[i][1]] = sparseMatrix[i][2];
        }

        return matrix;
    }

    //保存到文件
    public static void save(int[][] sparesMatrix){
        String data = "";
        for (int i = 0; i < sparesMatrix.length; i++) {
            data += sparesMatrix[i][0] + "\t" + sparesMatrix[i][1] + "\t" + sparesMatrix[i][2]+ "\n";
        }

        BufferedOutputStream bufferedOutputStream = null;
        try {
            if(!Files.exists(Paths.get(SparseMatrix.file))){
                Files.createFile(Paths.get(SparseMatrix.file));
            }
            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(SparseMatrix.file));
            bufferedOutputStream.write(data.getBytes(),0, data.length());
            bufferedOutputStream.flush();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if(bufferedOutputStream != null) {
                try {
                    bufferedOutputStream.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }

    }

    //从文件读取
    public static int[][] read() {

        if(!Files.exists(Paths.get(SparseMatrix.file))){
            return new int[0][0];
        }

        BufferedInputStream bufferedInputStream = null;
        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream(SparseMatrix.file));
            byte buffs[] = new byte[1024];
            int len;
            String data = "";
            while ((len = bufferedInputStream.read(buffs)) != -1) {
                data = new String(buffs, 0, len);
            }
            if(!"".equals(data)) {
                String[] split = data.split("\n");
                int sparseMatrix[][] = new int[split.length][3];
                for (int i = 0; i < split.length; i++) {
                    String[] split1 = split[i].split("\t");
                    for (int j = 0; j < 3; j++) {
                        sparseMatrix[i][0] = Integer.parseInt(split1[0]);
                        sparseMatrix[i][1] = Integer.parseInt(split1[1]);
                        sparseMatrix[i][2] = Integer.parseInt(split1[2]);
                    }
                }
                return sparseMatrix;
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if(bufferedInputStream != null){
                try {
                    bufferedInputStream.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }

        return new int[0][0];
    }

    //打印矩阵
    public static void printMatrix(int[][] matrix){
        for(int[] row : matrix){
            for (int data : row){
                System.out.print(data+"\t");
            }
            System.out.println("\n");
        }
    }

}
