package com.musikouyi.jzframe.dao.mapper;

import com.musikouyi.jzframe.domain.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleMapper {

    List<Role> findByDeptId(@Param("deptId") Integer deptId);
}
