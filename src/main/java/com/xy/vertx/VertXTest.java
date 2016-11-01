package com.xy.vertx;

import io.netty.handler.codec.sctp.SctpOutboundByteStreamHandler;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.file.AsyncFile;
import io.vertx.core.file.FileSystem;
import io.vertx.core.file.OpenOptions;

import java.io.UnsupportedEncodingException;

/**
 * Created by XiuYang on 2016/10/28.
 */
public class VertXTest {
    public static void main(String[] args) {
        VertxOptions options = new VertxOptions().setWorkerPoolSize(40);
        Vertx vertx = Vertx.vertx(options);

        FileSystem fileSystem = vertx.fileSystem();
        fileSystem.readFile("d:\\aa.txt",result->{
           if(result.succeeded()){
               Buffer buffer = result.result();
               System.out.println(buffer.toString("GBK"));
           }else{
               System.err.println("Oh oh ..." + result.cause());
           }
        });
        OpenOptions openOptions = new OpenOptions();
        fileSystem.open("d:\\aa.txt",openOptions,result->{
           if(result.succeeded()){
               AsyncFile asyncFile = result.result();
               Buffer buffer = Buffer.buffer(1024);
               asyncFile.read(buffer,0,0,1024,res->{
                  if(res.succeeded()){
                      System.out.println(res.result().toString("GBK"));
                  }
               });
           }
        });
        //vertx.close();
    }
}
