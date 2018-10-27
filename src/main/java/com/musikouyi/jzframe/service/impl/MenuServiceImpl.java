package com.musikouyi.jzframe.service.impl;

import com.musikouyi.jzframe.dao.mapper.MenuMapper;
import com.musikouyi.jzframe.domain.node.MenuNode;
import com.musikouyi.jzframe.domain.node.ZTreeNode;
import com.musikouyi.jzframe.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class MenuServiceImpl implements IMenuService {

    private final MenuMapper menuMapper;

    @Autowired
    public MenuServiceImpl(MenuMapper menuMapper) {
        this.menuMapper = menuMapper;
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
    public List<MenuNode> getMenusByRoleIds(List<Integer> roleIds) {
        List<MenuNode> menuNodeList = menuMapper.getMenusByRoleIds(roleIds);
        System.out.println(menuNodeList.toString());
        return null;
    }

    @Override
    public Set<String> getCodesByRoleId(Integer roleId) {
        return menuMapper.getCodesByRoleId(roleId);
    }
}
