package com.musikouyi.jzframe.common.exception;


import com.musikouyi.jzframe.domain.enums.ResultEnum;

public class GlobalException extends RuntimeException {

    private Integer code;

    public GlobalException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }
}
