package com.musikouyi.jzframe.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleMenuRelationMapper {

    Integer getIdByRoleIdAndMenuId(@Param("roleId") Integer roleId, @Param("menuId") Integer menuId);

    List<Integer> getMenuIdsByRoleId(@Param("roleId") Integer roleId);

    void deleteMenuIdsByRoleId(@Param("roleId") Integer roleId);

    void createRelation(@Param("roleId") Integer roleId, @Param("menuId") Integer menuId);
}
