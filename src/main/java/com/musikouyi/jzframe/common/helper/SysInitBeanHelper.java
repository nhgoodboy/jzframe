package com.musikouyi.jzframe.common.helper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * Create with IDEA
 * Author: YJZ
 * DateTime: 2018/10/7 15:34
 **/
@Slf4j
@Component
public class SysInitBeanHelper implements ApplicationRunner {

    private final SmallPictImageQueueHelper smallPictImageQueueHelper;

    @Autowired
    public SysInitBeanHelper(SmallPictImageQueueHelper smallPictImageQueueHelper) {
        this.smallPictImageQueueHelper = smallPictImageQueueHelper;
    }

    @Override
    public void run(ApplicationArguments args) {
        smallPictImageQueueHelper.init(); //初始化小图处理线程
        log.info("系统资源初始化成功");
    }
}
