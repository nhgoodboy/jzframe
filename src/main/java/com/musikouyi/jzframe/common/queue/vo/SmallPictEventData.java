package com.musikouyi.jzframe.common.queue.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Create with IDEA
 * Author: YJZ
 * DateTime: 2018/10/1 21:13
 **/
@Data
@AllArgsConstructor
public class SmallPictEventData {

    private int smallPictSetupId;

    private int fileInfId;

    private String filePath;

    private int width;

    private int height;

    /**
     * 是否内裁切，否的话为外裁切
     */
    private boolean innerCut;
}
