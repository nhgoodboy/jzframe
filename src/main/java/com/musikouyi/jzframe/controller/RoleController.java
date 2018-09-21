package com.musikouyi.jzframe.controller;

import com.musikouyi.jzframe.common.constant.ControllerMapping;
import com.musikouyi.jzframe.domain.entity.Result;
import com.musikouyi.jzframe.service.IRoleService;
import com.musikouyi.jzframe.utils.SpringContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ControllerMapping.ROLE)
public class RoleController {

    @GetMapping(ControllerMapping.ROLE_LIST)
    public Result getRoleList() {
        return SpringContextHolder.getBean(IRoleService.class).getRoleList();
    }
}
