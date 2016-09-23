package com.xy.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by XiuYang on 2016/9/14.
 */
public class IOUtil {

    private static final int OUTPUT_BUFFER_SIZE = 4096;

    /**
     * input stream 写入 output stream
     * @param inputStream
     * @param outputStream
     * @throws IOException
     */
    public void write(InputStream inputStream, OutputStream outputStream) throws IOException {
        Args.notNull(inputStream==null,"Input Stream");
        Args.notNull(outputStream==null,"Output Stream");
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
