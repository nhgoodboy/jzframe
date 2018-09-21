package com.musikouyi.jzframe.service;

import com.musikouyi.jzframe.domain.entity.Result;
import com.musikouyi.jzframe.domain.entity.User;
import com.musikouyi.jzframe.dto.ListReqDto;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 管理员表 Mapper 接口
 * </p>
 *
 * @author yjz
 * @since 2018-09-14
 */
public interface IUserService {

    /**
     * 修改用户状态
     */
    int setStatus(@Param("userId") Integer userId, @Param("status") int status);

    /**
     * 修改密码
     */
    int changePwd(@Param("userId") Integer userId, @Param("pwd") String pwd);

    /**
     * 根据条件查询用户列表
     */
//    List<Map<String, Object>> selectUsers(@Param("dataScope") DataScope dataScope, @Param("name") String name, @Param("beginTime") String beginTime, @Param("endTime") String endTime, @Param("deptid") Integer deptid);

    /**
     * 设置用户的角色
     */
    int setRoles(@Param("userId") Integer userId, @Param("roleIds") String roleIds);

    /**
     * 通过账号获取用户
     */
    User getByAccount(String account);

    /**
     * 通过userid获取用户信息
     */
    Result findById(Integer userId);

    /**
     * 获取用户列表
     */
    Result findAll(ListReqDto listReqDto);

    /**
     * 删除用户
     */
    Result deleteUser(Integer id);
}
