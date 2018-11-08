package com.musikouyi.jzframe.service;

import com.musikouyi.jzframe.domain.entity.Result;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * 权限服务
 *
 * @author fengshuonan
 * @date 2017-05-05 22:19
 */
public interface IPermissionService {

    /**
     * 根据条件查询权限
     *
     * @return
     * @date 2017年2月12日 下午9:14:34
     */
    List<Integer> getPermissionIdsByRoleId(@Param("roleId") Integer roleId);

    /**
     * 根据角色获取权限code
     */
    Set<String> getCodesByRoleId(Integer roleId);

    /**
     * 根据角色获取权限树
     *
     * @param roleId
     * @return
     */
    Result getPermissionsTree(Integer roleId);

    /**
     * 根据roleId修改可访问的权限
     *
     * @param roleId
     * @param permissionIds
     * @return
     */
    Result changePermission(Integer roleId, List<Integer> permissionIds);
}
