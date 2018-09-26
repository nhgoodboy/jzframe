package com.musikouyi.jzframe.service.impl;

import com.musikouyi.jzframe.common.constant.Global;
import com.musikouyi.jzframe.domain.entity.Result;
import com.musikouyi.jzframe.domain.entity.Role;
import com.musikouyi.jzframe.domain.enums.ResultEnum;
import com.musikouyi.jzframe.domain.node.ZTreeNode;
import com.musikouyi.jzframe.dto.ListReqDto;
import com.musikouyi.jzframe.dto.ListRespDto;
import com.musikouyi.jzframe.dto.RoleRespDto;
import com.musikouyi.jzframe.repository.DeptRepository;
import com.musikouyi.jzframe.repository.RoleRepository;
import com.musikouyi.jzframe.service.IRoleService;
import com.musikouyi.jzframe.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;


/**
 * Create with IDEA
 * Author: YJZ
 * DateTime: 2018/9/16 14:35
 **/
@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private DeptRepository deptRepository;


    @Override
    public void setAuthority(Integer roleId, String ids) {

    }

    @Override
    public void delRoleById(Integer roleId) {

    }

    @Override
    public List<Map<String, Object>> selectRoles(String condition) {
        return null;
    }

    @Override
    public int deleteRolesById(Integer roleId) {
        return 0;
    }

    @Override
    public List<ZTreeNode> roleTreeList() {
        return null;
    }

    @Override
    public List<ZTreeNode> roleTreeListByRoleId(String[] roleId) {
        return null;
    }

    @Override
    public Role findById(Integer roleId) {
        Optional<Role> roleOptional = roleRepository.findById(roleId);
        return roleOptional.get();
    }

    @Override
    public Result getRoleNameList() {
        List<String> roleNameList = roleRepository.findName();
        return ResultUtil.success(roleNameList);
    }

    @Override
    public Result findAll(ListReqDto listReqDto) {
        Page<Role> rolePage = roleRepository.findAll(PageRequest.of(listReqDto.getPage() - 1, listReqDto.getLimit()));
        List<Role> roleList = rolePage.getContent();
        ListRespDto<RoleRespDto> listRespDto = new ListRespDto<>();
        List<RoleRespDto> roleRespDtoList = new ArrayList<>();
        for (Role role : roleList) {
            RoleRespDto roleRespDto = new RoleRespDto(
                    role.getId(),
                    role.getName(),
                    role.getPid() == Global.SUPER_ROLE_PARENT ? null : roleRepository.findById(role.getPid()).get().getName(),
                    deptRepository.findById(role.getDeptid()).get().getFullname()
            );
            roleRespDtoList.add(roleRespDto);
        }
        listRespDto.setItems(roleRespDtoList);
        listRespDto.setTotal(rolePage.getTotalElements());
        return ResultUtil.success(listRespDto);
    }

    @Override
    @Transactional
    public Result delete(Integer id) {
        if(Global.SUPER_ROLE_ID == id){
            return ResultUtil.error(ResultEnum.FORBIDDEN);
        }
        roleRepository.deleteById(id);
        return ResultUtil.success();
    }
}
