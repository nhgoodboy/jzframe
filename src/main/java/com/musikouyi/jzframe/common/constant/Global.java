package com.musikouyi.jzframe.common.constant;

import java.io.File;

/**
 * Create with IDEA
 * Author: YJZ
 * DateTime: 2018/9/16 0:20
 **/
public interface Global {

    int SUPER_ROLE_PARENT = 0;   // 超级管理员的父角色id
    int SUPER_ROLE_ID = 1;   // 超级管理员的角色id
    int SUPER_USER_ID = 1;   // 超级管理员的用户id
    int SUPER_DEPT_ID = 1;   // 总部门的id
    int SUPER_DEPT_PARENT = 0;   // 总部门的父部门的id

    //目录
    String CLASSPATH = "classpath:";
    String STATIC_DIR = "static";
    String CLASSPATH_STATIC_DIR = "classpath:static";
    String TEMP_DIR = "temp";
    String SMALL_PICT_DIR = "smallpict";
    String UPLOAD_DIR = "upload";
    String SMALL_PICT_DEFAULT_DIR = "default";
    String SMALL_PICT_COMMON_DIR = "common";

    //4个entity需要自动保存到文件系统的变量命名后缀
    String PICT_ID_FIELD_SUFFIX = "PictId";
    String PICT_IDS_FIELD_SUFFIX = "PictIds";
    String FILE_ID_FIELD_SUFFIX = "FileId";
    String FILE_IDS_FIELD_SUFFIX = "FileIds";

    String DEFAULT_TEXT_SPLIT_CHAR = ",";
    String SMALL_PICT_SIZE_SPLIT_CHAR = "x";

    int DEFAULT_SMALL_PICT_SIZE = 150; //默认小图大小

    String ES_DEFAULT_INDEX = "default_index";
    String ES_DEFAULT_ANALYSER = "elasticsearch-analyser.json";
}
