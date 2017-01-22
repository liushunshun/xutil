package com.xy.chat.auth;

import com.google.protobuf.Message;
import com.xy.chat.proto.analisis.ParseMap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by XiuYang on 2016/10/14.
 */
public class PacketEncoder extends MessageToByteEncoder<Message> {

    private static final Logger logger = LogManager.getLogger(PacketEncoder.class);

    @Override
    protected void encode(ChannelHandlerContext ctx, Message msg, ByteBuf out) throws Exception {
        byte[] bytes = msg.toByteArray();// 将对象转换为byte
        int ptoNum = ParseMap.getPtoNum(msg);
        int length = bytes.length;

        /* 加密消息体
        ThreeDES des = ctx.channel().attr(ClientAttr.ENCRYPT).get();
        byte[] encryptByte = des.encrypt(bytes);
        int length = encryptByte.length;*/

        ByteBuf buf = Unpooled.buffer(8 + length);
        buf.writeInt(length);
        buf.writeInt(ptoNum);
        buf.writeBytes(bytes);
        out.writeBytes(buf);

        logger.info("GateServer Send Message, remoteAddress: {}, content length {}, ptoNum: {}", ctx.channel().remoteAddress(), length, ptoNum);

    }
}
