package com.test.huffman;

import com.tree.HuffmanDecode;
import com.tree.HuffmanEncode;
import org.junit.Test;

import java.io.*;
import java.util.Arrays;
import java.util.Map;

public class HuffmanTest {

    @Test
    public void testEncode(){
        String str = "this is a pen, this is a apple";

        HuffmanEncode huffmanEncode = new HuffmanEncode(str.getBytes());
        byte[] code = huffmanEncode.getCode();
        System.out.println(Arrays.toString(code));
    }

    @Test
    public void testDecode(){
        String str = "i'am a single dog, i want to get a Object";

        HuffmanEncode huffmanEncode = new HuffmanEncode(str.getBytes());
        byte[] code = huffmanEncode.getCode();
        System.out.println(Arrays.toString(code));
        Map<Byte, String> huffmanCodes = huffmanEncode.getHuffmanCodes();

        HuffmanDecode huffmanDecode = new HuffmanDecode(huffmanCodes, code);
        String str1 = new String(huffmanDecode.getBytes());
        System.out.println(str1);
    }

    /**
     * 测试压缩文件
     */
    @Test
    public void compressFile(){
        InputStream fileInputStream = null;
        OutputStream outputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            fileInputStream = new FileInputStream("C:\\Users\\PTR\\Desktop\\compress\\test.jpg");
            byte buff[] = new byte[fileInputStream.available()];
            fileInputStream.read(buff);
            fileInputStream.close();

            HuffmanEncode huffmanEncode = new HuffmanEncode(buff);
            byte[] code = huffmanEncode.getCode();
            Map<Byte, String> huffmanCodes = huffmanEncode.getHuffmanCodes();

            outputStream = new FileOutputStream("C:\\Users\\PTR\\Desktop\\compress\\test.jpg.zip");
            objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(code);
            objectOutputStream.writeObject(huffmanCodes);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
                try {
                    objectOutputStream.close();
                    outputStream.close();
                    fileInputStream.close();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
        }

    }

    /**
     * 测试文件解压
     */
    @Test
    public void uncompressFile(){
        InputStream inputStream = null;
        ObjectInputStream objectInputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = new FileInputStream("C:\\Users\\PTR\\Desktop\\compress\\test.jpg.zip");
            objectInputStream = new ObjectInputStream(inputStream);

            byte[] codes = (byte[]) objectInputStream.readObject();
            Map<Byte, String> huffmanCode = (Map<Byte, String>) objectInputStream.readObject();

            HuffmanDecode huffmanDecode = new HuffmanDecode(huffmanCode, codes);
            byte buff[] = huffmanDecode.getBytes();

            outputStream = new FileOutputStream("C:\\Users\\PTR\\Desktop\\compress\\test1.jpg");
            outputStream.write(buff);
        } catch (Exception e){
            System.out.println(e.getMessage());
        } finally {
            try {
                outputStream.close();
                objectInputStream.close();
                inputStream.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

}
