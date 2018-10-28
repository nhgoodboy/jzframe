package com.musikouyi.jzframe.service.impl;

import com.musikouyi.jzframe.dao.mapper.MenuMapper;
import com.musikouyi.jzframe.dao.mapper.RoleMenuRelationMapper;
import com.musikouyi.jzframe.domain.entity.Menu;
import com.musikouyi.jzframe.domain.entity.Result;
import com.musikouyi.jzframe.domain.node.MenuNode;
import com.musikouyi.jzframe.domain.node.TreeNode;
import com.musikouyi.jzframe.domain.node.ZTreeNode;
import com.musikouyi.jzframe.dto.MenuTreeRespDto;
import com.musikouyi.jzframe.service.IMenuService;
import com.musikouyi.jzframe.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
    public void delMenu(Integer menuId) {

    }

    @Override
    public void delMenuContainSubMenus(Integer menuId) {

    }

    @Override
    public List<Map<String, Object>> selectMenus(String condition, String level) {
        return null;
    }

    @Override
    public List<Integer> getMenuIdsByRoleId(Integer roleId) {
        return menuMapper.getMenuIdsByRoleId(roleId);
    }

    @Override
    public List<ZTreeNode> menuTreeList() {
        return null;
    }

    @Override
    public List<ZTreeNode> menuTreeListByMenuIds(List<Integer> menuIds) {
        return null;
    }

    @Override
    public int deleteRelationByMenu(Integer menuId) {
        return 0;
    }

    @Override
    public List<String> getResUrlsByRoleId(Integer roleId) {
        return null;
    }

    @Override
    public List<MenuNode> getMenusByRoleId(List<Integer> roleIds) {
        return null;
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
            if (menu.getPCode().equals("0")) {
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
        for(TreeNode treeNode: treeNodeList){
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
        roleMenuRelationMapper.deleteMenuIdsByRoleId(roleId);
        for (Integer menuId : menuIds) {
            roleMenuRelationMapper.createRelation(roleId, menuId);
        }
        return ResultUtil.success();
    }
}
