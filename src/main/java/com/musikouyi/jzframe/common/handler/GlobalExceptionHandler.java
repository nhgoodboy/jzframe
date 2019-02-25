package com.musikouyi.jzframe.common.handler;

import com.musikouyi.jzframe.common.exception.GlobalException;
import com.musikouyi.jzframe.domain.entity.Result;
import com.musikouyi.jzframe.domain.enums.ResultEnum;
import com.musikouyi.jzframe.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice //用于拦截全局的Controller的异常，注意：ControllerAdvice注解只拦截Controller不会拦截Interceptor的异常
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e) {
        if (e instanceof GlobalException) {
            GlobalException globalException = (GlobalException) e;
            return ResultUtil.error(globalException.getCode(), globalException.getMessage());
        } else {
            log.error(ResultEnum.UNKNOWN_ERROR.getMsg(), e);
            return ResultUtil.error(ResultEnum.UNKNOWN_ERROR);
//            return ResultUtil.error(-1, e.getMessage());
        }
    }
}
