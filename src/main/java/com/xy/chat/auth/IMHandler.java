package com.xy.chat.auth;

import com.google.protobuf.Message;
import io.netty.channel.ChannelHandlerContext;
import redis.clients.jedis.Jedis;

/**
 * Created by XiuYang on 2016/10/13.
 */
public abstract class IMHandler {
    protected final String userId;
    protected final long netId;
    protected final Message _msg;
    protected ChannelHandlerContext ctx;
    protected Jedis jedis;

    protected IMHandler(String userId,long netId,Message msg,ChannelHandlerContext ctx){
        this.userId = userId;
        this.netId = netId;
        this._msg = msg;
        this.ctx = ctx;
    }

    protected abstract void excute(Worker worker);
}
