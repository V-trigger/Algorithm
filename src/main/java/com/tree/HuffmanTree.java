package com.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 *
 * 名词:
 *     路径：在一棵树中，一个结点到另一个结点之间的通路
 *
 *     路径长度：在一条路径中，每经过一个结点，路径长度都要加 1 。例如在一棵树中，规定根结点所在层数为1层，那么从根结点到第 i 层结点的路径长度为 i - 1
 *
 *     结点的权：给每一个结点赋予一个新的数值，被称为这个结点的权。
 *
 *     结点的带权路径长度：指的是从根结点到该结点之间的路径长度与该结点的权的乘积
 *
 *     树的带权路径长度(WPL): 指的是树中所有叶子结点的带权路径长度之和。
 *
 * 哈夫曼树:
 *     当用 n 个结点（都做叶子结点且都有各自的权值）试图构建一棵树时，如果构建的这棵树的带权路径长度最小，称这棵树为“最优二叉树”，有时也叫“赫夫曼树”或者“哈夫曼树”。
 *
 *     在构建哈弗曼树时，要使树的带权路径长度最小，只需要遵循一个原则，那就是：权重越大的结点离树根越近。
 *
 */
public class HuffmanTree {

    private Node root;

    public static void main(String[] args) {
        int arr[] = {13,7,8,3,29,6,1};
        //1,3,6,7,8,13,29
        HuffmanTree huffmanTree = buildHuffmanTree(arr);
        huffmanTree.prevOrder();
    }

    //构建哈夫曼树
    public static HuffmanTree buildHuffmanTree(int arr[]){
        /**
         *
         * step:
         *     1.先将带处理节点根据权值进行排序
         *     2.取出前两个二叉树,也就是权值最小的两个
         *     3.将小的作为左子节点，大的作为右子节点，生成一个新的二叉树，新的二叉树的根节点的权值为左右两个子树的权值之和
         *     4.将新二叉树的左子节点和右子节点从待处理节点中删除
         *     5.将新的二叉树的根节点放到待处理的队列中
         *
         *     重复以上步骤，直到所有节点都处理完成时，哈夫曼树构建完成
         */
        //生成对应权值的节点
        List<Node> nodes = new ArrayList<Node>();
        for (int i = 0; i < arr.length; i++) {
            nodes.add(new Node(arr[i]));
        }
        while (nodes.size() > 1){
            //取出权值最小的两个
            Collections.sort(nodes);
            Node left = nodes.get(0);
            Node right = nodes.get(1);
            //生成新的二叉树
            Node root = new Node(left.getWeight() + right.getWeight());
            root.setLeft(left);
            root.setRight(right);
            //从待处理队列中删除左子节点和右子节点
            nodes.remove(left);
            nodes.remove(right);
            //将新的二叉树的根节点放到待处理的队列中
            nodes.add(root);
        }

        HuffmanTree huffmanTree = new HuffmanTree();
        huffmanTree.setRoot(nodes.get(0));
        return huffmanTree;
    }

    public void setRoot(Node root) {
        this.root = root;
    }


    //前序遍历
    public void prevOrder(){
        if(this.root != null){
            this.root.prevOrder();
        }
    }

    static class Node implements Comparable<Node>{

        private int weight; //权值

        private Node left; //左子节点

        private Node right; //右子节点

        public Node(int weight){
            this.weight = weight;
        }

        public void prevOrder(){
            System.out.println(this);
            if(this.left != null){
                this.left.prevOrder();
            }
            if (this.right != null){
                this.right.prevOrder();
            }
        }


        public int getWeight() {
            return weight;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "weight=" + weight +
                    '}';
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }

}
