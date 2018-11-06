package com.musikouyi.jzframe.controller;

import com.musikouyi.jzframe.common.constant.ControllerMapping;
import com.musikouyi.jzframe.common.constant.JwtConstants;
import com.musikouyi.jzframe.domain.entity.Result;
import com.musikouyi.jzframe.dto.UserInfoReqDto;
import com.musikouyi.jzframe.service.IUserInfoService;
import com.musikouyi.jzframe.utils.JwtTokenUtil;
import com.musikouyi.jzframe.utils.SpringContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;

@RestController
@RequestMapping(ControllerMapping.USER_INFO)
public class UserInfoController {

    @GetMapping("/userInfo")
    public Result userInfo(@RequestHeader(JwtConstants.AUTH_HEADER) String token) {
        return SpringContextHolder.getBean(IUserInfoService.class).userInfo(
                JwtTokenUtil.getUserIdFromToken(token.substring(JwtConstants.TOKEN_PREFIX_LENGTH)));
    }

    @PostMapping(ControllerMapping.EDIT_USER_INFO)
    public Result editUserInfo(@RequestBody UserInfoReqDto userInfoReqDto) {
        return SpringContextHolder.getBean(IUserInfoService.class).editUserInfo(userInfoReqDto);
    }

    @PutMapping(ControllerMapping.CHANGE_AVATAR + "/{userHeadId}")   //REST风格
    public Result changeAvatar(@PathVariable("userHeadId") Integer userHeadId, @RequestHeader(JwtConstants.AUTH_HEADER) String token) throws FileNotFoundException {
        return SpringContextHolder.getBean(IUserInfoService.class).changeAvatar(userHeadId,
                JwtTokenUtil.getUserIdFromToken(token.substring(JwtConstants.TOKEN_PREFIX_LENGTH)));
    }
}
