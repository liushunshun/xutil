package com.xy.pattern.callback;

/**
 * Created by XiuYang on 2016/10/20.
 */
public class Teacher{
    public void ask(ICallback iCallback,Integer num){
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        iCallback.sove(num+1+"");
    }

}
