package com.musikouyi.jzframe.service;

import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

public class TestService {

    /**
     * 保存文件，直接以multipartFile形式
     *
     * @param multipartFile
     * @param path          文件保存绝对路径
     * @return 返回文件名
     * @throws IOException
     */
    public static String saveImg(MultipartFile multipartFile, String path) throws IOException {
//        File file = new File(path);
//        if (!file.exists()) {
//            file.mkdirs();
//        }
        FileInputStream fileInputStream = (FileInputStream) multipartFile.getInputStream();
        String fileName = getUUID32() + ".png";
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(ResourceUtils.getURL("classpath:")
                .getPath() + File.separator + "static" + File.separator + "images" + File.separator + fileName));
        byte[] bs = new byte[1024];
        int len;
        while ((len = fileInputStream.read(bs)) != -1) {
            bos.write(bs, 0, len);
        }
        bos.flush();
        bos.close();
        return fileName;
    }

    public static String getUUID32() {
        String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        return uuid;
    }
}
