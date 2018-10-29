package com.musikouyi.jzframe.service;

import com.musikouyi.jzframe.domain.entity.Result;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * 菜单服务
 *
 * @author fengshuonan
 * @date 2017-05-05 22:19
 */
public interface IMenuService {

    /**
     * 根据条件查询菜单
     *
     * @return
     * @date 2017年2月12日 下午9:14:34
     */
    List<Integer> getMenuIdsByRoleId(@Param("roleId") Integer roleId);

    /**
     * 根据角色获取菜单code
     */
    Set<String> getCodesByRoleId(Integer roleId);

    /**
     * 根据角色获取菜单树
     * @param roleId
     * @return
     */
    Result getMenusTree(Integer roleId);

    /**
     * 根据roleId修改可访问的菜单
     * @param roleId
     * @param menuIds
     * @return
     */
    Result changePermission(Integer roleId, List<Integer> menuIds);
}
