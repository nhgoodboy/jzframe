package com.musikouyi.jzframe.service.impl;

import com.musikouyi.jzframe.domain.entity.Result;
import com.musikouyi.jzframe.domain.entity.Role;
import com.musikouyi.jzframe.domain.node.ZTreeNode;
import com.musikouyi.jzframe.repository.RoleRepository;
import com.musikouyi.jzframe.service.IRoleService;
import com.musikouyi.jzframe.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
