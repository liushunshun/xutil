package com.xy.chat.auth;

import com.google.protobuf.Message;
import io.netty.channel.ChannelHandlerContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by XiuYang on 2016/10/14.
 */
public class GreetHandler extends IMHandler {

    private static final Logger logger = LogManager.getLogger(GreetHandler.class);

    protected GreetHandler(String userId, long netId, Message msg, ChannelHandlerContext ctx) {
        super(userId, netId, msg, ctx);
    }

    @Override
    protected void excute(Worker worker) {
        AuthServerHandler.setGateAuthConnection(ctx);
        logger.info("[Gate-Auth] connection is established");
    }
}
