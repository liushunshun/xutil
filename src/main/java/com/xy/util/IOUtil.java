package com.xy.util;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

/**
 * Created by XiuYang on 2016/9/14.
 */
public class IOUtil {

    private static final int OUTPUT_BUFFER_SIZE = 4096;
    //private static final String DEFAULT_CHARSET = "UTF-8";

    /**
     * InputStream转String 完成之后关闭流
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static String InputStream2String(InputStream inputStream,String charset) throws IOException {
        Args.notNull(inputStream,"Input Stream");
        try {
            return IOUtils.toString(inputStream,Charset.forName(charset));
        }finally {
            IOUtils.closeQuietly(inputStream);
        }
    }
    /**
     * input stream 写入 output stream
     * @param inputStream
     * @param outputStream
     * @throws IOException
     */
    public void write(InputStream inputStream, OutputStream outputStream) throws IOException {
        Args.notNull(inputStream,"Input Stream");
        Args.notNull(outputStream,"Output Stream");
        try{
            int l;
            final byte[] tmp = new byte[OUTPUT_BUFFER_SIZE];
            while ((l=inputStream.read(tmp)) != -1){
                outputStream.write(tmp,0,l);
            }
        }finally {
            inputStream.close();
            outputStream.close();
        }
    }
}
