package com.musikouyi.jzframe.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum ResultEnum {

    SUCCESS(0, "成功"),
    UNKNOWN_ERROR(-1, "未知错误"),

    USERNAME_PASSWORD_ERROR(601, "账号或密码错误"),

    TOKEN_EXPIRED(700, "token过期"),
    TOKEN_ERROR(701, "token验证失败"),
    TOKEN_WITHOUT(702, "请求头没有携带token"),

    FORBIDDEN(800, "非法操作"),

    DATABASE_QUERRY_ERROR(900, "数据库查询错误");

    private Integer code;
    private String msg;
}
