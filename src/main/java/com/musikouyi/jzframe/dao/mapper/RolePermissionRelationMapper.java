package com.musikouyi.jzframe.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RolePermissionRelationMapper {

    Integer getIdByRoleIdAndPermissionId(@Param("roleId") Integer roleId, @Param("permissionId") Integer permissionId);

    List<Integer> getPermissionIdsByRoleId(@Param("roleId") Integer roleId);

    void deletePermissionIdsByRoleId(@Param("roleId") Integer roleId);

    void createRelation(@Param("roleId") Integer roleId, @Param("permissionId") Integer permissionId);
}

