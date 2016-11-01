package com.xy.file;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.*;

/**
 * Created by XiuYang on 2016/11/1.
 */
public class WriteFile {

    private static final String DEFAULT_CHARSET = "UTF-8";

    /**
     * 写文件，默认字符集UTF-8、追加写
     * @param filePath
     * @param s
     * @throws IOException
     */
    public static void writeString(String filePath,String s) throws IOException {
        writeString(filePath,s,DEFAULT_CHARSET,true);
    }

    /**
     * 写文件，默认字符集UTF-8
     * @param filePath
     * @param s
     * @param append 是否追加写
     * @throws IOException
     */
    public static void writeString(String filePath,String s,boolean append) throws IOException {
        writeString(filePath,s,DEFAULT_CHARSET,true);
    }

    /**
     * 写文件
     * @param filePath
     * @param s
     * @param charset 字符集
     * @param append 是否追加写
     * @throws IOException
     */
    public static void writeString(String filePath,String s,String charset,boolean append) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath),Charset.forName(charset),append?APPEND:TRUNCATE_EXISTING,CREATE)){
            writer.write(s);
        }
    }
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        String s = "hello world hello world hello world hello world hello world hello world 这些事中文 \r\n";
        String pathString = "E:\\test.txt";
        try {
            writeString(pathString,s,DEFAULT_CHARSET,false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(System.currentTimeMillis() -start);
    }
}
