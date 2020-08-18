package com.test.huffman;

import com.tree.HuffmanDecode;
import com.tree.HuffmanEncode;
import org.junit.Test;

import java.util.Arrays;
import java.util.Map;

public class HuffmanTest {

    @Test
    public void testEncode(){
        String str = "this is a pen, this is a apple";

        HuffmanEncode huffmanEncode = new HuffmanEncode(str);
        byte[] code = huffmanEncode.getCode();
        System.out.println(Arrays.toString(code));
    }

    @Test
    public void testDecode(){
        String str = "this is a pen, this is a apple";

        HuffmanEncode huffmanEncode = new HuffmanEncode(str);
        byte[] code = huffmanEncode.getCode();
        Map<Character, String> huffmanCodes = huffmanEncode.getHuffmanCodes();

        HuffmanDecode huffmanDecode = new HuffmanDecode(huffmanCodes, code);
        String str1 = huffmanDecode.getStr();
        System.out.println(str1);
    }

}
