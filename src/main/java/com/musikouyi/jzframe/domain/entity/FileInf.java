package com.musikouyi.jzframe.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "sys_file_inf")
public class FileInf implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String FILE_INF_ID = "fileInfId";
    public static final String FILE_PATH = "filePath";
    public static final String BUSINESS_CLASS_NM = "businessClassNm";
    public static final String BUSINESS_OBJECT_ID = "businessObjectId";
    public static final String IF_PICT = "ifPict"; //是否图片决定了是否生成小图
    public static final String FILE_NM = "fileNm";
    public static final String FILE_TYPE_NM = "fileTypeNm";
    public static final String FILE_SIZE_KB = "fileSizeKb";
    public static final String FILE_TIME = "fileTime";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE) //hibernate 5 的 MYSQL 下 AUTO 策略无法对应自增，等修复
    @Column(name = "FILE_INF_ID", nullable = false, length = 10)
    private Integer fileInfId;

    @Column(name = "BUSINESS_CLASS_NM", nullable = false, length = 32)
    private String businessClassNm;

    @Column(name = "BUSINESS_OBJECT_ID", nullable = false, length = 10)
    private Integer businessObjectId;

    @Column(name = "IF_PICT", nullable = false, length = 2)
    private String ifPict;

    @Column(name = "FILE_NM", nullable = false, length = 60)
    private String fileNm;

    @Column(name = "FILE_TYPE_NM", length = 4)
    private String fileTypeNm;

    @Column(name = "FILE_SIZE_KB", length = 10)
    private Integer fileSizeKb;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FILE_TIME", nullable = false, length = 0)
    private Date fileTime;

    @Column(name = "FILE_PATH", nullable = false, length = 60)
    private String filePath;
}


