package com.musikouyi.jzframe.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 目前的策略：单图片服务器方案.
 *
 * @author JIM
 */
@Component
public class FileUrlHelper {

    private static String fileSystemHost;

    /**
     * 文件系统默认为本地即/开头的地址，如果采用异地图片服务器，请指定站点，例如:http://img.toabao.com
     *
     * @param fileSystemHost
     */
    @Value("${app.fileSystemHost:}")
    public void setFileSystemHost(String fileSystemHost) {
        this.fileSystemHost = fileSystemHost;
    }

    public static String getFileSystemUrl(String filePath) {
        return new StringBuilder(fileSystemHost)
                .append(WebContextHolder.getContextPath())
                .append("/")
                .append(filePath.replaceAll("\\\\", "/"))
                .toString();
    }
}
