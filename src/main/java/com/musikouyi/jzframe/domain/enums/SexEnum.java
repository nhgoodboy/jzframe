package com.musikouyi.jzframe.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * Create with IDEA
 * Author: YJZ
 * DateTime: 2018/9/16 21:20
 **/
@Getter
@ToString
@AllArgsConstructor
public enum SexEnum {

    MALE(1, "男"),
    FEMALE(2, "女");

    private Integer code;
    private String msg;

    /**
     * from code to msg
     *
     * @param code
     * @return
     */
    public static String fromCode(Integer code) {
        for (SexEnum sexEnum : SexEnum.values()) {
            if (sexEnum.code.equals(code)) {
                return sexEnum.msg;
            }
        }
        return null;
    }

    /**
     * from msg to code
     *
     * @param msg
     * @return
     */
    public static Integer toCode(String msg) {
        for (SexEnum sexEnum : SexEnum.values()) {
            if (sexEnum.msg.equals(msg)) {
                return sexEnum.code;
            }
        }
        return null;
    }
}
