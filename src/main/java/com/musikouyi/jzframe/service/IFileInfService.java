package com.musikouyi.jzframe.service;

import com.musikouyi.jzframe.domain.entity.Result;
import com.musikouyi.jzframe.domain.enums.BusinessTypeCodeEnum;
import com.musikouyi.jzframe.dto.FileInfDto;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface IFileInfService {

    /**
     * 保存文件到临时目录
     * @param fileName
     * @param fileStream
     * @return
     */
    Result saveTempFile(String fileName, InputStream fileStream);


    Map<String, String> syncFileInfList(BusinessTypeCodeEnum businessTypeCode, Integer businessObjectId, List<FileInfDto> fileInfBarDtoList);
}
