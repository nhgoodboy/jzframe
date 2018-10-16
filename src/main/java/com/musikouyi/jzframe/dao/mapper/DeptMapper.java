package com.musikouyi.jzframe.dao.mapper;

import com.musikouyi.jzframe.domain.entity.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Create with IDEA
 * Author: YJZ
 * DateTime: 2018/4/11 13:54
 **/
@Mapper
public interface DeptMapper {

    List<Dept> getDepts(@Param("currPage") int currPage, @Param("pageSize") int pageSize);

    Dept findById(@Param("id") int id);

    int getTotalNum();

    int createDept(Dept dept);

    int deleteDept(int id);

}
