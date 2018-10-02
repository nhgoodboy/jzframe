package com.musikouyi.jzframe.common.constant;

/**
 * 后台的REST请求资源定义
 * Author: YJZ
 * DateTime: 2018/9/16 0:25
 **/
public interface ControllerMapping {

    String BASE = "/";    //基本路径
    String ADMIN_BASE = "/admin";    //需要管理员权限路径

    String DO_LOGIN = "/doLogin";    //登录
    String DO_LOGOUT = "/doLogout";    //登出

    String DELETE = "/delete";
    String CREATE = "/create";
    String MODIFY = "/modify";
    String LIST = "/list";
    //test
    String TEST = ADMIN_BASE + "/test";
    //user
    String USER = ADMIN_BASE + "/user";
    String USER_INFO = "/userInfo";
    String CHANGE_PWD = "/changePwd";
    String EDIT_USER_INFO = "/editUserInfo";
    //role
    String ROLE = ADMIN_BASE + "/role";
    String ROLE_NAME_LIST = "/roleNameList";
    //dept
    String DEPT = ADMIN_BASE + "/dept";
    String DEPT_NAME_LIST = "/deptNameList";
    //文件管理
    String ADMIN_FILE_INF_BASE = ADMIN_BASE + "/fileInf"; //文件管理
    String UPLOAD = "/upload"; //文件上传
    String CHANGE_AVATAR = "/changeAvatar";
}
