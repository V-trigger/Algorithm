package com.tree;

/**
 *
 * 二叉排序树
 * 若左子树不空，则左子树上所有结点的值均小于它的根结点的值
 * 若右子树不空，则右子树上所有结点的值均大于它的根结点的值
 * 左、右子树也分别为二叉排序树
 * 没有键值相等的结点
 *
 */
public class BST {

    public static void main(String[] args) {
        BST bst = new BST();
        bst.add(bst.buildNode(6));
        bst.add(bst.buildNode(8));
        bst.add(bst.buildNode(9));
        bst.add(bst.buildNode(7));
        bst.add(bst.buildNode(3));
        bst.add(bst.buildNode(2));
        bst.add(bst.buildNode(4));
        bst.add(bst.buildNode(10));
//        bst.midOrder();

//        Node search = bst.search(4);
//        System.out.println(search.getParent());
        System.out.println();
        bst.del(6);
        bst.midOrder();
    }

    private Node root;

    public void add(Node node){
        if(root == null){
            root = node;
            return;
        }
        root.add(node);
    }

    /**
     * 查找节点
     * @param value
     * @return
     */
    public Node search(int value){
        if(root == null) return null;
        return root.search(value);
    }

    /**
     * 删除节点
     * @param value
     */
    public void del(int value){
        if(root == null) return;

        //待删除的节点
        Node node = search(value);
        if(node == null) return;

        //BST只有一个根节点
        if(root.left == null && root.right == null){
            root = null;
            return;
        }

        //待删除的节点是叶子节点
        if(node.left == null && node.right == null){
            //判断待删除的节点是父节点的左子节点还是右子节点
            if(node.parent.left != null && node.parent.left.value == node.value){
                node.parent.left = null;
            } else {
                node.parent.right = null;
            }
            return;
        }

        //待删除的节点有两个子树
        if(node.left != null && node.right != null){
            //找出当前节点右子树中最小的节点
            //就是右子树一直往左找到的最后一个节点
            //这个节点肯定是一个叶子节点
            Node temp = node.right;
            while (temp.left != null){
                temp = temp.left;
            }
            //删除当前节点右子树中最小的节点
            del(temp.value);
            //将右子树中最小的节点的数据覆盖到待删除节点
            node.value = temp.value;
            return;
        }

        //待删除的节点只有左子树
        if(node.left != null){
            //判断待删除的节点是父节点的左子节点还是右子节点
            if(node.parent.left != null && node.parent.left.value == node.value){
                node.parent.left = node.left;
            } else {
                node.parent.right = node.left;
            }
            return;
        }

        //待删除的节点只有右子树
        if(node.right != null){
            //判断待删除的节点是父节点的左子节点还是右子节点
            if(node.parent.left != null && node.parent.left.value == node.value){
                node.parent.left = node.right;
            } else {
                node.parent.right = node.right;
            }
        }

    }

    public void midOrder(){
        root.midOrder();
    }

    public Node buildNode(int value){
        return new Node(value);
    }

    private class Node{
        private int value;
        private Node left;
        private Node right;
        private Node parent;

        public Node(int value) {
            this.value = value;
        }

        /**
         * 添加节点
         * @param node
         */
        private void add(Node node){
            if(node == null) return;
            node.parent = this;
            if(node.value < this.value){
                if(this.left == null){
                    this.left = node;
                } else {
                    this.left.add(node);
                }
            } else if(node.value > this.value){
                if(this.right == null){
                    this.right = node;
                } else {
                    this.right.add(node);
                }
            }
        }

        /**
         * 添加节点
         * @param value
         */
        private Node search(int value){
            if(value == this.value){
                return this;
            } else if(value > this.value){
                if(this.right == null) return null;
                return this.right.search(value);
            } else {
                if(this.left == null) return null;
                return this.left.search(value);
            }
        }

        /**
         * 中序遍历
         */
        private void midOrder(){
            if(this.left != null){
                this.left.midOrder();
            }
            System.out.println(this);
            if(this.right != null){
                this.right.midOrder();
            }
        }

        public Node getParent() {
            return parent;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    '}';
        }
    }

}
