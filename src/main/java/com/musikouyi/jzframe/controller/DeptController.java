package com.musikouyi.jzframe.controller;

import com.musikouyi.jzframe.common.constant.ControllerMapping;
import com.musikouyi.jzframe.domain.entity.Result;
import com.musikouyi.jzframe.dto.DeptDto;
import com.musikouyi.jzframe.dto.ListReqDto;
import com.musikouyi.jzframe.service.IDeptService;
import com.musikouyi.jzframe.utils.SpringContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ControllerMapping.DEPT)
public class DeptController {

    @GetMapping(ControllerMapping.DEPT_NAME_LIST)
    public Result getDeptNameList() {
        return SpringContextHolder.getBean(IDeptService.class).getDeptNameList();
    }

    @GetMapping(ControllerMapping.LIST)
    public Result list(ListReqDto listReqDto) {
        return SpringContextHolder.getBean(IDeptService.class).findAll(listReqDto);
    }

    @PostMapping(ControllerMapping.DELETE)
    public Result delete(Integer id) {
        return SpringContextHolder.getBean(IDeptService.class).delete(id);
    }

    @PostMapping(ControllerMapping.CREATE)
    public Result create(@RequestBody DeptDto deptDto) {
        return SpringContextHolder.getBean(IDeptService.class).create(deptDto);
    }

    @PostMapping(ControllerMapping.MODIFY)
    public Result modify(@RequestBody DeptDto deptDto) {
        return SpringContextHolder.getBean(IDeptService.class).modify(deptDto);
    }

    @GetMapping(ControllerMapping.GET_DEPTS)
    public Result getDepts(ListReqDto listReqDto) {
        return SpringContextHolder.getBean(IDeptService.class).getDepts(listReqDto);
    }
}
