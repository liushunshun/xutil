package com.xy.chat.auth;

import com.google.protobuf.Message;
import com.xy.chat.proto.Internal;
import com.xy.chat.proto.analisis.ParseMap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;

/**
 * Created by XiuYang on 2016/10/13.
 */
public class AuthServerHandler extends SimpleChannelInboundHandler<Message>{
    private static final Logger logger = LogManager.getLogger(AuthServerHandler.class);
    private static ChannelHandlerContext gateAuthConnection;

    private static HashMap<String, Long> userid2netidMap = new HashMap<>();

    public static ChannelHandlerContext getGateAuthConnection() {
        if(gateAuthConnection!=null){
            return gateAuthConnection;
        }else{
            return null;
        }
    }

    public static void setGateAuthConnection(ChannelHandlerContext gateAuthConnection) {
        AuthServerHandler.gateAuthConnection = gateAuthConnection;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Message message) throws Exception {
        Internal.GTransfer gt = (Internal.GTransfer)message;
        int ptoNum = gt.getPtoNum();
        Message msg = ParseMap.getMessage(ptoNum,gt.getMsg().toByteArray());

        IMHandler handler;
        if(msg instanceof Internal.Greet){
            handler = HandlerManager.getHandler(ptoNum,gt.getUserId(),gt.getNetId(),msg,channelHandlerContext);
        }else{
            handler = HandlerManager.getHandler(ptoNum,gt.getUserId(),gt.getNetId(),msg,getGateAuthConnection());
        }
        Worker.dispath(gt.getUserId(),handler);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        // super.exceptionCaught(ctx, cause);
        logger.error("An Exception Caught");
    }
    public static void putInUseridMap(String userid, Long netId) {
        userid2netidMap.put(userid, netId);
    }

}
