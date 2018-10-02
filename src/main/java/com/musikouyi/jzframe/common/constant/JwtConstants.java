package com.musikouyi.jzframe.common.constant;

/**
 * jwt相关配置
 *
 * @author fengshuonan
 * @date 2017-08-23 9:23
 */
public interface JwtConstants {

    String AUTH_HEADER = "Authorization";

    String SECRET = "defaultSecret";    //密钥

    Long EXPIRATION = 604800L;   //一星期

    String AUTH_PATH = ControllerMapping.DO_LOGIN;   //需要验证的路径

}
