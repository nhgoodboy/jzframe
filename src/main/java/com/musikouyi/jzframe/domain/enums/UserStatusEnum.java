package com.musikouyi.jzframe.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * Create with IDEA
 * Author: YJZ
 * DateTime: 2018/9/16 22:18
 **/

@Getter
@ToString
@AllArgsConstructor
public enum UserStatusEnum {

    USING(1, "启用"),
    FREEZE(2, "冻结"),
    DELETED(3, "已删除");

    private Integer code;
    private String msg;

    public static String fromCode(Integer code) {
        for (UserStatusEnum userStatusEnum : UserStatusEnum.values()) {
            if (userStatusEnum.code.equals(code)) {
                return userStatusEnum.msg;
            }
        }
        return null;
    }

    public static Integer toCode(String msg) {
        for (UserStatusEnum userStatusEnum : UserStatusEnum.values()) {
            if (userStatusEnum.msg.equals(msg)) {
                return userStatusEnum.code;
            }
        }
        return null;
    }
}
