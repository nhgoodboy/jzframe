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
    public static final String BUSINESS_TYPE_CODE = "businessTypeCode";
    public static final String BUSINESS_OBJECT_ID = "businessObjectId";
    public static final String FILE_NM = "fileNm";
    public static final String FILE_TYPE_NM = "fileTypeNm";
    public static final String FILE_TIME = "fileTime";
    public static final String IF_FAIL_USE = "ifFailUse";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FILE_INF_ID", nullable = false, length = 10)
    private Integer fileInfId;

    @Column(name = "BUSINESS_TYPE_CODE", nullable = false, length = 2)
    private String businessTypeCode;

    @Column(name = "BUSINESS_OBJECT_ID", nullable = false, length = 128)
    private Integer businessObjectId;

    @Column(name = "FILE_NM", nullable = false, length = 60)
    private String fileNm;

    @Column(name = "FILE_TYPE_NM", length = 4)
    private String fileTypeNm;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FILE_TIME", nullable = false, length = 0)
    private Date fileTime;

    @Column(name = "FILE_PATH", nullable = false, length = 60)
    private String filePath;

    @Column(name = "IF_FAIL_USE", nullable = false, length = 2)
    private String ifFailUse;
}


