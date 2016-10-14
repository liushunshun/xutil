package com.xy.chat.auth;

import com.xy.chat.proto.Auth;
import com.xy.chat.proto.Internal;
import io.netty.buffer.ByteBuf;

/**
 * Created by XiuYang on 2016/10/14.
 */
public class RouteUtil {
    public static void sendResponse(int code, String desc, long netId,String userId) {
        Auth.SResponse.Builder sb = Auth.SResponse.newBuilder();
        sb.setCode(code);
        sb.setDesc(desc);

        ByteBuf byteBuf = Utils.pack2Server(sb.build(), ParseRegistryMap.SRESPONSE, netId, Internal.Dest.Client, userId);
        AuthServerHandler.getGateAuthConnection().writeAndFlush(byteBuf);
    }
}
