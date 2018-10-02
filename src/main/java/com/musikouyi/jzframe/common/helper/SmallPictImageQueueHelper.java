package com.musikouyi.jzframe.common.helper;

import com.musikouyi.jzframe.common.queue.handler.SmallPictEventHandler;
import com.musikouyi.jzframe.common.queue.vo.SmallPictEvent;
import com.musikouyi.jzframe.common.queue.vo.SmallPictEventData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Create with IDEA
 * Author: YJZ
 * DateTime: 2018/10/1 21:14
 **/
@Component
public class SmallPictImageQueueHelper extends BaseQueueHelper<SmallPictEventData, SmallPictEvent, SmallPictEventHandler> {

    private static final int QUEUE_SIZE = 1024;

    private int threadNum;

    @Value("${app.queue.smallPict.threadNum:1}") //默认只要单线程写入小图
    public void setThreadNum(int threadNum) {
        this.threadNum = threadNum;
    }

    @Override
    protected int getThreadNum() {
        return threadNum;
    }

    @Override
    protected int getQueueSize() {
        return QUEUE_SIZE;
    }
}