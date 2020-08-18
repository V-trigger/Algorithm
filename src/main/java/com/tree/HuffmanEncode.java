package com.tree;


import java.util.*;

/**
 *
 * 赫夫曼编码:
 *     Huffman于1952年提出一种编码方法，该方法完全依据字符出现概率来构造异字头的平均长度最短的码字，有时称之为最佳编码
 *
 * 过程:
 *     比如对"this is a pen, this is a apple"进行编码
 *     第一步: 找出所有字符出现的次数
 *         t => 2; h => 2; i => 4; s => 4; 空格 => 7
 *         a => 3; p => 3; e => 2; n => 1; , => 1; l => 1
 *     第二步: 将出现次数作为权值构建赫夫曼编码
 *
 *     第三步: 根据路径给各个字符编码,往左找一下为0,往右找一下为1
 *             此时每个字符的编码就是赫夫曼编码
 *  赫夫曼编码是一个前缀编码，每个字符的编码都不是其他的字符编码的前缀
 *
 *  ps: 赫夫曼树根据赫夫曼树构建时的排序规则不同，对应的赫夫曼树叶不同，这样赫夫曼编码就不一样
 *      但是wpl一定是样的
 *
 *  数据压缩:
 *      将字符串的每个字符转对应的赫夫曼编码
 *      将字符串每8位转成一个byte,放入到byte数组中
 *      这个byte数组就是压缩后的字符串
 *
 */
public class HuffmanEncode {

    //赫夫曼树的根节点
    private Node root;

    //字符对应的节点
    private List<Node> nodes;

    //字符对应的赫夫曼编码
    private Map<Character, String> huffmanCodes = new HashMap<>();

    //待编码的字符数组
    private char[] chars;

    //赫夫曼编码
    private byte[] code;

    public HuffmanEncode(String value){
        encode(value);
    }

    //进行编码
    private void encode(String value){
        chars = value.toCharArray();
        //根据字符构建森林
        buildNodeList();
        //构建赫夫曼树
        buildHuffmanTree();
        //根据赫夫曼树构建字符串的赫夫曼编码
        encode(root.left, "0", new StringBuilder());
        encode(root.right, "1", new StringBuilder());
        //根据赫夫曼编码压缩字符串
        compress();
    }


    /**
     * 根据编码压缩字符串,每8位一个byte
     * @return
     */
    private void compress(){
        StringBuilder codesStr = new StringBuilder();
        for(char c : chars){
            codesStr.append(huffmanCodes.get(c));
        }

        byte code[] = new byte[(codesStr.length() + 7) / 8];
        int offset = 0;
        String codeStr;
        for (int i = 0; i < codesStr.length(); i+=8) {
            codeStr = i+8 > codesStr.length() ? codesStr.substring(i) : codesStr.substring(i,i+8);
            code[offset] = (byte) Integer.parseInt(codeStr, 2);
            offset++;
        }
        this.code = code;
    }

    /**
     * 对节点进行赫夫曼编码
     * @param node
     * @param code
     * @param path
     */
    private void encode(Node node, String code, StringBuilder path){
        StringBuilder currentPath = new StringBuilder(path);
        currentPath.append(code);
        if(node != null && node.data == null){
            //非叶子节点继续往下找
            encode(node.left, "0", currentPath);
            encode(node.right, "1", currentPath);
        } else {
            //叶子节点
            huffmanCodes.put(node.data, currentPath.toString());
        }
    }

    /**
     * 构建赫夫曼树
     * @return
     */
    private void buildHuffmanTree(){
        Node left;
        Node right;
        Node root;
        while (nodes.size() > 1){
            Collections.sort(nodes);
            left = nodes.get(0);
            right = nodes.get(1);
            root = new Node(left.weight + right.weight, null);
            root.setLeft(left);
            root.setRight(right);
            nodes.remove(left);
            nodes.remove(right);
            nodes.add(root);
        }
        setRoot(nodes.get(0));
    }

    /**
     * 构建森林
     * 生成每个字符的节点,权值为字符出现的次数
     * @return
     */
    private void buildNodeList(){
        List<Node> nodes = new ArrayList<>();
        Map<Character, Integer> map = new HashMap<>();
        //获取每个字符出现的次数
        for (char c : chars){
            if(map.containsKey(c)){
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }
        //生成node并放入list
        for(Map.Entry<Character, Integer> entry : map.entrySet()){
            nodes.add(new Node(entry.getValue(), entry.getKey()));
        }

        this.nodes = nodes;
    }

    public Map<Character, String> getHuffmanCodes() {
        return huffmanCodes;
    }

    public byte[] getCode() {
        return code;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    class Node implements Comparable<Node>{
        private int weight; //权值

        private Character data;  //字符

        private Node left;  //左子节点

        private Node right; //右子节点

        public Node(int weight, Character data) {
            this.weight = weight;
            this.data = data;
        }


        public void setLeft(Node left) {
            this.left = left;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "weight=" + weight +
                    ", data=" + data +
                    '}';
        }
    }


}
