package com.musikouyi.jzframe.common.constant;

/**
 * Create with IDEA
 * Author: YJZ
 * DateTime: 2018/9/16 0:20
 **/
public interface Global {

    //数据库
    String TOP_SIGN = "#";
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

    //图片
    //4个entity需要自动保存到文件系统的变量命名后缀
    String PICT_ID_FIELD_SUFFIX = "PictId";
    String PICT_IDS_FIELD_SUFFIX = "PictIds";
    String FILE_ID_FIELD_SUFFIX = "FileId";
    String FILE_IDS_FIELD_SUFFIX = "FileIds";
    String DEFAULT_TEXT_SPLIT_CHAR = ",";
    String SMALL_PICT_SIZE_SPLIT_CHAR = "x";
    int DEFAULT_SMALL_PICT_SIZE = 150; //默认小图大小

    //ElasticSearch
    String ES_DEFAULT_INDEX = "role";
    String ES_DEFAULT_ANALYSER = "elasticsearch-analyser.json";

    //请求
    String OPTIONS_REQUEST = "OPTIONS";

    //shiro
    String HASH_ALGORITHM_NAME = "SHA-256";
    int SALT_LENGTH = 5; //密码盐的位数
    int HASH_ITERATIONS = 8;  //加密hash次数

    //权限
    String PERMISSION_USER = "user";
    String PERMISSION_USER_ADD = "user_add";
    String PERMISSION_USER_EDIT = "user_edit";
    String PERMISSION_USER_DELETE = "user_delete";
    String PERMISSION_USER_CHANGE_PWD = "user_change_pwd";
    String PERMISSION_ROLE = "role";
    String PERMISSION_ROLE_ADD = "role_add";
    String PERMISSION_ROLE_EDIT = "role_edit";
    String PERMISSION_ROLE_DELETE = "role_delete";
    String PERMISSION_ROLE_PERMISSION_CONFIG = "role_permission_config";
    String PERMISSION_DEPT = "dept";
    String PERMISSION_DEPT_ADD = "dept_add";
    String PERMISSION_DEPT_EDIT = "dept_edit";
    String PERMISSION_DEPT_DELETE = "dept_delete";
}
