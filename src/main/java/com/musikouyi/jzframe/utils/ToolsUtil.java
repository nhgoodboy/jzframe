package com.musikouyi.jzframe.utils;

import com.musikouyi.jzframe.common.constant.Global;
import org.apache.shiro.crypto.hash.SimpleHash;

import java.util.Random;

public class ToolsUtil {

    /**
     * 获取随机位数的字符串
     *
     * @author fengshuonan
     * @Date 2017/8/24 14:09
     */
    public static String getRandomString(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    /**
     * shiro密码加密工具类
     *
     * @param credentials 密码
     * @param saltSource 密码盐
     * @return
     */
    public static String encrypt(String credentials, String saltSource) {
        return new SimpleHash(Global.HASH_ALGORITHM_NAME, credentials, saltSource, Global.HASH_ITERATIONS).toString();
    }
}
