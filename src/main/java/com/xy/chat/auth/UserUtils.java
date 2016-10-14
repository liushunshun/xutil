package com.xy.chat.auth;

import java.nio.charset.Charset;

/**
 * Created by XiuYang on 2016/10/14.
 */
public class UserUtils {
    private static Charset charset = Charset.forName("UTF-8");
    public static enum userFileds {
        Account;

        public final byte[] field;

        private userFileds() {this.field = this.name().toLowerCase().getBytes(Charset.forName("UTF-8"));}
    }

    public static byte[] genDBKey(String userId) {
        StringBuilder sb = new StringBuilder();
        sb.append("userId={");
        sb.append(userId);
        sb.append("}");

        return sb.toString().getBytes(charset);
    }

    public static void main(String[] args) {
        System.out.println(userFileds.Account.name());
    }
}
