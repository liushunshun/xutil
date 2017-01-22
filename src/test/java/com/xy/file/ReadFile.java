package com.xy.file;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by XiuYang on 2016/11/1.
 */
public class ReadFile {

    private static final String DEFAULT_CHARSET = "UTF-8";

    /**
     * 读文件，默认字符集 UTF-8
     * @param filePath
     * @return
     * @throws IOException
     */
    public static List<String> read(String filePath) throws IOException {
        return read(filePath,DEFAULT_CHARSET);
    }

    /**
     * 读文件
     * @param filePath
     * @param charset
     * @return
     * @throws IOException
     */
    public static List<String> read(String filePath,String charset) throws IOException {
        try(BufferedReader reader = Files.newBufferedReader(Paths.get(filePath),Charset.forName(charset))){
            return reader.lines().collect(Collectors.toList());
        }
    }
    public static void main(String[] args) throws IOException {
        read("E:\\NlpAnalysisTest.txt").forEach(System.out::println);
    }
}
