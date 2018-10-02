package com.musikouyi.jzframe.service.impl;

import com.musikouyi.jzframe.common.constant.Global;
import com.musikouyi.jzframe.domain.entity.Dept;
import com.musikouyi.jzframe.domain.entity.Result;
import com.musikouyi.jzframe.domain.enums.ResultEnum;
import com.musikouyi.jzframe.dto.DeptDto;
import com.musikouyi.jzframe.dto.ListReqDto;
import com.musikouyi.jzframe.dto.ListRespDto;
import com.musikouyi.jzframe.repository.DeptRepository;
import com.musikouyi.jzframe.service.IDeptService;
import com.musikouyi.jzframe.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    @Override
    public Result findAll(ListReqDto listReqDto) {
        Page<Dept> deptPage = deptRepository.findAll(PageRequest.of(listReqDto.getPage() - 1, listReqDto.getLimit()));
        List<Dept> deptList = deptPage.getContent();
        ListRespDto<DeptDto> listRespDto = new ListRespDto<>();
        List<DeptDto> deptDtoList = new ArrayList<>();
        for (Dept dept : deptList) {
            DeptDto deptDto = new DeptDto(
                    dept.getId(),
                    dept.getFullname(),
                    dept.getPid() == Global.SUPER_DEPT_PARENT ? null : deptRepository.findById(dept.getPid()).get().getFullname()
            );
            deptDtoList.add(deptDto);
        }
        listRespDto.setItems(deptDtoList);
        listRespDto.setTotal(deptPage.getTotalElements());
        return ResultUtil.success(listRespDto);
    }

    @Override
    @Transactional
    public Result delete(Integer id) {
        if (Global.SUPER_DEPT_ID == id) {
            return ResultUtil.error(ResultEnum.FORBIDDEN);
        }
        deptRepository.deleteById(id);
        return ResultUtil.success();
    }

    @Override
    public Result create(DeptDto deptDto) {
        Dept dept = new Dept();
        dept.setFullname(deptDto.getFullname());
        dept.setPid(deptRepository.findIdByName(deptDto.getParent_dept()));
        deptRepository.saveAndFlush(dept);
        return ResultUtil.success();
    }

    @Override
    public Result modify(DeptDto deptDto) {
        Dept dept = deptRepository.findById(deptDto.getId()).get();
        dept.setFullname(deptDto.getFullname());
        dept.setPid(deptRepository.findIdByName(deptDto.getParent_dept()));
        deptRepository.saveAndFlush(dept);
        return ResultUtil.success();
    }
}
