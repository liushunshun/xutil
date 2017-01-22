package com.xy.pattern.observer;

import java.util.Observable;

/**
 * Created by XiuYang on 2016/9/26.
 */
public class HanFeiZi extends Observable{
    public void haveBrekfast() {
        System.out.println("韩非子在吃饭。。。");
        this.setChanged();
        this.notifyObservers("韩非子在吃饭");
    }

    public void haveHan() {
        System.out.println("韩非子在玩耍。。。");
        this.setChanged();
        this.notifyObservers("韩非子在玩耍");
    }
}
