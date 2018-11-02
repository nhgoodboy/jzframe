package com.musikouyi.jzframe.dao.mapper;

import com.musikouyi.jzframe.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    User findByAccount(@Param("username") String username);

    String findSaltByAccount(@Param("username") String username);
}
