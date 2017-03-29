package com.xy.util;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by XiuYang on 2016/11/1.
 */
public class FileUtil {

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
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath), Charset.forName(charset),append?APPEND:TRUNCATE_EXISTING,CREATE)){
            writer.write(s);
        }
    }

    /**
     * 读文件，默认字符集 UTF-8
     * @param filePath
     * @return
     * @throws IOException
     */
    public static List<String> read(String filePath) throws IOException {
        return read(Paths.get(filePath),DEFAULT_CHARSET);
    }
    /**
     * 读文件，默认字符集 UTF-8
     * @param filePath
     * @return
     * @throws IOException
     * @return List<String>
     * @exception
     * @createTime：2017年3月28日
     * @author: liuss
     */
    public static List<String> read(Path filePath) throws IOException {
        return read(filePath,DEFAULT_CHARSET);
    }
    

    /**
     * 读文件
     * @param filePath
     * @param charset
     * @return
     * @throws IOException
     */
    public static List<String> read(Path filePath,String charset) throws IOException {
        try(BufferedReader reader = Files.newBufferedReader(filePath,Charset.forName(charset))){
            return reader.lines().collect(Collectors.toList());
        }
    }
}
