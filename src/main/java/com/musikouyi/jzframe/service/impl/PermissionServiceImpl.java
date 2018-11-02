package com.musikouyi.jzframe.service.impl;

import com.musikouyi.jzframe.common.constant.Global;
import com.musikouyi.jzframe.dao.mapper.PermissionMapper;
import com.musikouyi.jzframe.dao.mapper.RolePermissionRelationMapper;
import com.musikouyi.jzframe.domain.entity.Permission;
import com.musikouyi.jzframe.domain.entity.Result;
import com.musikouyi.jzframe.domain.enums.ResultEnum;
import com.musikouyi.jzframe.domain.node.TreeNode;
import com.musikouyi.jzframe.dto.PermissionTreeRespDto;
import com.musikouyi.jzframe.service.IPermissionService;
import com.musikouyi.jzframe.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class PermissionServiceImpl implements IPermissionService {

    private final PermissionMapper permissionMapper;

    private final RolePermissionRelationMapper rolePermissionRelationMapper;

    @Autowired
    public PermissionServiceImpl(PermissionMapper permissionMapper, RolePermissionRelationMapper rolePermissionRelationMapper) {
        this.permissionMapper = permissionMapper;
        this.rolePermissionRelationMapper = rolePermissionRelationMapper;
    }

    @Override
    public List<Integer> getPermissionIdsByRoleId(Integer roleId) {
        return permissionMapper.getPermissionIdsByRoleId(roleId);
    }

    @Override
    public Set<String> getCodesByRoleId(Integer roleId) {
        return permissionMapper.getCodesByRoleId(roleId);
    }

    private List<Permission> permissionList;

    @Override
    @Transactional(readOnly = true)
    public Result getPermissionsTree(Integer roleId) {
        List<TreeNode> treeNodeList = new ArrayList<>();
        permissionList = permissionMapper.getPermissions();
        for (Permission permission : permissionList) {
            if (Global.TOP_SIGN.equals(permission.getPCode())) {
                TreeNode treeNode = new TreeNode(
                        permission.getId(),
                        permission.getName(),
                        permission.getCode(),
                        permission.getPCode(),
                        new ArrayList<>()
                );
                treeNodeList.add(treeNode);
            }
        }
        for (TreeNode treeNode : treeNodeList) {
            generatePermissionTree(treeNode);
        }
        PermissionTreeRespDto permissionTreeRespDto = new PermissionTreeRespDto();
        permissionTreeRespDto.setPermissionsList(treeNodeList);
        permissionTreeRespDto.setCheckedPermissionIds(rolePermissionRelationMapper.getPermissionIdsByRoleId(roleId));
        return ResultUtil.success(permissionTreeRespDto);
    }

    private void generatePermissionTree(TreeNode pNode) {
        for (Permission permission : permissionList) {
            if (permission.getPCode().equals(pNode.getCode())) {
                TreeNode treeNode = new TreeNode(
                        permission.getId(),
                        permission.getName(),
                        permission.getCode(),
                        permission.getPCode(),
                        new ArrayList<>()
                );
                pNode.getChildren().add(treeNode);
                generatePermissionTree(treeNode);
            }
        }
    }

    @Override
    @Transactional
    public Result changePermission(Integer roleId, List<Integer> permissionIds) {
        if (Global.SUPER_ROLE_ID == roleId) {
            ResultUtil.error(ResultEnum.FORBIDDEN);
        }
        rolePermissionRelationMapper.deletePermissionIdsByRoleId(roleId);
        for (Integer permissionId : permissionIds) {
            rolePermissionRelationMapper.createRelation(roleId, permissionId);
        }
        return ResultUtil.success();
    }
}
