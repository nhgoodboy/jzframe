package com.musikouyi.jzframe.service;

import com.musikouyi.jzframe.domain.entity.Dept;
import com.musikouyi.jzframe.dto.DeptDto;
import com.musikouyi.jzframe.dto.ListReqDto;
import com.musikouyi.jzframe.dto.ListRespDto;

import java.util.List;

/**
 * Create with IDEA
 * Author: YJZ
 * DateTime: 2018/9/16 21:56
 **/
public interface IDeptService {

    /**
     * 获取部门列表
     */
    List<String> getDeptNameList();

    /**
     * 获取部门列表
     */
    ListRespDto<DeptDto> findAll(ListReqDto listReqDto);

    /**
     * 删除部门
     */
    void delete(Integer id);

    /**
     * 创建部门
     */
    Dept create(DeptDto deptDto);

    /**
     * 修改部门
     */
    Dept modify(DeptDto deptDto);

    /**
     * 获取部门（mybatis测试）
     */
    ListRespDto<DeptDto> getDepts(ListReqDto listReqDto);
}
