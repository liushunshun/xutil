package com.xy.disruptor;

import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * Created by XiuYang on 2016/9/28.
 */
public class LongEventProducer {
    private final RingBuffer<LongEvent> ringBuffer;

    public LongEventProducer(RingBuffer<LongEvent> ringBuffer){
        this.ringBuffer = ringBuffer;
    }

    /**
     * onData用来发布事件，每次调用就发布一次事件
     * 它的参数会通过事件传递给消费者
     * @param bb
     */
    public void onData(ByteBuffer bb){
        //可以把ringBuffer看作一个事件队列，那么next就是得到下面一个事件槽
        long sequence = ringBuffer.next();
        try{
            //用上面的索引取出一个空的事件用于填充
            LongEvent event = ringBuffer.get(sequence);
            event.setValue(bb.getLong(0));
        }finally {
            //发布事件
            ringBuffer.publish(sequence);
        }
    }
}
