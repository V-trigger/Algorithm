package com.tree;

/**
 *
 * 线索化二叉树
 * 对二叉树以某种遍历方式进行遍历的顺序中，某个节点的前一个节点为他的前驱节点，后一个节点为他的后继节点
 * 如果这个节点的left指向为空，则将left指向他的前驱节点，如果right为空，则将他的right指向他的后继节点
 * 这个过程就是线索化二叉树
 *
 */
public class ThreadedBinaryTree {

    private Node root; //root节点

    private Node pre = null; //保存当前节点的前驱节点

    public static void main(String[] args) {
        Node root = new Node(1, "学生1");
        Node node1 = new Node(2, "学生2");
        Node node2 = new Node(3, "学生3");
        Node node3 = new Node(4, "学生4");
        Node node4 = new Node(5, "学生5");
        Node node5 = new Node(6, "学生6");
        Node node6 = new Node(7, "学生7");

        root.setLeft(node1);
        root.setRight(node2);
        node1.setLeft(node3);
        node1.setRight(node4);
        node2.setLeft(node5);
        node2.setRight(node6);
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);

    }

    public void preThreaded() {
        pre = null;
        this.preThreaded(root);
    }

    public void midThreaded() {
        pre = null;
        this.midThreaded(root);
    }

    public void postThreaded() {
        pre = null;
        this.postThreaded(root);
    }



    //前序线索化二叉树
    public void preThreaded(Node node) {

        if(node == null) return;

        //线索化当前节点
        threadedNode(node);
        pre = node;

        //线索化左子树
        if(node.getLeftType() == 0) preThreaded(node.getLeft());

        //线索化右子树
        if(node.getRightType() == 0) preThreaded(node.getRight());

    }


    //中序线索化二叉树
    public void midThreaded(Node node) {

        if(node == null) return;

        //线索化左子树
        midThreaded(node.getLeft());

        //线索化当前节点
        threadedNode(node);
        pre = node;

        //线索化右子树
        midThreaded(node.getRight());

    }

    //后序线索化二叉树
    public void postThreaded(Node node) {

        if(node == null) return;

        //线索化左子树
        postThreaded(node.getLeft());

        //线索化右子树
        postThreaded(node.getRight());

        //线索化当前节点
        threadedNode(node);
        pre = node;

    }

    //线索化节点
    public void threadedNode(Node node){
        if(node.getLeft() == null) {
            node.setLeft(pre);
            node.setLeftType((byte) 1);
        }

        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setRightType((byte) 1);
        }
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public void preThreadedOrder(){
        root.preThreadedOrder();
    }

    public void midThreadedOrder(){
        root.midThreadedOrder();
    }


    static class Node {

        private int id;

        private String name;

        private Node left; //左子节点

        private Node right; //右子节点

        //left指向类型 0左子树，1前驱节点
        private byte leftType;

        //right指向类型 0右子树，1后继节点
        private byte rightType;


        public Node(int id, String name){
            this.id = id;
            this.name = name;
        }

        //前序遍历线索化二叉树
        public void preThreadedOrder(){
            Node node = this;
            while (node != null){
                //输出left节点指向左子树的全部节点
                System.out.println(node);
                while (node.getLeftType() == 0){
                    node = node.getLeft();
                    System.out.println(node);
                }
                node = node.getRight();
            }
        }

        //遍历中序线索化二叉树
        public void midThreadedOrder(){
            Node node = this;
            while (node != null){
                //遍历左子树，直到找到节点的left指向不是左子树的节点
                //相当于就是找到中序遍历输出的第一个节点
                while (node.getLeftType() == 0){
                    node = node.getLeft();
                }
                System.out.println(node); //输出当前节点
                //持续输出并更新right指向为后继节点的节点
                //直到right指向不为后继节点的节点
                while (node.getRightType() == 1){
                    node = node.getRight();
                    System.out.println(node);
                }
                node = node.getRight();
            }
        }


        public void setLeftType(byte leftType) {
            this.leftType = leftType;
        }

        public void setRightType(byte rightType) {
            this.rightType = rightType;
        }


        public Node getLeft() {
            return left;
        }

        public Node getRight() {
            return right;
        }

        public byte getLeftType() {
            return leftType;
        }

        public byte getRightType() {
            return rightType;
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
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

}



