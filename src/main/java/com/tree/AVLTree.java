package com.tree;

/**
 *
 * 平衡二叉树
 * 平衡二叉树是特殊的二叉搜索树
 * 它的左子树的高度和右子树的高度差不能超过1
 * 并且他的它子树和右子树都是平衡二叉树
 *
 */
public class AVLTree {

    public static void main(String[] args) {
        AVLTree avl = new AVLTree();
        avl.add(avl.buildNode(16));
        avl.add(avl.buildNode(3));
        avl.add(avl.buildNode(7));

        avl.midOrder();
        System.out.println(avl.root.deepth());

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
            //待删除节点是否是根节点
            if(node.parent != null) {
                //判断待删除的节点是父节点的左子节点还是右子节点
                node.left.parent = node.parent;
                if (node.parent.left != null && node.parent.left.value == node.value) {
                    node.parent.left = node.left;
                } else {
                    node.parent.right = node.left;
                }
            } else {
                //删除的是根节点, 将根节点替换为左子节点
                node.left.parent = null;
                root = node.left;
            }
            return;
        }

        //待删除的节点只有右子树
        if(node.right != null){
            //待删除节点是否是根节点
            if(node.parent != null) {
                //判断待删除的节点是父节点的左子节点还是右子节点
                node.right.parent = node.parent;
                if (node.parent.left != null && node.parent.left.value == node.value) {
                    node.parent.left = node.right;
                } else {
                    node.parent.right = node.right;
                }
            } else {
                //删除的是根节点, 将根节点替换为右子节点
                node.right.parent = null;
                root = node.right;
            }
        }

    }


    public void midOrder(){
        if(root == null) return;
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
            this.doubleRotate();

        }

        /**
         * 左旋转
         *     降低右子树的高度
         *     将根节点的右子树的左子树取出来， 放到一个临时节点的右子树上
         *     这个临时节点的值为根节点的值，左子树为根节点的左子树
         *     把根节点的值替换为根节点的右子节点的值，根节点的左子节点替换为临时节点
         *     再把根节点根节点的右子树设置为根节点的右子树的右子树，相当于把右子树的根节点抽掉
         *     相当于就把原来二叉树的右子节点抽掉了，右子树就少了一层
         * step:
         *     1.创建一个临时节点，值为当前节点的值
         *     2.设置新节点的左子树为当前节点的左子树
         *     3.设置新节点的右子树为当前节点的右子树的左子树
         *     4.当前节点的值替换为右子节点的值
         *     5.当前节点的右子树替换为当前节点的右子树的右子树
         *     6.设置当前节点的左子树为新的节点
         */
        private void leftRotate(){

            //用当前节点拷贝出一个新的节点
            Node newNode = new Node(this.value);

            //设置新节点的左子树为当前节点的左子树
            if(this.left != null) {
                this.left.parent = newNode;
            }
            newNode.left = this.left;


            if(this.right != null){
                //设置新节点的右子树为当前节点的右子树的左子树
                if(this.right.left != null){
                    this.right.left.parent = newNode;
                }
                newNode.right = this.right.left;

                //当前节点的值替换为右子节点的值
                this.value = this.right.value;

                //当前节点的右子树替换为当前节点的右子树的右子树
                if(this.right.right != null){
                    this.right.right.parent = this;
                }
                this.right = this.right.right;


            }
            //设置当前节点的左子树为新的节点
            newNode.parent = this;
            this.left = newNode;
        }

        /**
         * 右旋转
         *  step
         *     1.创建一个临时节点, 值为当前节点的值
         *     2.设置临时节点的右子子树为当前节点的右子树
         *     3.设置临时节点的左子树为当前节点的左子树的右子树
         *     4.设置当前节点的值为当前节点的左子节点的值
         *     5.设置当前节点的左子树为当前节点的左子树的左子树
         *     6.设置当前节点的右子树为临时节点
         */
        public void rightRotate(){

            //创建一个临时节点, 值为当前节点的值
            Node newNode = new Node(this.value);

            //设置临时节点的右子子树为当前节点的右子树
            if(this.right != null){
                this.right.parent = newNode;
                newNode.right = this.right;
            }


            if(this.left != null){
                //设置临时节点的左子树为当前节点的左子树的右子树
                if (this.left.right != null) {
                    this.left.right.parent = newNode;
                }
                newNode.left = this.left.right;

                //设置当前节点的值为当前节点的左子节点的值
                this.value = this.left.value;

                //设置当前节点的左子树为当前节点的左子树的左子树
                if(this.left.left != null){
                    this.left.left.parent = this.left;
                }
                this.left = this.left.left;
            }


            //设置当前节点的右子树为临时节点
            newNode.parent = this;
            this.right = newNode;

        }

        /**
         *
         * 双旋转
         *    单旋转(左旋转，右旋转)的问题:
         *        某些情况旋转过后并不一定是avl树，比如依次添加[10,7,12,6,8,9]生成的BST
         *        这种情况进行右旋转的时候，根节点的左子树的右子树高度大于根节点的左子树的左子树高度
         *        进行旋转过后右子树的高度大于与左子树的高度仍然是大于1的
         * step
         *    满足左旋转的条件
         *        如果右子树的左子树高度大于右子树的右子树高度，先对右子树进行右旋转
         *    满足右旋转的条件
         *        如果左子树的右子树高度大于左子树的左子树高度，先对左子树进行左旋转
         */
        public void doubleRotate(){
            //满足左旋转的条件
            if(this.rightDeep() - this.leftDeepth() > 1){
                if(this.right.leftDeepth() > this.right.rightDeep()){
                    this.right.rightRotate();
                }
                this.leftRotate();
            }
            //满足右旋转的条件
            if(this.leftDeepth() - this.rightDeep() > 1){
                if(this.left.rightDeep() > this.left.leftDeepth()){
                    this.left.leftRotate();
                }
                this.rightRotate();
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

        /**
         * 左子树高度
         * @return
         */
        private int leftDeepth(){
            if(this.left == null) return 0;
            return this.left.deepth();
        }

        /**
         * 右子树高度
         * @return
         */
        private int rightDeep(){
            if(this.right == null) return 0;
            return this.right.deepth();
        }

        /**
         * 获取树的高度
         * @return
         */
        private int deepth(){
            return Math.max(this.left == null ? 0 : this.left.deepth(),
                    this.right == null ? 0 : this.right.deepth()) + 1;
        }


        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    '}';
        }
    }

}
