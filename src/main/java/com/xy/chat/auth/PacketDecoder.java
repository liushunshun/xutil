package com.xy.chat.auth;

import com.google.protobuf.Message;
import com.xy.chat.proto.analisis.ParseMap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Created by XiuYang on 2016/10/14.
 */
public class PacketDecoder extends ByteToMessageDecoder {

    private static final Logger logger = LogManager.getLogger(PacketDecoder.class);

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> list) throws Exception {
        byteBuf.markReaderIndex();

        if(byteBuf.readableBytes()<4){
            logger.info("readableBytes length less than 4 bytes, ignored");
            byteBuf.resetReaderIndex();
            return;
        }

        int length = byteBuf.readInt();

        if(length<0){
            ctx.close();
            logger.error("message length less than 0, channel closed");
            return;
        }

        if(length > byteBuf.readableBytes() - 4){
            //注意！编解码器加这种in.readInt()日志，在大并发的情况下很可能会抛数组越界异常！
            //logger.error("message received is incomplete,ptoNum:{}, length:{}, readable:{}", in.readInt(), length, in.readableBytes());
            byteBuf.resetReaderIndex();
            return;
        }

        int ptoNum = byteBuf.readInt();

        ByteBuf byteBuf1 = Unpooled.buffer(length);

        byteBuf.readBytes(byteBuf1);

        try{
            /* 解密消息体
            ThreeDES des = ctx.channel().attr(ClientAttr.ENCRYPT).get();
            byte[] bareByte = des.decrypt(inByte);*/

            byte[] body = byteBuf.array();

            Message msg = ParseMap.getMessage(ptoNum,body);

            list.add(msg);

            logger.info("GateServer Received Message: content length {}, ptoNum: {}", length, ptoNum);
        }catch (Exception e){
            logger.error(ctx.channel().remoteAddress() + ",decode failed.", e);
        }

    }
}
