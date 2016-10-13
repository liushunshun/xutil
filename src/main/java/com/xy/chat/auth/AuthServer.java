package com.xy.chat.auth;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.InetSocketAddress;

/**
 * Created by XiuYang on 2016/10/13.
 */
public class AuthServer {
    private static final Logger logger = LogManager.getLogger(AuthServer.class);

    public static void startAuthServer(int port){
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();

        ServerBootstrap bootstrap = new ServerBootstrap()
                .group(bossGroup,workGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel channel) throws Exception {
                        ChannelPipeline pipeline = channel.pipeline();
                        //pipeline.addLast("MessageDecoder",new PacketDecoder());
                        //pipeline.addLast("MessageEncoder",new PacketEncoder());
                        //pipeline.addLast("AuthServerHandler",new AuthServerHandler());
                    }
                });

        bindConnectionOptions(bootstrap);

        bootstrap.bind(new InetSocketAddress(port)).addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                if(channelFuture.isSuccess()){
                    //ParseRegistryMap.initRegistry();
                    //HandlerManager.initHandlers();
                    logger.info("[AuthServer] Started Successed, waiting for other server connect...");
                }else{
                    logger.error("[AuthServer] Started Failed");
                }
            }
        });
    }

    protected static void bindConnectionOptions(ServerBootstrap bootstrap){
        /**
         * BACKLOG用于构造服务端套接字ServerSocket对象，标识当服务器请求处理线程全满时，
         * 用于临时存放已完成三次握手的请求的队列的最大长度。如果未设置或所设置的值小于1，Java将使用默认值50
         */
        bootstrap.option(ChannelOption.SO_BACKLOG,1024);
        /**
         * SO_LINGER选项用来设置延迟关闭的时间，等待套接字发送缓冲区中的数据发送完成。没有设置该选项时，在调用close()后，
         * 在发送完FIN后会立即进行一些清理工作并返回。如果设置了SO_LINGER选项，并且等待时间为正值，则在清理之前会等待一段时间。
         */
        bootstrap.childOption(ChannelOption.SO_LINGER,0);

        bootstrap.childOption(ChannelOption.TCP_NODELAY, true);

        /**
         * 设置套接字选项为SO_REUSEADDR，socket可重用，经常在socket通信时进行设置。
         */
        bootstrap.childOption(ChannelOption.SO_REUSEADDR,true);


        bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true); //心跳机制暂时使用TCP选项，之后再自己实现
    }
}
