package com.musikouyi.jzframe.controller;

import com.musikouyi.jzframe.common.constant.ControllerMapping;
import com.musikouyi.jzframe.domain.entity.Result;
import com.musikouyi.jzframe.service.IDeptService;
import com.musikouyi.jzframe.utils.SpringContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ControllerMapping.DEPT)
public class DeptController {

    @GetMapping(ControllerMapping.DEPT_LIST)
    public Result getDeptList() {
        return SpringContextHolder.getBean(IDeptService.class).getDeptList();
    }
}
