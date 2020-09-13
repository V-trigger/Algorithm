package com.graph;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * 邻接矩阵表示无向图
 *
 */
public class AdjacencyMatrix {

    public static void main(String[] args) {
        String vertexes[] = {"A", "B", "C", "D", "E"};
        AdjacencyMatrix adjacencyMatrix = new AdjacencyMatrix(vertexes.length);
        for (String s : vertexes){
            adjacencyMatrix.addVertex(s);
        }
        adjacencyMatrix.addEdge(0, 1, 1);
        adjacencyMatrix.addEdge(0, 2, 1);
        adjacencyMatrix.addEdge(1, 2, 1);
        adjacencyMatrix.addEdge(1, 3, 1);
        adjacencyMatrix.addEdge(1, 4, 1);

        adjacencyMatrix.show();
    }

    //顶点
    private ArrayList<String> vertexes;

    //邻接矩阵
    private int[][] adjacencyMatrix;

    //边的数量
    private int edgeNum;

    public AdjacencyMatrix(int vertexNum){
        adjacencyMatrix = new int[vertexNum][vertexNum];
        vertexes = new ArrayList<String>(vertexNum);
        edgeNum = 0;
    }

    //添加顶点
    public void addVertex(String vertex){
        vertexes.add(vertex);
    }

    /**
     * 添加边
     * @param v1 顶点1的索引
     * @param v2 顶点2的索引
     * @param weight 权值
     */
    public void addEdge(int v1, int v2, int weight){
        adjacencyMatrix[v1][v2] = weight;
        adjacencyMatrix[v2][v1] = weight;
        edgeNum++;
    }

    //顶点的个数
    public int vertexNum(){
        return vertexes.size();
    }

    //边的数量
    public int edgeNum(){
        return edgeNum;
    }

    //邻接矩阵对应的顶点的值
    public String getValue(int index){
        return vertexes.get(index);
    }

    //获得顶点之间的权值
    public int getWeight(int v1, int v2){
        return adjacencyMatrix[v1][v2];
    }

    //打印邻接表
    public void show(){
        for(int row[] : adjacencyMatrix){
            System.out.println(Arrays.toString(row));
        }
    }

}
