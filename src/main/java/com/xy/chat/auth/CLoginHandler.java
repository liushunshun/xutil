package com.xy.chat.auth;

import com.google.protobuf.Message;
import com.xy.chat.proto.Auth;
import com.xy.util.DBOperator;
import io.netty.channel.ChannelHandlerContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.thrift.TException;

/**
 * Created by XiuYang on 2016/10/14.
 */
public class CLoginHandler extends IMHandler{
    private static final Logger logger = LogManager.getLogger(CLoginHandler.class);

    public CLoginHandler(String userid, long netid, Message msg, ChannelHandlerContext ctx) {
        super(userid, netid, msg, ctx);
    }

    @Override
    protected void excute(Worker worker){
        Auth.CLogin msg = (Auth.CLogin)_msg;
        Account account = null;

        if(!jedis.exists(UserUtils.genDBKey(userId))) {
            RouteUtil.sendResponse(Common.ACCOUNT_INEXIST, "Account not exists", netId, userId);
            logger.info("Account not exists, userid: {}", userId);
            return;
        } else {
            byte[] userIdBytes = jedis.hget(UserUtils.genDBKey(userId), UserUtils.userFileds.Account.field);
            try {
                account = DBOperator.Deserialize(new Account(), userIdBytes);
            } catch (TException e) {
                e.printStackTrace();
            }
        }

        if(account.getUserid().equals(userId) && account.getPasswd().equals(msg.getPasswd())) {
            AuthServerHandler.putInUseridMap(userId, netId);
            RouteUtil.sendResponse(Common.VERYFY_PASSED, "Verify passed", netId, userId);
            logger.info("userid: {} verify passed", userId);
        } else {
            RouteUtil.sendResponse(Common.VERYFY_ERROR, "Account not exist or passwd error", netId, userId);
            logger.info("userid: {} verify failed", userId);
            return;
        }
    }
}
