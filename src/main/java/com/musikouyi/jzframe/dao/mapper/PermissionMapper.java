package com.musikouyi.jzframe.dao.mapper;

import com.musikouyi.jzframe.domain.entity.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

@Mapper
public interface PermissionMapper {

    /**
     * 根据条件查询权限
     *
     * @return
     * @date 2017年2月12日 下午9:14:34
     */
    List<Integer> getPermissionIdsByRoleId(@Param("roleId") Integer roleId);

    /**
     * 根据角色获取权限code
     * @param roleId
     * @return
     */
    Set<String> getCodesByRoleId(Integer roleId);

    /**
     * 获取权限列表
     * @return
     */
    List<Permission> getPermissions();


    Set<String> getPermissionByUserName(@Param("username") String username);
}
