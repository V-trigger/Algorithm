package com.tree;

import java.util.*;

/**
 *
 * 赫夫曼编码 数据解压
 *
 */
public class HuffmanDecode {

    private String content;

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
        StringBuilder codesStr = byteToBit(codes);
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
        this.content = str.toString();
    }

    /**
     * 获取原始编码二进制字符串
     * @param codes
     * @return
     */
    private StringBuilder byteToBit(byte[] codes){
        StringBuilder codesStr = new StringBuilder();
        int temp;
        boolean isPadding; //是否需要补高位
        String str; //临时存放byte对应的二进制字符串
        for (int i = 0; i < codes.length - 1; i++) {
            temp = codes[i];
            //只有最后一位不需要补高位
            isPadding = i != codes.length - 2;
            /*
             * 判断是否是编码实际内容的最后一个byte
             * 由于编码的最后一个byte存放了最后一个byte高位的0的个数
             * 所以倒数第一个才是实际内容的最后一个byte
             */
            if(isPadding) {
                //按位或1 0000 0000再截取掉低八位，就完成了高位补0
                temp |= 256;
                str = Integer.toBinaryString(temp);
                codesStr.append(str.substring(str.length()-8));
            } else {
                str = Integer.toBinaryString(temp);
                //最后一位不需要补高位,但是需要补上高位出现的0
                String zeroStr = "0".repeat(codes[codes.length-1]);
                codesStr.append(zeroStr);
                if(!"0".equals(str)) codesStr.append(str);

            }
        }
        return codesStr;
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

    public String getContent() {
        return content;
    }
}
