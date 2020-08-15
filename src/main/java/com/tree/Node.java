package com.tree;

class Node {

    private int id;

    private String name;

    private Node left; //左子节点

    private Node right; //右子节点

    public Node(int id, String name){
        this.id = id;
        this.name = name;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    /**
     *
     * 前序查找
     * 判断当前节点的是否是要查到的结点，如果是就返回
     * 否则判断左子节点是否为空，不为空对左子节点进行前序查找
     * 如果左子节点前序查找没有找到，则判断右子节点是否为空，如果不为空，对右子节点进行前序查找
     *
     */
    public Node prevSearch(int id){
        if(this.id == id) return this;

        Node node = null;
        if(this.left != null){
            node = this.left.prevSearch(id);
        }

        if(node != null) return node;

        if (this.right != null){
            node = this.right.prevSearch(id);
        }
        return node;
    }

    /**
     *
     * 中序查找
     * 判断当前结点的左子节点是否为空，如果不为空，则对当前节点的左子节点进行中序查找
     * 如果找到则返回
     * 否则与当前节点进行比较，如果相等则返回，如果不相等则判断右子节点是否为空，如果不为空则对右子节点进行中序查找
     *
     *
     */
    public Node midSearch(int id){
        Node node = null;
        if(this.left != null){
            node = this.left.midSearch(id);
        }

        if(node != null){
            return node;
        } else if(this.id == id){
            return this;
        }

        if (this.right != null){
            node = this.right.midSearch(id);
        }
        return node;
    }

    /**
     *
     * 后序查找
     * 判断当前结点的左子节点是否为空，如果不为空，则对当前节点的左子节点进行后序查找
     * 如果找到则返回
     * 否则判断右子节点是否为空，如果不为空则对右子节点进行后序查找
     * 如果找到则返回
     * 否则与当前节点进行比较，如果相等则返回
     *
     *
     */
    public Node postSearch(int id){
        Node node = null;
        if(this.left != null){
            node = this.left.postSearch(id);
        }

        if(node != null) return node;

        if (this.right != null){
            node = this.right.postSearch(id);
        }

        if(node != null) return node;

        if(this.id == id) return this;

        return null;
    }

    /**
     *   前序遍历
     *   从root节点开始遍历
     *
     *   先输出当前节点
     *   如果左子节点不为空，递归遍历左子节点，如果右子节点不为空，继续遍历右子节点
     *
     */
    public void prevOrder(){
        System.out.println(this);
        if(this.left != null){
            this.left.prevOrder();
        }
        if (this.right != null){
            this.right.prevOrder();
        }
    }

    /**
     *   中序遍历
     *   从root开始
     *   先遍历左子树的节点，输出当前节点，左子树遍历完后输出父节点
     *   再接着遍历右子树
     *
     */
    public void midOrder(){
        if(this.left != null){
            this.left.midOrder();
        }
        System.out.println(this);
        if(this.right != null){
            this.right.midOrder();
        }
    }

    /**
     *
     * 后序遍历
     * 先遍历左子树，遍历到根节点，输出当前节点
     * 再遍历右子树，遍历到根节点，输入当前节点
     * 最后输出root节点
     *
     */
    public void postOrder(){
        if(this.left != null){
            this.left.postOrder();
        }
        if(this.right != null){
            this.right.postOrder();
        }
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
