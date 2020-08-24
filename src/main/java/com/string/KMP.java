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
 *      文本串索引和模式串索引i，j 从0开始
 *      逐个比较文本串i位和模式串j位是否相等
 *      如果在模式串的j位失去匹配。就需要找j位前的字符串的公共前后缀的长度，也就是部分匹配值
 *        由于除开最长公共前后缀的字符一定不是公共前后缀，所以不需要再比较
 *        所以只需要将模式串向后移动 已匹配到的字符长度 - 最长公共前后缀(j - pmt[j-1]) 位
 *        模式串后移。文本串不需要操作； 即将模式串索引向前移动即可
 *        即为: j = j - (j - pmt[j-1]), j = pmt[j -1]
 *
 *
 */
public class KMP {

    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABDE";

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
        int i = 0, j = 0;
        while (i < text.length()){
            if(text.charAt(i) == pattern.charAt(j)){
                i++;
                j++;
            } else if(j == 0){
                i++;
            } else {
                j = pmt[j-1];
            }
            if(j == pattern.length()) return i - j;
        }
        return -1;

    }

    //获取部分匹配表 Partial Match Table
    public static int[] PMT(String str){
        //长度为1的字符串，部分匹配值为0
        int PMT[] = new int[str.length()];
        PMT[0] = 1;
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
