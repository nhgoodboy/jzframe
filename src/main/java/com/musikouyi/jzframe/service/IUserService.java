package com.musikouyi.jzframe.service;

import com.musikouyi.jzframe.domain.entity.Result;
import com.musikouyi.jzframe.domain.entity.User;
import com.musikouyi.jzframe.dto.ListReqDto;
import com.musikouyi.jzframe.dto.UserInfoReqDto;
import com.musikouyi.jzframe.dto.UserReqDto;
import org.springframework.data.repository.query.Param;

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
     * 通过账号获取用户
     */
    User getByAccount(String account);

    /**
     * 通过userid获取用户信息
     */
    Result userInfo(Integer userId);

    /**
     * 获取用户列表(排除status为deleted的)
     */
    Result findAll(ListReqDto listReqDto);

    /**
     * 删除用户(软删除)
     */
    Result delete(Integer id);

    /**
     * 创建用户
     */
    Result create(UserReqDto userReqDto);

    /**
     * 修改用户
     */
    Result modify(UserReqDto userReqDto);

    /**
     * 更改密码
     */
    Result changePwd(Integer id, String newPassword);

    /**
     * 修改用户信息
     */
    Result editUserInfo(UserInfoReqDto userInfoReqDto);
}
