package com.musikouyi.jzframe.controller;

import com.musikouyi.jzframe.common.constant.ControllerMapping;
import com.musikouyi.jzframe.common.constant.Global;
import com.musikouyi.jzframe.common.constant.JwtConstants;
import com.musikouyi.jzframe.domain.entity.Result;
import com.musikouyi.jzframe.dto.ListReqDto;
import com.musikouyi.jzframe.dto.UserInfoReqDto;
import com.musikouyi.jzframe.dto.UserReqDto;
import com.musikouyi.jzframe.service.IUserService;
import com.musikouyi.jzframe.utils.JwtTokenUtil;
import com.musikouyi.jzframe.utils.SpringContextHolder;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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

    @RequiresPermissions(value = Global.PERMISSION_USER)
    @GetMapping(ControllerMapping.LIST)
    public Result list(ListReqDto listReqDto) {
        return SpringContextHolder.getBean(IUserService.class).findAll(listReqDto);
    }

    @RequiresPermissions(value = Global.PERMISSION_USER_DELETE)
    @PostMapping(ControllerMapping.DELETE)
    public Result delete(Integer id) {
        return SpringContextHolder.getBean(IUserService.class).delete(id);
    }

    @RequiresPermissions(value = Global.PERMISSION_USER_ADD)
    @PostMapping(ControllerMapping.CREATE)
    public Result create(@RequestBody UserReqDto userReqDto) {
        return SpringContextHolder.getBean(IUserService.class).create(userReqDto);
    }

    @RequiresPermissions(value = Global.PERMISSION_USER_EDIT)
    @PostMapping(ControllerMapping.MODIFY)
    public Result modify(@RequestBody UserReqDto userReqDto) {
        return SpringContextHolder.getBean(IUserService.class).modify(userReqDto);
    }

    @RequiresPermissions(value = Global.PERMISSION_USER_CHANGE_PWD)
    @PostMapping(ControllerMapping.CHANGE_PWD)
    public Result changePwd(Integer id, String newPassword) {
        return SpringContextHolder.getBean(IUserService.class).changePwd(id, newPassword);
    }
}
