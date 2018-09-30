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
    String TEMP_DIR = File.separator + "static" + File.separator + "temp" + File.separator;
    String TEMP_PATH = "static/temp/";
    String UPLOAD_DIR = File.separator + "static" + File.separator + "upload" + File.separator;
}
