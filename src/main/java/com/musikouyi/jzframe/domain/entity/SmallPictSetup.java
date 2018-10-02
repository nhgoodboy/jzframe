package com.musikouyi.jzframe.domain.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Create with IDEA
 * Author: YJZ
 * DateTime: 2018/10/1 20:49
 **/
@Data
@Entity
@Table(name = "SYS_SMALL_PICT_SETUP")
public class SmallPictSetup {

    private static final long serialVersionUID = 1L;

    public static final String SMALL_PICT_SETUP_ID = "smallPictSetupId";
    public static final String BUSINESS_CLASS_NM = "businessClassNm";
    public static final String BUSINESS_FIELD_NM = "businessFieldNm";
    public static final String SMALL_PICT_SPEC = "smallPictSpec";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SMALL_PICT_SETUP_ID", nullable = false, length = 10)
    private Integer smallPictSetupId;

    @Column(name = "BUSINESS_CLASS_NM", nullable = false, length = 32)
    private String businessClassNm;

    @Column(name = "BUSINESS_FIELD_NM", nullable = false, length = 32)
    private String businessFieldNm;

    @Column(name = "SMALL_PICT_SPEC")
    private String smallPictSpec;
}
