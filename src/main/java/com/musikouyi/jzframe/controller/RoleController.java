package com.musikouyi.jzframe.controller;

import com.musikouyi.jzframe.common.constant.ControllerMapping;
import com.musikouyi.jzframe.domain.entity.Result;
import com.musikouyi.jzframe.dto.ListReqDto;
import com.musikouyi.jzframe.dto.RoleDto;
import com.musikouyi.jzframe.service.IMenuService;
import com.musikouyi.jzframe.service.IRoleService;
import com.musikouyi.jzframe.utils.SpringContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(ControllerMapping.ROLE)
public class RoleController {

    @GetMapping(ControllerMapping.ROLE_NAME_LIST)
    public Result getRoleNameList() {
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
    public Result create(@RequestBody RoleDto roleDto) {
        return SpringContextHolder.getBean(IRoleService.class).create(roleDto);
    }

    @PostMapping(ControllerMapping.MODIFY)
    public Result modify(@RequestBody RoleDto roleDto) {
        return SpringContextHolder.getBean(IRoleService.class).modify(roleDto);
    }

    @GetMapping("/menusTree/{roleId}")
    public Result getMenusTree(@PathVariable("roleId") Integer roleId) {
        return SpringContextHolder.getBean(IMenuService.class).getMenusTree(roleId);
    }

    @PutMapping("/menusTree/{roleId}")
    public Result changePermission(@PathVariable("roleId") Integer roleId, @RequestBody List<Integer> menuIds) {
        return SpringContextHolder.getBean(IMenuService.class).changePermission(roleId, menuIds);
    }
}
