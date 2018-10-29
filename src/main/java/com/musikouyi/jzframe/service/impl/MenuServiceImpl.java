package com.musikouyi.jzframe.service.impl;

import com.musikouyi.jzframe.common.constant.Global;
import com.musikouyi.jzframe.dao.mapper.MenuMapper;
import com.musikouyi.jzframe.dao.mapper.RoleMenuRelationMapper;
import com.musikouyi.jzframe.domain.entity.Menu;
import com.musikouyi.jzframe.domain.entity.Result;
import com.musikouyi.jzframe.domain.enums.ResultEnum;
import com.musikouyi.jzframe.domain.node.TreeNode;
import com.musikouyi.jzframe.dto.MenuTreeRespDto;
import com.musikouyi.jzframe.service.IMenuService;
import com.musikouyi.jzframe.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class MenuServiceImpl implements IMenuService {

    private final MenuMapper menuMapper;

    private final RoleMenuRelationMapper roleMenuRelationMapper;

    @Autowired
    public MenuServiceImpl(MenuMapper menuMapper, RoleMenuRelationMapper roleMenuRelationMapper) {
        this.menuMapper = menuMapper;
        this.roleMenuRelationMapper = roleMenuRelationMapper;
    }

    @Override
    public List<Integer> getMenuIdsByRoleId(Integer roleId) {
        return menuMapper.getMenuIdsByRoleId(roleId);
    }

    @Override
    public Set<String> getCodesByRoleId(Integer roleId) {
        return menuMapper.getCodesByRoleId(roleId);
    }

    private List<Menu> menuList;

    @Override
    @Transactional(readOnly = true)
    public Result getMenusTree(Integer roleId) {
        List<TreeNode> treeNodeList = new ArrayList<>();
        menuList = menuMapper.getMenus();
        for (Menu menu : menuList) {
            if (Global.TOP_SIGN.equals(menu.getPCode())) {
                TreeNode treeNode = new TreeNode(
                        menu.getId(),
                        menu.getName(),
                        menu.getCode(),
                        menu.getPCode(),
                        new ArrayList<>()
                );
                treeNodeList.add(treeNode);
            }
        }
        for (TreeNode treeNode : treeNodeList) {
            generateMenuTree(treeNode);
        }
        MenuTreeRespDto menuTreeRespDto = new MenuTreeRespDto();
        menuTreeRespDto.setMenusList(treeNodeList);
        menuTreeRespDto.setCheckedMenuIds(roleMenuRelationMapper.getMenuIdsByRoleId(roleId));
        return ResultUtil.success(menuTreeRespDto);
    }

    private void generateMenuTree(TreeNode pNode) {
        for (Menu menu : menuList) {
            if (menu.getPCode().equals(pNode.getCode())) {
                TreeNode treeNode = new TreeNode(
                        menu.getId(),
                        menu.getName(),
                        menu.getCode(),
                        menu.getPCode(),
                        new ArrayList<>()
                );
                pNode.getChildren().add(treeNode);
                generateMenuTree(treeNode);
            }
        }
    }

    @Override
    @Transactional
    public Result changePermission(Integer roleId, List<Integer> menuIds) {
        if (Global.SUPER_ROLE_ID == roleId) {
            ResultUtil.error(ResultEnum.FORBIDDEN);
        }
        roleMenuRelationMapper.deleteMenuIdsByRoleId(roleId);
        for (Integer menuId : menuIds) {
            roleMenuRelationMapper.createRelation(roleId, menuId);
        }
        return ResultUtil.success();
    }
}
