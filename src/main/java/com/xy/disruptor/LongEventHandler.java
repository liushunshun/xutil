package com.xy.disruptor;

import com.lmax.disruptor.EventHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by XiuYang on 2016/9/28.
 */
public class LongEventHandler implements EventHandler<LongEvent> {

    Logger logger = LogManager.getLogger(LongEventHandler.class);
    @Override
    public void onEvent(LongEvent longEvent, long l, boolean b) throws Exception {
        logger.info(longEvent.getValue());
    }
}
