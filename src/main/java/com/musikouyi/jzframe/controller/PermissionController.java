package com.musikouyi.jzframe.controller;

import com.musikouyi.jzframe.common.constant.ControllerMapping;
import com.musikouyi.jzframe.common.constant.JwtConstants;
import com.musikouyi.jzframe.domain.entity.Result;
import com.musikouyi.jzframe.service.IPermissionService;
import com.musikouyi.jzframe.utils.ResultUtil;
import com.musikouyi.jzframe.utils.SpringContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Create with IDEA
 * Author: YJZ
 * DateTime: 2018/10/27 18:47
 **/

@RestController
@RequestMapping(ControllerMapping.PERMISSION)
public class PermissionController {

    @GetMapping("/list")
    public Result getPermissionsList(@RequestHeader(JwtConstants.AUTH_HEADER) String token) {
        List permissionIds = SpringContextHolder.getBean(IPermissionService.class).getPermissionIdsByRoleId(1);
        System.out.println(permissionIds.toString());
        return ResultUtil.success(permissionIds);
    }
}
