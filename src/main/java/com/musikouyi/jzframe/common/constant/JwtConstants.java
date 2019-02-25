package com.musikouyi.jzframe.common.constant;

/**
 * jwt相关配置
 *
 * @author yjz
 * @date 2018-10-23 9:23
 */
public interface JwtConstants {

    String AUTH_HEADER = "Authorization";

    String SECRET = "defaultSecret";    //密钥

    long EXPIRATION = 1 * 60L;   //过期时间: 1小时（单位秒）

    String AUTH_PATH = ControllerMapping.DO_LOGIN;   //不需要验证的路径

    String TOKEN_PREFIX = "Bearer ";

    int TOKEN_PREFIX_LENGTH = TOKEN_PREFIX.length();

    String TOKEN_SESSION = "token";
}
