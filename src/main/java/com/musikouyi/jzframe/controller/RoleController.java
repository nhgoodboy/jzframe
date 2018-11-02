package com.musikouyi.jzframe.controller;

import com.musikouyi.jzframe.common.constant.ControllerMapping;
import com.musikouyi.jzframe.common.constant.Global;
import com.musikouyi.jzframe.domain.entity.Result;
import com.musikouyi.jzframe.dto.ListReqDto;
import com.musikouyi.jzframe.dto.RoleDto;
import com.musikouyi.jzframe.service.IPermissionService;
import com.musikouyi.jzframe.service.IRoleService;
import com.musikouyi.jzframe.utils.SpringContextHolder;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ControllerMapping.ROLE)
public class RoleController {

    @RequiresPermissions(value = Global.PERMISSION_ROLE_PERMISSION_CONFIG)
    @GetMapping(ControllerMapping.ROLE_NAME_LIST)
    public Result getRoleNameList() {
        return SpringContextHolder.getBean(IRoleService.class).getRoleNameList();
    }

    @RequiresPermissions(value = Global.PERMISSION_ROLE)
    @GetMapping(ControllerMapping.LIST)
    public Result list(ListReqDto listReqDto) {
        return SpringContextHolder.getBean(IRoleService.class).findAll(listReqDto);
    }

    @RequiresPermissions(value = Global.PERMISSION_ROLE_DELETE)
    @PostMapping(ControllerMapping.DELETE)
    public Result delete(Integer id) {
        return SpringContextHolder.getBean(IRoleService.class).delete(id);
    }

    @RequiresPermissions(value = Global.PERMISSION_ROLE_ADD)
    @PostMapping(ControllerMapping.CREATE)
    public Result create(@RequestBody RoleDto roleDto) {
        return SpringContextHolder.getBean(IRoleService.class).create(roleDto);
    }

    @RequiresPermissions(value = Global.PERMISSION_ROLE_EDIT)
    @PostMapping(ControllerMapping.MODIFY)
    public Result modify(@RequestBody RoleDto roleDto) {
        return SpringContextHolder.getBean(IRoleService.class).modify(roleDto);
    }

    @RequiresPermissions(value = Global.PERMISSION_ROLE_PERMISSION_CONFIG)
    @GetMapping("/permissionsTree/{roleId}")
    public Result getPermissionsTree(@PathVariable("roleId") Integer roleId) {
        return SpringContextHolder.getBean(IPermissionService.class).getPermissionsTree(roleId);
    }

    @RequiresPermissions(value = Global.PERMISSION_ROLE_PERMISSION_CONFIG)
    @PutMapping("/permissionsTree/{roleId}")
    public Result changePermission(@PathVariable("roleId") Integer roleId, @RequestBody List<Integer> permissionIds) {
        return SpringContextHolder.getBean(IPermissionService.class).changePermission(roleId, permissionIds);
    }
}
