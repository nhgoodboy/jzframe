package com.musikouyi.jzframe.service;

import com.musikouyi.jzframe.domain.entity.Result;
import com.musikouyi.jzframe.domain.entity.Role;
import com.musikouyi.jzframe.domain.node.ZTreeNode;
import com.musikouyi.jzframe.dto.ListReqDto;
import com.musikouyi.jzframe.dto.RoleDto;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

/**
 * 角色相关业务
 *
 * @author fengshuonan
 * @Date 2017年1月10日 下午9:11:57
 */
public interface IRoleService {

    /**
     * 获取角色列表
     */
    Result getRoleNameList();

    /**
     * 获取角色列表
     */
    Result findAll(ListReqDto listReqDto);

    /**
     * 删除角色
     */
    Result delete(Integer id);

    /**
     * 创建角色
     */
    Result create(RoleDto roleDto);

    /**
     * 修改角色
     */
    Result modify(RoleDto roleDto);
}
