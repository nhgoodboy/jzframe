package com.musikouyi.jzframe.service.impl;

import com.musikouyi.jzframe.common.constant.Global;
import com.musikouyi.jzframe.domain.entity.Result;
import com.musikouyi.jzframe.domain.entity.Role;
import com.musikouyi.jzframe.domain.enums.ResultEnum;
import com.musikouyi.jzframe.dto.ListReqDto;
import com.musikouyi.jzframe.dto.ListRespDto;
import com.musikouyi.jzframe.dto.RoleDto;
import com.musikouyi.jzframe.exception.GlobalException;
import com.musikouyi.jzframe.repository.DeptRepository;
import com.musikouyi.jzframe.repository.RoleRepository;
import com.musikouyi.jzframe.search.RoleSearchRepository;
import com.musikouyi.jzframe.service.IRoleService;
import com.musikouyi.jzframe.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


/**
 * Create with IDEA
 * Author: YJZ
 * DateTime: 2018/9/16 14:35
 **/
@Service
public class RoleServiceImpl implements IRoleService {

    private final RoleRepository roleRepository;

    private final DeptRepository deptRepository;

    private final RoleSearchRepository roleSearchRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, DeptRepository deptRepository, RoleSearchRepository roleSearchRepository) {
        this.roleRepository = roleRepository;
        this.deptRepository = deptRepository;
        this.roleSearchRepository = roleSearchRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Result getRoleNameList() {
        List<String> roleNameList = roleRepository.findName();
        return ResultUtil.success(roleNameList);
    }

    @Override
    @Transactional(readOnly = true)
    public Result findAll(ListReqDto listReqDto) {
        Page<Role> rolePage = roleSearchRepository.findAll(PageRequest.of(listReqDto.getPage() - 1, listReqDto.getLimit()));
//        Page<Role> rolePage = roleRepository.findAll(PageRequest.of(listReqDto.getPage() - 1, listReqDto.getLimit()));
        List<Role> roleList = rolePage.getContent();
        ListRespDto<RoleDto> listRespDto = new ListRespDto<>();
        List<RoleDto> roleDtoList = new ArrayList<>();
        for (Role role : roleList) {
            RoleDto roleRespDto = new RoleDto(
                    role.getId(),
                    role.getName(),
                    role.getParentId() == Global.SUPER_ROLE_PARENT ? null : roleRepository.findById(role.getParentId())
                            .orElseThrow(()->new GlobalException(ResultEnum.DATABASE_QUERRY_ERROR)).getName(),
                    deptRepository.findById(role.getDeptId()).orElseThrow(()->new GlobalException(ResultEnum.DATABASE_QUERRY_ERROR)).getFullName()
            );
            roleDtoList.add(roleRespDto);
        }
        listRespDto.setItems(roleDtoList);
        listRespDto.setTotal(rolePage.getTotalElements());
        return ResultUtil.success(listRespDto);
    }

    @Override
    @Transactional
    public Result delete(Integer id) {
        if (Global.SUPER_ROLE_ID == id) {
            return ResultUtil.error(ResultEnum.FORBIDDEN);
        }
        roleRepository.deleteById(id);
        return ResultUtil.success();
    }

    @Override
    @Transactional
    public Result create(RoleDto roleDto) {
        Role role = new Role();
        role.setName(roleDto.getName());
        role.setParentId(roleRepository.findIdByName(roleDto.getParent_role()));
        role.setDeptId(deptRepository.findIdByName(roleDto.getDept()));
        roleRepository.saveAndFlush(role);
        return ResultUtil.success();
    }

    @Override
    @Transactional
    public Result modify(RoleDto roleDto) {
        Role role = roleRepository.findById(roleDto.getId()).orElseThrow(()->new GlobalException(ResultEnum.DATABASE_QUERRY_ERROR));
        role.setName(roleDto.getName());
        role.setParentId(roleRepository.findIdByName(roleDto.getParent_role()));
        role.setDeptId(deptRepository.findIdByName(roleDto.getDept()));
        roleRepository.saveAndFlush(role);
        return ResultUtil.success();
    }
}
