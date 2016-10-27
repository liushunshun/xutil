package com.xy;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslHandler;

import javax.net.ssl.SSLEngine;

/**
 * Created by XiuYang on 2016/10/24.
 */
public class SslChannelInitializer extends ChannelInitializer<Channel> {

    private final SslContext context;
    private final boolean startTls;
    private final boolean client;

    public SslChannelInitializer(SslContext context,boolean client,boolean startTls){
        this.context = context;
        this.startTls = startTls;
        this.client = client;
    }
    @Override
    protected void initChannel(Channel channel) throws Exception {
        SSLEngine engine = context.newEngine(channel.alloc());
        engine.setUseClientMode(client);
        channel.pipeline().addFirst("ssl",new SslHandler(engine,startTls));
    }
}
