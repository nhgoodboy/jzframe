package com.musikouyi.jzframe.controller;

import com.musikouyi.jzframe.common.constant.ControllerMapping;
import com.musikouyi.jzframe.domain.entity.Result;
import com.musikouyi.jzframe.dto.ListReqDto;
import com.musikouyi.jzframe.dto.RoleReqDto;
import com.musikouyi.jzframe.dto.UserReqDto;
import com.musikouyi.jzframe.service.IRoleService;
import com.musikouyi.jzframe.service.IUserService;
import com.musikouyi.jzframe.utils.SpringContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ControllerMapping.ROLE)
public class RoleController {

    @GetMapping(ControllerMapping.ROLE_NAME_LIST)
    public Result getRoleList() {
        return SpringContextHolder.getBean(IRoleService.class).getRoleNameList();
    }

    @GetMapping(ControllerMapping.LIST)
    public Result list(ListReqDto listReqDto) {
        return SpringContextHolder.getBean(IRoleService.class).findAll(listReqDto);
    }

    @PostMapping(ControllerMapping.DELETE)
    public Result delete(Integer id) {
        return SpringContextHolder.getBean(IRoleService.class).delete(id);
    }

    @PostMapping(ControllerMapping.CREATE)
    public Result create(@RequestBody RoleReqDto roleReqDto) {
        return SpringContextHolder.getBean(IRoleService.class).create(roleReqDto);
    }

    @PostMapping(ControllerMapping.MODIFY)
    public Result modify(@RequestBody RoleReqDto roleReqDto) {
        return SpringContextHolder.getBean(IRoleService.class).modify(roleReqDto);
    }
}
