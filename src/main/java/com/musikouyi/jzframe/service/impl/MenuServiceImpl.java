package com.musikouyi.jzframe.service.impl;

import com.musikouyi.jzframe.domain.node.MenuNode;
import com.musikouyi.jzframe.domain.node.ZTreeNode;
import com.musikouyi.jzframe.service.IMenuService;

import java.util.List;
import java.util.Map;

public class MenuServiceImpl implements IMenuService {

    @Override
    public void delMenu(Long menuId) {

    }

    @Override
    public void delMenuContainSubMenus(Long menuId) {

    }

    @Override
    public List<Map<String, Object>> selectMenus(String condition, String level) {
        return null;
    }

    @Override
    public List<Long> getMenuIdsByRoleId(Integer roleId) {
        return null;
    }

    @Override
    public List<ZTreeNode> menuTreeList() {
        return null;
    }

    @Override
    public List<ZTreeNode> menuTreeListByMenuIds(List<Long> menuIds) {
        return null;
    }

    @Override
    public int deleteRelationByMenu(Long menuId) {
        return 0;
    }

    @Override
    public List<String> getResUrlsByRoleId(Integer roleId) {
        return null;
    }

    @Override
    public List<MenuNode> getMenusByRoleIds(List<Integer> roleIds) {
        return null;
    }
}
