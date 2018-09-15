package com.musikouyi.jzframe.controller;

import com.musikouyi.jzframe.domain.entity.TestEntity;
import com.musikouyi.jzframe.service.TestService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/miniprogram")
public class TestController {

    @GetMapping("/coming_soon")
    public TestEntity datas() {
        System.out.println("adsf");
        TestEntity testEntity = new TestEntity(1, "A", "/images/test-copy.png");
        return testEntity;
    }

    @GetMapping("/index")
    public String index() {
        System.out.println("start");
        return "index";
    }

    @PostMapping("/upload")
    public String uploadImg(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        return TestService.saveImg(multipartFile, "static/images");

//        if (multipartFile.isEmpty() || StringUtils.isBlank(multipartFile.getOriginalFilename())) {
//            throw new BusinessException(ResultEnum.IMG_NOT_EMPTY);
//        }
//        String contentType = multipartFile.getContentType();
//        if (!contentType.contains("")) {
//            throw new BusinessException(ResultEnum.IMG_FORMAT_ERROR);
//        }
//        String root_fileName = multipartFile.getOriginalFilename();
//        logger.info("上传图片:name={},type={}", root_fileName, contentType);
        //处理图片
//        User currentUser = userService.getCurrentUser();
        //获取路径
//        String return_path = ImageUtil.getFilePath(currentUser);
//        String filePath = location + return_path;
//        logger.info("图片保存路径={}", filePath);
//        String file_name = null;
//        try {
//            file_name = TestService.saveImg(multipartFile, filePath);
//            MarkDVo markDVo = new MarkDVo();
//            markDVo.setSuccess(0);
//            if(StringUtils.isNotBlank(file_name)){
//                markDVo.setSuccess(1);
//                markDVo.setMessage("上传成功");
//                markDVo.setUrl(return_path+File.separator+file_name);
//                markDVo.setCallback(callback);
//            }
//            logger.info("返回值：{}",markDVo);
//            return markDVo;
//        } catch (IOException e) {
//            throw new BusinessException(ResultEnum.SAVE_IMG_ERROE);
//        }
    }
}
