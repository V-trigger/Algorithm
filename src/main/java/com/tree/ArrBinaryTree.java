package com.tree;

/**
 *
 * 顺序存储二叉树
 * 第n个节点的左子节点索引为y1(y1=2n+1),右子节点为y2(y2=2n+2)
 * 第y个节点的父节点为n(n=(y-1) /  2)
 * 第n个节点的父节点为
 *
 */
public class ArrBinaryTree {

    private int arr[];

    public static void main(String[] args) {
        int arr[] = {1,2,3,4,5,6,7};
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        System.out.println("前序遍历");
        arrBinaryTree.preOrder();
        System.out.println(" 中序遍历");
        arrBinaryTree.midOrder();
        System.out.println("后序遍历");
        arrBinaryTree.postOrder();
    }

    public ArrBinaryTree(int arr[]){
        this.arr = arr;
    }

    public void preOrder(){
        this.preOrder(0);
    }

    public void midOrder(){
        this.midOrder(0);
    }

    public void postOrder(){
        this.postOrder(0);
    }



    //前序遍历
    public void preOrder(int index){
        if(arr == null || arr.length == 0) return;

        System.out.println(arr[index]);
        int left = 2 * index + 1;

        if(left < arr.length){
            preOrder(left);
        }
        int right = 2 * index + 2;
        if(right < arr.length){
            preOrder(right);
        }
    }

    //中序遍历
    public void midOrder(int index){
        if(arr == null || arr.length == 0) return;

        int left = 2 * index + 1;
        if(left < arr.length){
            midOrder(left);
        }

        System.out.println(arr[index]);

        int right = 2 * index + 2;
        if(right < arr.length){
            midOrder(right);
        }

    }

    //后序遍历
    public void postOrder(int index){
        if(arr == null || arr.length == 0) return;

        int left = 2 * index + 1;
        if(left < arr.length){
            midOrder(left);
        }

        int right = 2 * index + 2;
        if(right < arr.length){
            midOrder(right);
        }

        System.out.println(arr[index]);

    }


}
