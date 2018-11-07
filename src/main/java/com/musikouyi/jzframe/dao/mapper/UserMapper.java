package com.musikouyi.jzframe.dao.mapper;

import com.musikouyi.jzframe.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    User findByAccount(@Param("username") String username);

    String findSaltByAccount(@Param("username") String username);

    List<User> findByDeptId(@Param("deptId") Integer id);
}
