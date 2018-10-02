package com.musikouyi.jzframe.service;

import com.musikouyi.jzframe.domain.entity.Result;

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

    Map<String, String> syncBusinessObject(Integer businessObjectId, Object newEntity, Object savedEntity, Class<?> entityClass);

}
