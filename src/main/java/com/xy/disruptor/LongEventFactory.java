package com.xy.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * Created by XiuYang on 2016/9/28.
 */
public class LongEventFactory implements EventFactory {
    @Override
    public Object newInstance() {
        return new LongEvent();
    }
}
