package com.musikouyi.jzframe.dao.mapper;

import com.musikouyi.jzframe.domain.entity.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

@Mapper
public interface MenuMapper {

    /**
     * 根据条件查询菜单
     *
     * @return
     * @date 2017年2月12日 下午9:14:34
     */
    List<Integer> getMenuIdsByRoleId(@Param("roleId") Integer roleId);

    /**
     * 根据角色获取菜单code
     * @param roleId
     * @return
     */
    Set<String> getCodesByRoleId(Integer roleId);

    /**
     * 获取菜单列表
     * @return
     */
    List<Menu> getMenus();

}
