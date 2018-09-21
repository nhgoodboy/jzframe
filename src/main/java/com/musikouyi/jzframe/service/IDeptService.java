package com.musikouyi.jzframe.service;

import com.musikouyi.jzframe.domain.entity.Dept;
import com.musikouyi.jzframe.domain.entity.Result;

/**
 * Create with IDEA
 * Author: YJZ
 * DateTime: 2018/9/16 21:56
 **/
public interface IDeptService {

    Dept findById(Integer deptId);

    /**
     * 获取部门列表
     */
    Result getDeptList();

}
