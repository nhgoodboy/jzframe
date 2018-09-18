package com.musikouyi.jzframe.controller;

import com.musikouyi.jzframe.common.constant.ControllerMapping;
import com.musikouyi.jzframe.domain.entity.Result;
import com.musikouyi.jzframe.dto.ListReqDto;
import com.musikouyi.jzframe.service.IUserService;
import com.musikouyi.jzframe.utils.JwtTokenUtil;
import com.musikouyi.jzframe.utils.SpringContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * Create with IDEA
 * Author: YJZ
 * DateTime: 2018/9/16 9:50
 **/
@RestController
@RequestMapping(ControllerMapping.USER)
public class UserController {

    @GetMapping(ControllerMapping.USER_INFO)
    public Result userInfo(@RequestParam("token") String token) {
        return SpringContextHolder.getBean(IUserService.class).findById(Integer.valueOf(JwtTokenUtil.getUserIdFromToken(token)));
    }

    @GetMapping(ControllerMapping.USER_LIST)
    public Result userList(@RequestBody ListReqDto listReqDto) {
        return SpringContextHolder.getBean(IUserService.class).findAll(listReqDto);
    }
}
