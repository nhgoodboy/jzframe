package com.musikouyi.jzframe.service;

import com.musikouyi.jzframe.domain.entity.Dept;
import com.musikouyi.jzframe.domain.entity.Result;
import com.musikouyi.jzframe.dto.DeptDto;
import com.musikouyi.jzframe.dto.ListReqDto;

/**
 * Create with IDEA
 * Author: YJZ
 * DateTime: 2018/9/16 21:56
 **/
public interface IDeptService {

    /**
     * 获取部门列表
     */
    Result getDeptNameList();

    /**
     * 获取部门列表
     */
    Result findAll(ListReqDto listReqDto);

    /**
     * 删除部门
     */
    Result delete(Integer id);

    /**
     * 创建部门
     */
    Result create(DeptDto roleReqDto);

    /**
     * 修改部门
     */
    Result modify(DeptDto roleReqDto);

    /**
     * 获取部门（mybatis测试）
     */
    Result getDepts(ListReqDto listReqDto);
}
