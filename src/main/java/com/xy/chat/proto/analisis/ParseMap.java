package com.xy.chat.proto.analisis;

import com.google.protobuf.Message;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by XiuYang on 2016/10/13.
 */
public class ParseMap {
    private static final Logger logger = LogManager.getLogger(ParseMap.class);

    @FunctionalInterface
    public interface Parsing{
        Message process(byte[] bytes) throws IOException;
    }

    public static HashMap<Integer,ParseMap.Parsing> parseMap = new HashMap<>();
    public static HashMap<Class<?>,Integer> msg2PtoNum = new HashMap<>();

    public static void register(int ptoNum,ParseMap.Parsing parse,Class<?> cla){
        if(parseMap.get(ptoNum)==null){
            parseMap.put(ptoNum,parse);
        }else{
            logger.error("pto has been registered in parseMap, ptoNum: {}", ptoNum);
            return;
        }
        if(msg2PtoNum.get(cla)==null){
            msg2PtoNum.put(cla,ptoNum);
        }else {
            logger.error("pto has been registered in msg2ptoNum, ptoNum: {}", ptoNum);
            return;
        }
    }

    public static Message getMessage(int ptoNum,byte[] bytes) throws IOException{
        Parsing parser = parseMap.get(ptoNum);
        if(parser == null){
            logger.error("UnKnown Protocol Num：{}",ptoNum);
        }
        Message msg = parser.process(bytes);

        return msg;
    }

    public static Integer getPtoNum(Message msg){
        return getPtoNum(msg.getClass());
    }
    public static Integer getPtoNum(Class<?> cla){
        return msg2PtoNum.get(cla);
    }
}
