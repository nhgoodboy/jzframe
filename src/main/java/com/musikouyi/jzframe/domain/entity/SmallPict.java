package com.musikouyi.jzframe.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Create with IDEA
 * Author: YJZ
 * DateTime: 2018/10/1 21:30
 **/
@Data
@Entity
@Table(name = "SYS_SMALL_PICT")
public class SmallPict {

    private static final long serialVersionUID = 6971226453296699368L;

    public static final String SMALL_PICT_ID = "smallPictId";
    public static final String FILE_INF_ID = "fileInfId";
    public static final String SMALL_PICT_SETUP_ID = "smallPictSetupId";
    public static final String SMALL_PICT_WIDTH = "smallPictWidth";
    public static final String SMALL_PICT_HEIGHT = "smallPictHeight";
    public static final String FILE_SIZE_KB = "fileSizeKb";
    public static final String FILE_TIME = "fileTime";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SMALL_PICT_ID", nullable = false, length = 10)
    private Integer smallPictId;

    @Column(name = "FILE_INF_ID", nullable = false, length = 10)
    private Integer fileInfId;

    @Column(name = "SMALL_PICT_SETUP_ID", length = 10)
    private Integer smallPictSetupId;

    @Column(name = "SMALL_PICT_WIDTH", nullable = false, length = 10)
    private Integer smallPictWidth;

    @Column(name = "SMALL_PICT_HEIGHT", nullable = false, length = 10)
    private Integer smallPictHeight;

    @Column(name = "FILE_SIZE_KB", length = 10)
    private Integer fileSizeKb;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FILE_TIME", nullable = false, length = 0)
    private Date fileTime;
}
