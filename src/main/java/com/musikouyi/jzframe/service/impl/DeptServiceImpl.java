package com.musikouyi.jzframe.service.impl;

import com.musikouyi.jzframe.domain.entity.Dept;
import com.musikouyi.jzframe.domain.entity.Result;
import com.musikouyi.jzframe.repository.DeptRepository;
import com.musikouyi.jzframe.service.IDeptService;
import com.musikouyi.jzframe.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Create with IDEA
 * Author: YJZ
 * DateTime: 2018/9/16 21:56
 **/
@Service
public class DeptServiceImpl implements IDeptService {

    @Autowired
    private DeptRepository deptRepository;

    @Override
    public Dept findById(Integer deptId) {
        Optional<Dept> dept = deptRepository.findById(deptId);
        return dept.get();
    }

    @Override
    public Result getDeptNameList() {
        List<String> deptNameList = deptRepository.findName();
        return ResultUtil.success(deptNameList);
    }
}
