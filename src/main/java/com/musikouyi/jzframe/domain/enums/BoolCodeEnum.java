package com.musikouyi.jzframe.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;


/**
 * 是否值代码
 *
 * @author wj
 */
@Getter
@ToString
@AllArgsConstructor
public enum BoolCodeEnum {

    /**
     * 是
     */
    YES(0, "Y"),
    /**
     * 否
     */
    NO(1, "N");

    private Integer code;
    private String msg;

    /**
     * from code to msg
     *
     * @param code
     * @return
     */
    public static String fromCode(Integer code) {
        for (BoolCodeEnum boolCodeEnum : BoolCodeEnum.values()) {
            if (boolCodeEnum.code.equals(code)) {
                return boolCodeEnum.msg;
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
        for (BoolCodeEnum boolCodeEnum : BoolCodeEnum.values()) {
            if (boolCodeEnum.msg.equals(msg)) {
                return boolCodeEnum.code;
            }
        }
        return null;
    }

    public static String toCode(boolean value) {
        if (value) {
            return YES.getMsg();
        }
        return NO.getMsg();
    }
}
