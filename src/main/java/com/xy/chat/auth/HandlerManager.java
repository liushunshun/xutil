package com.xy.chat.auth;

import com.google.protobuf.Message;
import com.xy.chat.proto.Internal;
import com.xy.chat.proto.analisis.ParseMap;
import io.netty.channel.ChannelHandlerContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by XiuYang on 2016/10/13.
 */
public class HandlerManager {
    private static Logger logger = LogManager.getLogger(HandlerManager.class);

    private static final Map<Integer,Constructor<? extends IMHandler>> handlers = new HashMap<>();

    public static void register(Class<? extends Message> msg, Class<? extends IMHandler> handler){
        int num = ParseMap.getPtoNum(msg);
        try {
            Constructor<? extends IMHandler> constructor = handler.getConstructor(String.class,long.class,Message.class, ChannelHandlerContext.class);
            handlers.put(num,constructor);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public static IMHandler getHandler(int msgNum,String userId,long netId,Message msg,ChannelHandlerContext ctx)
        throws IllegalAccessException,InvocationTargetException,InstantiationException{
        Constructor<? extends IMHandler> constructor = handlers.get(msgNum);
        if(constructor==null){
            logger.error("handler not exist, Message Number: {}", msgNum);
            return null;
        }
        return constructor.newInstance(userId,netId,msg,ctx);
    }

    public static void initHandlers(){
        HandlerManager.register(Internal.Greet.class,GreetHandler.class);
    }
}
