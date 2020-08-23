package com.string;

import java.util.Arrays;

/**
 * 部分匹配值
 *     字符串前缀集合和后缀集合的交集
 *     部分匹配值就是为交集中的最长的字符串的长度
 *     比如字符串ABCDAB
 *     前缀集合为{A, AB, ABC, ABCD, ABCDA}
 *     后缀集合为{BCDAB, CDAB, DAB, AB, B}
 *     部分匹配表就是  {A, AB, ABC, ABCD, ABCDA} ∩ {BCDAB, CDAB, DAB, AB, B} = { AB }
 *     部分匹配值就是2
 *
 *     以此方法可以求得子串 A, AB, ABC, ABCD, ABCDA的部分匹配值
 *     每个这些子串得索引对应得部分匹配值组成的表就是部分匹配表
 *     它长这个样子
 *       A B C D A B D   字符串
 *       0 1 2 3 4 5 6   索引
 *       0 0 0 0 1 2 0   部分匹配值
 *
 *  KMP算法思想
 *
 *
 */
public class KMP {

    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";

        int i = kmpSearch(str1, str2);
        System.out.println(i);

    }

    /**
     * @param text 文本串
     * @param pattern 模式串
     * @return 如果pattern在text出现过，返回第一次出现的索引，如果没找到返回-1
     */
    public static int kmpSearch(String text, String pattern){
        int[] pmt = KMP.PMT(pattern);

        for (int i = 0, j = 0; i < text.length(); i++) {
            while (j > 0 && text.charAt(i) != pattern.charAt(j)){
                j = pmt[j-1];
            }
            if(text.charAt(i) == pattern.charAt(j)){
                j++;
            }
            if(j == pattern.length()) return i - j + 1;
        }
        return -1;

    }

    //获取部分匹配表 Partial Match Table
    public static int[] PMT(String str){
        //长度为1的字符串，部分匹配值为0
        int PMT[] = new int[str.length()];
        PMT[0] = 0;
        for (int i = 1, j = 0; i < str.length(); i++) {
            if(str.charAt(i) == str.charAt(j)){
                j++;
            } else {
                j = 0;
            }
            PMT[i] = j;
        }
        return PMT;
    }


}
