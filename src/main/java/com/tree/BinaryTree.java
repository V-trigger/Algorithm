package com.tree;

/**
 *
 *
 * 二叉树: 每个节点最多有两个子结点
 * 满二叉树: 二叉树的所有叶子节点都在最后一层
 * 完全二叉树: 所有的叶子结点都分布在倒数第一层和倒数第二层，并且最后一层的结点在左边连续，倒数第二层的结点在右边连续
 *
 * 术语:
 *    结点的度：一个结点拥有子树的数目称为结点的度
 *    叶子结点：度为零的结点
 *
 */

public class BinaryTree {

    private Node root; //root节点

    public static void main(String[] args) {
        //创建二叉树
        BinaryTree binaryTree = new BinaryTree();
        //创建节点
        Node root = new Node(0, "老师");
        Node root1 = new Node(1, "学生1");
        Node root2 = new Node(2, "学生2");
        Node root3 = new Node(3, "学生3");
        Node root4 = new Node(4, "学生4");
        binaryTree.setRoot(root);
        //添加节点
        root.setLeft(root1);
        root.setRight(root2);
        root2.setRight(root3);
        root2.setLeft(root4);


        System.out.println("前序遍历");
        binaryTree.prevOrder();

        System.out.println("中序遍历");
        binaryTree.midOrder();

        System.out.println("后序遍历");
        binaryTree.postOrder();

        Node node;
        System.out.println("前序查找");
        node = binaryTree.prevSearch(4);
        System.out.println(node);

        System.out.println("中序查找");
        node = binaryTree.midSearch(4);
        System.out.println(node);

        System.out.println("后序查找");
        node = binaryTree.postSearch(4);
        System.out.println(node);


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

    //中序遍历
    public void midOrder(){
        if(this.root != null){
            this.root.midOrder();
        }
    }

    //后序遍历
    public void postOrder(){
        if(this.root != null){
            this.root.postOrder();
        }
    }

    //前序查找
    public Node prevSearch(int id){
        if(this.root != null){
            return this.root.prevSearch(id);
        }
        return null;
    }

    //中序查找
    public Node midSearch(int id){
        if(this.root != null){
            return this.root.midSearch(id);
        }
        return null;
    }

    //后序查找
    public Node postSearch(int id){
        if(this.root != null){
            return this.root.postSearch(id);
        }
        return null;
    }

}


