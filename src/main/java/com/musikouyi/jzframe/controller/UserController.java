package com.musikouyi.jzframe.controller;

import com.musikouyi.jzframe.common.constant.ControllerMapping;
import com.musikouyi.jzframe.common.constant.JwtConstants;
import com.musikouyi.jzframe.domain.entity.Result;
import com.musikouyi.jzframe.dto.ListReqDto;
import com.musikouyi.jzframe.dto.UserInfoReqDto;
import com.musikouyi.jzframe.dto.UserReqDto;
import com.musikouyi.jzframe.service.IUserService;
import com.musikouyi.jzframe.utils.JwtTokenUtil;
import com.musikouyi.jzframe.utils.SpringContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;

/**
 * Create with IDEA
 * Author: YJZ
 * DateTime: 2018/9/16 9:50
 **/
@RestController
@RequestMapping(ControllerMapping.USER)
public class UserController {

    @GetMapping(ControllerMapping.USER_INFO)
    public Result userInfo(@RequestHeader("Authorization") String token) {
        return SpringContextHolder.getBean(IUserService.class).userInfo(
                Integer.valueOf(JwtTokenUtil.getUserIdFromToken(token.substring(7))));
    }

    @GetMapping(ControllerMapping.LIST)
    public Result list(ListReqDto listReqDto) {
        return SpringContextHolder.getBean(IUserService.class).findAll(listReqDto);
    }

    @PostMapping(ControllerMapping.DELETE)
    public Result delete(Integer id) {
        return SpringContextHolder.getBean(IUserService.class).delete(id);
    }

    @PostMapping(ControllerMapping.CREATE)
    public Result create(@RequestBody UserReqDto userReqDto) {
        return SpringContextHolder.getBean(IUserService.class).create(userReqDto);
    }

    @PostMapping(ControllerMapping.MODIFY)
    public Result modify(@RequestBody UserReqDto userReqDto) {
        return SpringContextHolder.getBean(IUserService.class).modify(userReqDto);
    }

    @PostMapping(ControllerMapping.CHANGE_PWD)
    public Result changePwd(Integer id, String newPassword) {
        return SpringContextHolder.getBean(IUserService.class).changePwd(id, newPassword);
    }

    @PostMapping(ControllerMapping.EDIT_USER_INFO)
    public Result editUserInfo(@RequestBody UserInfoReqDto userInfoReqDto) {
        return SpringContextHolder.getBean(IUserService.class).editUserInfo(userInfoReqDto);
    }

    @PutMapping(ControllerMapping.CHANGE_AVATAR + "/{userHeadId}")   //REST风格
    public Result changeAvatar(@PathVariable("userHeadId") Integer userHeadId, @RequestHeader(JwtConstants.AUTH_HEADER) String token) throws FileNotFoundException {
        return SpringContextHolder.getBean(IUserService.class).changeAvatar(userHeadId,
                Integer.valueOf(JwtTokenUtil.getUserIdFromToken(token.substring(7))));
    }
}
