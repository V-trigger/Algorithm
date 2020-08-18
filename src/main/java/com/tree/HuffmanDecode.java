package com.tree;

import java.util.*;

/**
 *
 * 赫夫曼编码 数据解压
 *
 */
public class HuffmanDecode {

    public static void main(String[] args) {
        String str = "this is a pen, this is a apple";

        HuffmanEncode huffmanEncode = new HuffmanEncode(str);
        byte[] code = huffmanEncode.getCode();
        System.out.println(Arrays.toString(code));
        Map<Character, String> huffmanCodes = huffmanEncode.getHuffmanCodes();

        HuffmanDecode huffmanDecode = new HuffmanDecode(huffmanCodes, code);
        String str1 = huffmanDecode.getStr();
        System.out.println(str);
    }

    private String str;

    public HuffmanDecode(Map<Character, String> huffmanCodes, byte[] codes){
        decode(huffmanCodes, codes);
    }

    /**
     *
     * @param huffmanCodes 赫夫曼编码表
     * @param codes 赫夫曼编码
     * @return
     */
    private void decode(Map<Character, String> huffmanCodes, byte[] codes){
        //获取原始编码字符串
        StringBuilder codesStr = new StringBuilder();
        for (int i = 0; i < codes.length; i++) {
            codesStr.append(byteToBit(codes[i], i != codes.length -1));
        }
        //编码表 编码 -> 字符
        Map<String, Character> map = new HashMap<>();
        for(Map.Entry<Character, String> entry : huffmanCodes.entrySet()){
            map.put(entry.getValue(), entry.getKey());
        }

        List<Character> list = new ArrayList<>();
        StringBuilder str = new StringBuilder();
        int offset;
        for (int i = 0; i < codesStr.length();) {
            offset = 1;
            Character character;
            while (true){
                character = map.get(codesStr.substring(i, i+offset));
                if(character != null) break;
                offset++;
            }
            list.add(character);
            str.append(character);
            i += offset;
        }
        this.str = str.toString();
    }


    /**
     * byte 转二进制
     * @param b
     * @return
     */
    private String byteToBit(byte b, boolean isPadding){
        int temp = b;

        if(isPadding)  temp |= 256;

        String str = Integer.toBinaryString(temp);

        if(isPadding) return str.substring(str.length()-8);
        return str;
    }

    public String getStr() {
        return str;
    }
}
