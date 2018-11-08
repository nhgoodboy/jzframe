package com.musikouyi.jzframe.controller;

import com.musikouyi.jzframe.common.constant.ControllerMapping;
import com.musikouyi.jzframe.common.constant.Global;
import com.musikouyi.jzframe.domain.entity.Result;
import com.musikouyi.jzframe.dto.DeptDto;
import com.musikouyi.jzframe.dto.ListReqDto;
import com.musikouyi.jzframe.service.IDeptService;
import com.musikouyi.jzframe.utils.ResultUtil;
import com.musikouyi.jzframe.utils.SpringContextHolder;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ControllerMapping.DEPT)
public class DeptController {

    //    @RequiresPermissions(value = {Global.Per})
    @GetMapping(ControllerMapping.DEPT_NAME_LIST)
    public Result getDeptNameList() {
        return ResultUtil.success(SpringContextHolder.getBean(IDeptService.class).getDeptNameList());
    }

    @RequiresPermissions(value = Global.PERMISSION_DEPT)
    @GetMapping(ControllerMapping.LIST)
    public Result list(ListReqDto listReqDto) {
        return ResultUtil.success(SpringContextHolder.getBean(IDeptService.class).findAll(listReqDto));
    }

    @RequiresPermissions(value = Global.PERMISSION_DEPT_DELETE)
    @PostMapping(ControllerMapping.DELETE)
    public Result delete(Integer id) {
        SpringContextHolder.getBean(IDeptService.class).delete(id);
        return ResultUtil.success();
    }

    @RequiresPermissions(value = Global.PERMISSION_DEPT_ADD)
    @PostMapping(ControllerMapping.CREATE)
    public Result create(@RequestBody DeptDto deptDto) {
        SpringContextHolder.getBean(IDeptService.class).create(deptDto);
        return ResultUtil.success();
    }

    @RequiresPermissions(value = Global.PERMISSION_DEPT_EDIT)
    @PostMapping(ControllerMapping.MODIFY)
    public Result modify(@RequestBody DeptDto deptDto) {
        SpringContextHolder.getBean(IDeptService.class).modify(deptDto);
        return ResultUtil.success();
    }

    @RequiresPermissions(Global.PERMISSION_DEPT)
    @GetMapping(ControllerMapping.GET_DEPTS)
    public Result getDepts(ListReqDto listReqDto) {
        return ResultUtil.success(SpringContextHolder.getBean(IDeptService.class).getDepts(listReqDto));
    }
}
