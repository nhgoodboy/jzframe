package com.musikouyi.jzframe.dto;


import com.musikouyi.jzframe.domain.entity.FileInf;
import lombok.Data;

/**
 * 文件资源对应数据结构.
 *
 * @author JIM
 * @see FileInf
 */
@Data
public class FileInfDto {

    public static final String FILE_URL = "fileUrl";

    private Integer fileInfId;
    private String fileNm;
    private String fileTypeNm; //扩展名决定了显示图标
    private String filePath;
    private String fileUrl;
}
