package com.musikouyi.jzframe.common.queue.handler;

import com.lmax.disruptor.WorkHandler;
import com.musikouyi.jzframe.common.queue.vo.SmallPictEvent;
import com.musikouyi.jzframe.common.queue.vo.SmallPictEventData;
import com.musikouyi.jzframe.domain.entity.SmallPict;
import com.musikouyi.jzframe.repository.SmallPictRepository;
import com.musikouyi.jzframe.utils.SmallPictUtil;
import com.musikouyi.jzframe.utils.WebContextHolder;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.Date;

/**
 * Create with IDEA
 * Author: YJZ
 * DateTime: 2018/10/1 21:17
 **/

/**
 * 小图队列消息图片处理器.
 *
 * @author JIM
 */
public class SmallPictEventHandler implements WorkHandler<SmallPictEvent> {

    @Autowired
    private SmallPictRepository smallPictRepository;

    @Override
    public void onEvent(SmallPictEvent event) throws Exception {
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
                smallPictRepository.save(smallPict);
            }
        } catch (Throwable tr) {
            tr.printStackTrace();
        }
    }
}

