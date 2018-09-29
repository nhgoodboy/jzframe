package com.musikouyi.jzframe.service.impl;

import com.musikouyi.jzframe.common.constant.Global;
import com.musikouyi.jzframe.domain.entity.Result;
import com.musikouyi.jzframe.dto.FileInfDto;
import com.musikouyi.jzframe.service.IFileInfService;
import com.musikouyi.jzframe.utils.ResultUtil;
import org.apache.commons.io.FilenameUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.UUID;

@Service
public class FileInfServiceImpl implements IFileInfService {

    @Override
    @Transactional
    public Result saveTempFile(String fileName, InputStream fileStream) {
        FileOutputStream fileOutputStream = null;
        try {
            String fileTypeNm = FilenameUtils.getExtension(fileName);
            String fileUUID = UUID.randomUUID().toString();
            String filePath = Global.TEMP_DIR + File.separator + fileUUID + "." + fileTypeNm;
            fileOutputStream = new FileOutputStream(ResourceUtils.getURL(Global.CLASSPATH).getPath() + Global.IMAGES_DIR + filePath);
            IOUtils.copy(fileStream, fileOutputStream);
            FileInfDto fileInfDto = new FileInfDto();
            fileInfDto.setFileNm(fileName);
            fileInfDto.setFilePath(filePath);
            fileInfDto.setFileTypeNm(fileTypeNm);
            fileInfDto.setFileUrl(ResourceUtils.getURL(Global.CLASSPATH).getPath() + Global.IMAGES_DIR + fileUUID + "." + fileTypeNm);
            return ResultUtil.success(fileInfDto);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } finally {
            if (fileOutputStream != null) {
                IOUtils.closeQuietly(fileOutputStream);
            }
        }
    }
}
