package com.musikouyi.jzframe.controller;

import com.musikouyi.jzframe.common.constant.ControllerMapping;
import com.musikouyi.jzframe.domain.entity.Result;
import com.musikouyi.jzframe.service.IFileInfService;
import com.musikouyi.jzframe.utils.SpringContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(ControllerMapping.ADMIN_FILE_INF_BASE)
public class FileInfController {

    @PostMapping(ControllerMapping.UPLOAD)
    public Result upload(@RequestParam("file") MultipartFile file) throws IOException {
        return SpringContextHolder.getBean(IFileInfService.class).saveTempFile(file.getOriginalFilename(), file.getInputStream());
    }
}
