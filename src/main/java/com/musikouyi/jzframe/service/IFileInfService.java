package com.musikouyi.jzframe.service;

import com.musikouyi.jzframe.domain.entity.Result;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

public interface IFileInfService {

    /**
     * 保存文件到临时目录
     *
     * @param fileName
     * @param fileStream
     * @return
     */
    Result saveTempFile(String fileName, InputStream fileStream);

    /**
     * 根据对象来同步更新所有的文件字段，包括图片ID集合的字段，图片ID字段。返回所有要更新的URL地址
     *
     * @param newEntity
     * @param savedEntity
     * @param entityClass
     * @return replaceMap
     */
    Map<String, String> syncBusinessObject(Integer businessObjectId, Object newEntity, Object savedEntity, Class<?> entityClass) throws FileNotFoundException;

    /**
     * 如果图片ID为空或没有该记录，则返回默认的指定大小的图片.
     * 默认图片放到/war/smallpict/default/common下，以 长x宽 的格式存放，例如100x100.jpg
     *
     * @param fileInfId
     * @param width
     * @param height
     * @return
     */
    String getSmallPictUrl(Integer fileInfId, int width, int height);
}
