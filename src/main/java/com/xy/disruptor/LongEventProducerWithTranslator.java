package com.xy.disruptor;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * Created by XiuYang on 2016/9/28.
 */
public class LongEventProducerWithTranslator {
    //一个translator可以看作是一个事件初始化器，publicEvent方法会调用它
    //填充Event
    private static final EventTranslatorOneArg<LongEvent,ByteBuffer> TRANSLATOR =
            (event, sequence, bb) -> event.setValue(bb.getLong(0));

    private final RingBuffer<LongEvent> ringBuffer;

    public LongEventProducerWithTranslator(RingBuffer<LongEvent> ringBuffer){
        this.ringBuffer = ringBuffer;
    }

    public void onData(ByteBuffer bb){
        ringBuffer.publishEvent(TRANSLATOR,bb);
    }

}
