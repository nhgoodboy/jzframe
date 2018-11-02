package com.musikouyi.jzframe.common.queue.handler;

import com.lmax.disruptor.WorkHandler;
import com.musikouyi.jzframe.common.queue.vo.SmallPictEvent;
import com.musikouyi.jzframe.common.queue.vo.SmallPictEventData;
import com.musikouyi.jzframe.dao.repository.SmallPictRepository;
import com.musikouyi.jzframe.domain.entity.SmallPict;
import com.musikouyi.jzframe.utils.SmallPictUtil;
import com.musikouyi.jzframe.utils.SpringContextHolder;
import com.musikouyi.jzframe.utils.WebContextHolder;

import java.io.File;
import java.util.Date;

/**
 * 小图队列消息图片处理器.
 * Create with IDEA
 * DateTime: 2018/10/1 21:17
 * @author YJZ
 */
public class SmallPictEventHandler implements WorkHandler<SmallPictEvent> {

    @Override
    public void onEvent(SmallPictEvent event) {
        try {
            SmallPictEventData smallPictEventData = event.getValue();
            int size = SmallPictUtil.generateSmallPict(smallPictEventData.getWidth(), smallPictEventData.getHeight(), WebContextHolder.getWarPath() + File.separator + smallPictEventData.getFilePath(), smallPictEventData.isInnerCut());
            if (size > -1) {
                SmallPict smallPict = new SmallPict();
                smallPict.setFileInfId(smallPictEventData.getFileInfId());
                smallPict.setSmallPictSetupId(smallPictEventData.getSmallPictSetupId());
                smallPict.setSmallPictWidth(smallPictEventData.getWidth());
                smallPict.setSmallPictHeight(smallPictEventData.getHeight());
                smallPict.setFileSizeKb(size);
                smallPict.setFileTime(new Date());
                SpringContextHolder.getBean(SmallPictRepository.class).saveAndFlush(smallPict);
            }
        } catch (Throwable tr) {
            tr.printStackTrace();
        }
    }
}

